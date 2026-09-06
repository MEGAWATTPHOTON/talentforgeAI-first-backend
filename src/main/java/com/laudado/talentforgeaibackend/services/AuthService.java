package com.laudado.talentforgeaibackend.services;

import com.laudado.talentforgeaibackend.dto.AuthUser;
import com.laudado.talentforgeaibackend.dto.requestDtos.*;
import com.laudado.talentforgeaibackend.dto.responseDtos.*;
import com.laudado.talentforgeaibackend.enums.Role;
import com.laudado.talentforgeaibackend.enums.UserStatus;
import com.laudado.talentforgeaibackend.exception.EmailAlreadyExistsException;
import com.laudado.talentforgeaibackend.exception.InvalidTokenException;
import com.laudado.talentforgeaibackend.exception.UserNotFoundException;
import com.laudado.talentforgeaibackend.exception.UsernameAlreadyExistsException;
import com.laudado.talentforgeaibackend.mappers.AuthUserMapper;
import com.laudado.talentforgeaibackend.models.*;
import com.laudado.talentforgeaibackend.repositories.UserRepository;
import com.laudado.talentforgeaibackend.repositories.VerificationTokenRepository;
import com.laudado.talentforgeaibackend.security.JWTWebService;
import com.laudado.talentforgeaibackend.security.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final JWTWebService jwtWebToken;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;
    private final VerificationTokenService authTokenService;
    private final VerificationTokenRepository verificationTokenRepo;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder(12);


    public RegisterResponse registerJobSeeker(
            RegisterJobseekerRequest request
    ) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email or Username already exists"
            );
        }

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException(
                    "Email or Username already exists"
            );
        }

        User user = new User();

        JobSeekerProfile jobSeekerProfile =
                new JobSeekerProfile();

        Profile profile = new Profile();

        LocalDateTime now = LocalDateTime.now();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRoles(Set.of(Role.JOB_SEEKER));
        user.setStatus(UserStatus.ACTIVE);
        user.setEmailIsVerified(false);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setDateOfBirth(
                LocalDate.parse(request.getDateOfBirth())
        );
        profile.setGender(request.getGender());
        profile.setPhoneNumber(request.getPhoneNumber());

        if (request.getProfilePicture() != null) {
            profile.setProfilePictureUrl(
                    cloudinaryService.uploadFile(
                            request.getProfilePicture()
                    )
            );
        }

        Location location = new Location();
        location.setCountry(request.getCountry());

        profile.setLocation(location);

        if (request.getCoverLetter() != null) {
            jobSeekerProfile.setCoverLetterUrl(
                    cloudinaryService.uploadFile(
                            request.getCoverLetter()
                    )
            );
        }

        if (request.getResume() != null) {
            jobSeekerProfile.setResumeUrl(
                    cloudinaryService.uploadFile(
                            request.getResume()
                    )
            );
        }

        jobSeekerProfile.setGithubUrl(request.getGithubUrl());
        jobSeekerProfile.setPortfolioUrl(request.getPortfolioUrl());
        jobSeekerProfile.setLinkedinUrl(request.getLinkedinUrl());

        user.setUserProfile(profile);
        user.setJobSeekerProfile(jobSeekerProfile);
        userRepo.save(user);
        return getRegisterResponse(user);
    }


    public RegisterResponse registerRecruiter(
            RegisterRecruiterRequest request
    ) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email or Username already exists"
            );
        }

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException(
                    "Email or Username already exists"
            );
        }

        User user = new User();

        Profile profile = new Profile();
        RecruiterProfile recruiterProfile =
                new RecruiterProfile();

        LocalDateTime now = LocalDateTime.now();

        user.setUsername(request.getUsername());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setEmail(request.getEmail());

        user.setRoles(Set.of(Role.RECRUITER));
        user.setStatus(UserStatus.ACTIVE);
        user.setEmailIsVerified(false);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());

        user.setUserProfile(profile);
        user.setRecruiterProfile(recruiterProfile);

        return getRegisterResponse(user);
    }


    private RegisterResponse getRegisterResponse(User user) {

        User savedUser = userRepo.save(user);

        AuthUser authUser =
                AuthUserMapper.toDto(savedUser);

        RegisterResponse response =
                new RegisterResponse();

        response.setUser(authUser);
        response.setRequiresEmailVerification(true);

        emailVerificationDetails(savedUser);

        return response;
    }


    public void emailVerificationDetails(User user) {

        verificationTokenRepo.deleteByUserId(
                user.getId()
        );

        LocalDateTime now = LocalDateTime.now();

        String rawToken =
                authTokenService.generateAuthToken();

        String tokenHash =
                authTokenService.hashToken(rawToken);

        VerificationToken verificationToken =
                new VerificationToken();

        verificationToken.setUserId(user.getId());
        verificationToken.setTokenHash(tokenHash);
        verificationToken.setCreatedAt(now);
        verificationToken.setExpiresAt(
                now.plusMinutes(15)
        );
        verificationToken.setUsed(false);

        verificationTokenRepo.save(verificationToken);

        emailService.sendEmailVerificationEmail(
                user.getEmail(),
                rawToken
        );
    }


    public LoginResponse login(LoginRequest request) {

        User user =
                userRepo.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException(
                    "Invalid Email or Password"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid Email or Password"
            );
        }

        int refreshTokenDays = 1;

        if (request.isRememberMe()) {
            refreshTokenDays = 30;
        }

        String accessToken =
                jwtWebToken.generateAccessToken(
                        user.getUsername()
                );

        String refreshToken =
                jwtWebToken.generateRefreshToken(
                        user.getUsername(),
                        refreshTokenDays
                );

        JWTResponse jwtResponse =
                new JWTResponse(
                        accessToken,
                        refreshToken,
                        "Bearer",
                        jwtWebToken.accessTokeLifetime
                );

        LoginResponse response =
                new LoginResponse();

        response.setUser(
                AuthUserMapper.toDto(user)
        );

        response.setTokens(jwtResponse);

        return response;
    }


    public JWTResponse refresh(
            RefreshTokenRequest request
    ) {

        if (!jwtWebToken.isRefreshTokenValid(
                request.getRefreshToken()
        )) {
            throw new InvalidTokenException(
                    "Invalid token"
            );
        }

        String username =
                jwtWebToken.getUsername(
                        request.getRefreshToken()
                );

        String accessToken =
                jwtWebToken.generateAccessToken(username);

        return new JWTResponse(
                accessToken,
                request.getRefreshToken(),
                "Bearer",
                jwtWebToken.accessTokeLifetime
        );
    }


    public AuthUser getMe() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null) {
            throw new RuntimeException(
                    "Authentication failed"
            );
        }

        String username =
                authentication.getName();

        User user =
                userRepo.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(
                    "User not found"
            );
        }

        return AuthUserMapper.toDto(user);
    }


    public void forgotPassword(
            ForgotPasswordRequest request
    ) {

        User user =
                userRepo.findByEmail(
                        request.getEmail()
                );

        /*
         * Do not reveal whether an email exists.
         */
        if (user == null) {
            return;
        }

        verificationTokenRepo.deleteByUserId(
                user.getId()
        );

        LocalDateTime now =
                LocalDateTime.now();

        String resetToken =
                authTokenService.generateAuthToken();

        VerificationToken passwordResetToken =
                new VerificationToken();

        passwordResetToken.setUserId(user.getId());

        passwordResetToken.setTokenHash(
                authTokenService.hashToken(resetToken)
        );

        passwordResetToken.setCreatedAt(now);

        passwordResetToken.setExpiresAt(
                now.plusMinutes(15)
        );

        passwordResetToken.setUsed(false);

        verificationTokenRepo.save(
                passwordResetToken
        );

        emailService.sendPasswordResetEmail(
                user.getEmail(),
                resetToken
        );
    }


    public void resetPassword(
            PasswordResetRequest request
    ) {

        String hash =
                authTokenService.hashToken(
                        request.getToken()
                );

        VerificationToken token =
                verificationTokenRepo
                        .findByTokenHash(hash)
                        .orElseThrow(
                                () -> new InvalidTokenException(
                                        "Invalid token"
                                )
                        );

        if (LocalDateTime.now()
                .isAfter(token.getExpiresAt())) {

            throw new InvalidTokenException(
                    "Token expired"
            );
        }

        User user =
                userRepo.findById(
                        token.getUserId()
                ).orElseThrow(
                        () -> new UserNotFoundException(
                                "User not found"
                        )
                );

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        user.setUpdatedAt(
                LocalDateTime.now()
        );

        userRepo.save(user);

        verificationTokenRepo.deleteByUserId(
                user.getId()
        );
    }


    public VerifyEmailResponse verifyEmail(
            String token
    ) {

        String hash =
                authTokenService.hashToken(token);

        VerificationToken verificationToken =
                verificationTokenRepo
                        .findByTokenHash(hash)
                        .orElseThrow(
                                () -> new InvalidTokenException(
                                        "Invalid token"
                                )
                        );

        if (LocalDateTime.now()
                .isAfter(
                        verificationToken.getExpiresAt()
                )) {

            throw new InvalidTokenException(
                    "Token expired"
            );
        }

        User user =
                userRepo.findById(
                        verificationToken.getUserId()
                ).orElseThrow(
                        () -> new UserNotFoundException(
                                "User not found"
                        )
                );

        user.setEmailIsVerified(true);
        user.setUpdatedAt(LocalDateTime.now());

        userRepo.save(user);

        verificationTokenRepo.deleteByUserId(
                user.getId()
        );

        return new VerifyEmailResponse(
                true,
                user.getEmail()
        );
    }


    public void resendVerificationEmail(
            ResendVerificationRequest request
    ) {

        User user =
                userRepo.findByEmail(
                        request.getEmail()
                );

        if (user == null) {
            throw new UserNotFoundException(
                    "User not found!"
            );
        }

        /*
         * Don't send another verification email
         * if the email has already been verified.
         */
        if (user.isEmailIsVerified()) {
            return;
        }

        emailVerificationDetails(user);
    }
}
