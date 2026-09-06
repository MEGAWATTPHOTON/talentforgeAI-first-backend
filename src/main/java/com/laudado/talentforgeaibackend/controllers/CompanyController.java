package com.laudado.talentforgeaibackend.controllers;

import com.laudado.talentforgeaibackend.dto.PaginationParams;
import com.laudado.talentforgeaibackend.dto.requestDtos.RegisterCompanyRequest;
import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyResponse;
import com.laudado.talentforgeaibackend.dto.responseDtos.PaginatedResponse;
import com.laudado.talentforgeaibackend.models.CompanyJoinRequest;
import com.laudado.talentforgeaibackend.models.CompanyMembership;
import com.laudado.talentforgeaibackend.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<PaginatedResponse<CompanyResponse>> getCompanies(
            @RequestParam(required = false) String companyName, @RequestBody PaginationParams params) {

        return ResponseEntity.ok(
                companyService.getCompanies(companyName,params)
        );
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponse> getById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                companyService.getById(id)
        );
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyResponse> register(
            @RequestBody RegisterCompanyRequest request) {

        return ResponseEntity.ok(
                companyService.register(request)
        );
    }

    @PatchMapping("/companies/{id}")
    public ResponseEntity<CompanyResponse> update(
            @PathVariable String id,
            @RequestBody CompanyResponse request) {

        return ResponseEntity.ok(
                companyService.update(id, request)
        );
    }

    @GetMapping("/companies/{companyId}/members")
    public ResponseEntity<List<CompanyMembership>> getListOfMembers(
            @PathVariable String companyId) {

        return ResponseEntity.ok(
                companyService.getCompanyMembers(companyId)
        );
    }

    @DeleteMapping("/companies/{companyId}/members/{membershipId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable String companyId,
            @PathVariable String membershipId) {

        companyService.removeMember(companyId, membershipId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/companies/{companyId}/join-requests")
    public ResponseEntity<List<CompanyJoinRequest>> getJoinRequests(
            @PathVariable String companyId) {

        return ResponseEntity.ok(
                companyService.getJoinRequests(companyId)
        );
    }

    @PostMapping("/companies/{companyId}/join-requests")
    public ResponseEntity<CompanyJoinRequest> requestToJoin(
            @PathVariable String companyId,
            @RequestBody CompanyJoinRequest request) {

        return ResponseEntity.ok(
                companyService.requestToJoin(
                        companyId,
                        request.getMessage()
                )
        );
    }

    @DeleteMapping("/companies/{companyId}/join-requests/{requestId}")
    public ResponseEntity<Void> cancelJoinRequest(
            @PathVariable String companyId,
            @PathVariable String requestId) {

        companyService.cancelJoinService(companyId, requestId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/companies/{companyId}/join-requests/{requestId}/approve")
    public ResponseEntity<CompanyMembership> approveJoinRequest(
            @PathVariable String companyId,
            @PathVariable String requestId) {

        return ResponseEntity.ok(
                companyService.approveJoinRequest(companyId, requestId)
        );
    }

    @PostMapping("/companies/{companyId}/join-requests/{requestId}/reject")
    public ResponseEntity<Void> rejectJoinRequest(
            @PathVariable String companyId,
            @PathVariable String requestId) {

        companyService.rejectJoinRequest(companyId, requestId);

        return ResponseEntity.noContent().build();
    }
}

