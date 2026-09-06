package com.laudado.talentforgeaibackend.controllers;

import com.laudado.talentforgeaibackend.dto.requestDtos.InviteRecruiterRequest;
import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyInvitationResponse;
import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyMembershipResponse;
import com.laudado.talentforgeaibackend.services.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping("/companies/{companyId}/invitations")
    public ResponseEntity<List<CompanyInvitationResponse>> invitationsList(
            @PathVariable String companyId) {

        return ResponseEntity.ok(
                invitationService.invitationList(companyId)
        );
    }

    @PostMapping("/companies/{companyId}/invitations")
    public ResponseEntity<CompanyInvitationResponse> inviteRecruiter(
            @PathVariable String companyId,
            @RequestBody InviteRecruiterRequest request) {

        return ResponseEntity.ok(
                invitationService.inviteRecruiter(
                        companyId,
                        request.getEmail()
                )
        );
    }

    @DeleteMapping("/companies/{companyId}/invitations/{invitationId}")
    public ResponseEntity<Void> cancelInvitation(
            @PathVariable String companyId,
            @PathVariable String invitationId) {

        invitationService.cancelInvitation(companyId, invitationId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/invitations/{invitationId}/accept")
    public ResponseEntity<CompanyMembershipResponse> acceptInvitation(
            @PathVariable String invitationId) {

        return ResponseEntity.ok(
                invitationService.acceptInvitation(invitationId)
        );
    }

    @PostMapping("/invitations/{invitationId}/reject")
    public ResponseEntity<Void> rejectInvitation(
            @PathVariable String invitationId) {

        invitationService.rejectInvitation(invitationId);

        return ResponseEntity.noContent().build();
    }
}
