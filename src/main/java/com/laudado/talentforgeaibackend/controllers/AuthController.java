package com.laudado.talentforgeaibackend.controllers;

import com.laudado.talentforgeaibackend.dto.AuthUser;
import com.laudado.talentforgeaibackend.dto.requestDtos.*;
import com.laudado.talentforgeaibackend.dto.responseDtos.*;
import com.laudado.talentforgeaibackend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register/jobseeker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegisterResponse> registerJobSeeker(
            @Valid @ModelAttribute RegisterJobseekerRequest registerJobseekerRequest) {
        return ResponseEntity.ok(authService.registerJobSeeker(registerJobseekerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @ModelAttribute LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register/recruiter")
    public ResponseEntity<RegisterResponse> registerRecruiter(@RequestBody RegisterRecruiterRequest request){
        return ResponseEntity.ok(authService.registerRecruiter(request));
    }
    @PostMapping("/logout")
    public void logout(){

    }
    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refresh(@RequestBody RefreshTokenRequest refreshToken){
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
    @GetMapping("/me")
    public ResponseEntity<AuthUser> getMe(){
        return ResponseEntity.ok(authService.getMe());
    }
    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest request){
        authService.forgotPassword(request);
    }
    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody PasswordResetRequest request){
        authService.resetPassword(request);
    }
    @GetMapping("/verify-email")
    public ResponseEntity<VerifyEmailResponse> verifyEmail(@RequestParam String token){
        return ResponseEntity.ok(authService.verifyEmail(token));
    }
    @PostMapping("/resend-verification")
    public void resendVerificationEmail(@RequestBody ResendVerificationRequest email){
        authService.resendVerificationEmail(email);
    }
}
