package com.gachaland.api.common.model;

import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberToken;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by jhpark1220 on 2017. 7. 5..
 */
@Data
public class UserSession implements Serializable {

    private boolean valid;

    private Member member;

    private MemberToken token;

}
