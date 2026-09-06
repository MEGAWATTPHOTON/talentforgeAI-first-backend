package com.laudado.talentforgeaibackend.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMember {
    private String id;
    private String userId;
    private String companyId;
    public enum Role{
        OWNER("owner"),
        ADMIN("admin"),
        MEMBER("member");

        private final String value;

        Role(String value){
            this.value=value;
        }
        @JsonValue
        public String getValue(){
            return value;
        }
    }
    private Role role;
    private String joinedAt;
}
