package com.gachaland.api.common.model;

import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberToken;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserSession implements Serializable {

    private boolean valid;

    private Member member;

    private MemberToken token;

}
