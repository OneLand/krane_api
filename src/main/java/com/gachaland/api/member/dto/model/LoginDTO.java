package com.gachaland.api.member.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private long memberId;

    private String accessToken;

    private MemberDTO member;

}
