package com.gachaland.api.member.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jhpark1220 on 2017. 7. 5..
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private long memberId;
    private String accessToken;

}
