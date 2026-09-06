package com.laudado.talentforgeaibackend.services;

import com.laudado.talentforgeaibackend.dto.PaginationMeta;
import com.laudado.talentforgeaibackend.dto.PaginationParams;
import com.laudado.talentforgeaibackend.dto.requestDtos.RegisterCompanyRequest;
import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyResponse;
import com.laudado.talentforgeaibackend.dto.responseDtos.PaginatedResponse;
import com.laudado.talentforgeaibackend.enums.CompanyRole;
import com.laudado.talentforgeaibackend.enums.JoinRequestStatus;
import com.laudado.talentforgeaibackend.enums.MembershipStatus;
import com.laudado.talentforgeaibackend.enums.SortDirection;
import com.laudado.talentforgeaibackend.exception.CompanyNotFoundException;
import com.laudado.talentforgeaibackend.exception.MembershipAlreadyExistsException;
import com.laudado.talentforgeaibackend.exception.RequestNotFoundException;
import com.laudado.talentforgeaibackend.mappers.CompanyToCompanyResponseMapper;
import com.laudado.talentforgeaibackend.mappers.RegisterCompanyRequestToCompanyMapper;
import com.laudado.talentforgeaibackend.models.Company;
import com.laudado.talentforgeaibackend.models.CompanyJoinRequest;
import com.laudado.talentforgeaibackend.models.CompanyMembership;
import com.laudado.talentforgeaibackend.repositories.CompanyJoinRequestRepository;
import com.laudado.talentforgeaibackend.repositories.CompanyMembershipRepository;
import com.laudado.talentforgeaibackend.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepo;
    private final CompanyMembershipRepository companyMembershipRepo;
    private final RegisterCompanyRequestToCompanyMapper companyMapper;
    private final CompanyJoinRequestRepository joinRepo;
    private final AuthService authService;


    // ---------------------------------------------------------
    // GET COMPANIES
    // ---------------------------------------------------------

    public PaginatedResponse<CompanyResponse> getCompanies(
            String companyName,
            PaginationParams params
    ) {

        Sort.Direction direction =
                params.getSortDirection() == SortDirection.ASC
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, params.getSortBy());

        Pageable pageable = PageRequest.of(
                params.getPage() - 1,
                params.getPageSize(),
                sort
        );

        Page<Company> companyPage;

        if (companyName == null || companyName.isBlank()) {
            companyPage = companyRepo.findAll(pageable);
        } else {
            companyPage = companyRepo.findByNameContainingIgnoreCase(
                    companyName,
                    pageable
            );
        }

        PaginatedResponse<CompanyResponse> response =
                new PaginatedResponse<>();

        List<CompanyResponse> companyResponses =
                new ArrayList<>();

        companyPage.forEach(company ->
                companyResponses.add(
                        CompanyToCompanyResponseMapper.toDto(company)
                )
        );

        response.setItems(companyResponses);

        PaginationMeta metaData = new PaginationMeta();

        metaData.setPage(params.getPage());
        metaData.setPageSize(params.getPageSize());
        metaData.setTotalItems((int) companyPage.getTotalElements());
        metaData.setTotalPages(companyPage.getTotalPages());
        metaData.setHasNextPage(companyPage.hasNext());
        metaData.setHasPreviousPage(companyPage.hasPrevious());

        response.setMeta(metaData);

        return response;
    }


    // ---------------------------------------------------------
    // GET COMPANY BY ID
    // ---------------------------------------------------------

    public CompanyResponse getById(String id) {

        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        Company company = companyRepo
                .findById(new ObjectId(id))
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found"
                        )
                );

        return CompanyToCompanyResponseMapper.toDto(company);
    }


    // ---------------------------------------------------------
    // REGISTER COMPANY
    // ---------------------------------------------------------

    public CompanyResponse register(RegisterCompanyRequest request) {

        Company company = companyMapper.toCompany(request);

        return CompanyToCompanyResponseMapper.toDto(
                companyRepo.save(company)
        );
    }


    // ---------------------------------------------------------
    // UPDATE COMPANY
    // ---------------------------------------------------------

    public CompanyResponse update(
            String id,
            CompanyResponse request
    ) {

        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        Company company = companyRepo
                .findById(new ObjectId(id))
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found"
                        )
                );

        /*
         * Only allow actual company-profile fields to be updated.
         *
         * Do NOT allow:
         * - id
         * - verificationStatus
         * - activeJobsCount
         * - followersCount
         * - createdAt
         * - updatedAt
         */

        if (request.getEmailDomains() != null) {
            company.setEmailDomains(
                    new HashSet<>(request.getEmailDomains())
            );
        }

        if (request.getName() != null) {
            company.setName(request.getName());
        }

        if (request.getSlug() != null) {
            company.setSlug(request.getSlug());
        }

        if (request.getLogoUrl() != null) {
            company.setLogoUrl(request.getLogoUrl());
        }

        if (request.getBannerUrl() != null) {
            company.setBannerUrl(request.getBannerUrl());
        }

        if (request.getWebsite() != null) {
            company.setWebsiteUrl(request.getWebsite());
        }

        if (request.getIndustry() != null) {
            company.setIndustry(request.getIndustry());
        }

        if (request.getDescription() != null) {
            company.setDescription(request.getDescription());
        }

        if (request.getFoundedYear() != null) {
            company.setFoundedYear(
                    Year.of(request.getFoundedYear())
            );
        }

        if (request.getLocation() != null) {
            company.setLocation(request.getLocation());
        }

        company.setUpdatedAt(LocalDateTime.now());

        Company savedCompany = companyRepo.save(company);

        return CompanyToCompanyResponseMapper.toDto(savedCompany);
    }


    // ---------------------------------------------------------
    // GET COMPANY MEMBERS
    // ---------------------------------------------------------

    public List<CompanyMembership> getCompanyMembers(
            String companyId
    ) {

        if (!ObjectId.isValid(companyId)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return companyMembershipRepo.findByCompanyId(
                new ObjectId(companyId)
        );
    }


    // ---------------------------------------------------------
    // REMOVE MEMBER
    // ---------------------------------------------------------

    public void removeMember(
            String companyId,
            String membershipId
    ) {

        if (!ObjectId.isValid(companyId)
                || !ObjectId.isValid(membershipId)) {

            throw new IllegalArgumentException("Invalid ID");
        }

        long deleted = companyMembershipRepo.deleteByIdAndCompanyId(
                new ObjectId(membershipId),
                new ObjectId(companyId)
        );

        if (deleted == 0) {
            throw new RequestNotFoundException(
                    "Membership not found"
            );
        }
    }


    // ---------------------------------------------------------
    // GET JOIN REQUESTS
    // ---------------------------------------------------------

    public List<CompanyJoinRequest> getJoinRequests(
            String companyId
    ) {

        if (!ObjectId.isValid(companyId)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return joinRepo.findByCompanyId(
                new ObjectId(companyId)
        );
    }


    // ---------------------------------------------------------
    // REQUEST TO JOIN COMPANY
    // ---------------------------------------------------------

    public CompanyJoinRequest requestToJoin(
            String companyId,
            String message
    ) {

        if (!ObjectId.isValid(companyId)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        ObjectId companyObjectId = new ObjectId(companyId);

        // Make sure company exists
        companyRepo.findById(companyObjectId)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found"
                        )
                );

        ObjectId userId =
                new ObjectId(authService.getMe().getId());

        // Prevent duplicate membership
        if (companyMembershipRepo
                .findByUserIdAndCompanyId(
                        userId,
                        companyObjectId
                )
                .isPresent()) {

            throw new MembershipAlreadyExistsException(
                    "User is already a member of this company"
            );
        }

        CompanyJoinRequest request =
                new CompanyJoinRequest();

        request.setUserId(userId);
        request.setCompanyId(companyObjectId);

        if (message != null && !message.isBlank()) {
            request.setMessage(message);
        }

        request.setStatus(JoinRequestStatus.PENDING);

        LocalDateTime now = LocalDateTime.now();

        request.setCreatedAt(now);
        request.setUpdatedAt(now);

        return joinRepo.save(request);
    }


    // ---------------------------------------------------------
    // CANCEL JOIN REQUEST
    // ---------------------------------------------------------

    public void cancelJoinService(
            String companyId,
            String requestId
    ) {

        if (!ObjectId.isValid(companyId)
                || !ObjectId.isValid(requestId)) {

            throw new IllegalArgumentException("Invalid ID/s");
        }

        long deleted = joinRepo.deleteByIdAndCompanyId(
                new ObjectId(requestId),
                new ObjectId(companyId)
        );

        if (deleted == 0) {
            throw new RequestNotFoundException(
                    "Join request not found"
            );
        }
    }


    // ---------------------------------------------------------
    // APPROVE JOIN REQUEST
    // ---------------------------------------------------------

    public CompanyMembership approveJoinRequest(
            String companyId,
            String requestId
    ) {

        CompanyJoinRequest request =
                requestUpdate(companyId, requestId);

        // Prevent approving an already processed request
        if (request.getStatus() != JoinRequestStatus.PENDING) {
            throw new IllegalStateException(
                    "Join request has already been processed"
            );
        }

        // Prevent duplicate membership
        if (companyMembershipRepo
                .findByUserIdAndCompanyId(
                        request.getUserId(),
                        request.getCompanyId()
                )
                .isPresent()) {

            throw new MembershipAlreadyExistsException(
                    "User is already a member of this company"
            );
        }

        LocalDateTime now = LocalDateTime.now();

        request.setStatus(JoinRequestStatus.APPROVED);
        request.setUpdatedAt(now);

        CompanyMembership membership =
                new CompanyMembership();

        membership.setUserId(request.getUserId());
        membership.setCompanyId(request.getCompanyId());
        membership.setJoinedAt(now);
        membership.setRole(CompanyRole.RECRUITER);
        membership.setStatus(MembershipStatus.ACTIVE);

        joinRepo.save(request);

        return companyMembershipRepo.save(membership);
    }


    // ---------------------------------------------------------
    // REJECT JOIN REQUEST
    // ---------------------------------------------------------

    public void rejectJoinRequest(
            String companyId,
            String requestId
    ) {

        CompanyJoinRequest request =
                requestUpdate(companyId, requestId);

        if (request.getStatus() != JoinRequestStatus.PENDING) {
            throw new IllegalStateException(
                    "Join request has already been processed"
            );
        }

        request.setStatus(JoinRequestStatus.REJECTED);
        request.setUpdatedAt(LocalDateTime.now());

        joinRepo.save(request);
    }


    // ---------------------------------------------------------
    // FIND JOIN REQUEST
    // ---------------------------------------------------------

    private CompanyJoinRequest requestUpdate(
            String companyId,
            String requestId
    ) {

        if (!ObjectId.isValid(companyId)
                || !ObjectId.isValid(requestId)) {

            throw new IllegalArgumentException(
                    "Invalid ID/s"
            );
        }

        CompanyJoinRequest request =
                joinRepo.findByIdAndCompanyId(
                        new ObjectId(requestId),
                        new ObjectId(companyId)
                );

        if (request == null) {
            throw new RequestNotFoundException(
                    "Request not found"
            );
        }

        return request;
    }
}
