package com.laudado.talentforgeaibackend.services;

import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyInvitationResponse;
import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyMembershipResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {
    public List<CompanyInvitationResponse> invitationList(String companyId) {
        return null;
    }

    public CompanyInvitationResponse inviteRecruiter(String companyId, String invitationId) {
        return null;
    }

    public void cancelInvitation(String companyId, String invitationId) {
    }

    public CompanyMembershipResponse acceptInvitation(String invitationId) {
        return null;
    }

    public void rejectInvitation(String invitationId) {
    }
}