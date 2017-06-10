package com.gachaland.api.member.controller;

import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jhpark1220 on 2017. 6. 7..
 */
@RestController
@RequestMapping("/member")
@Api(description = "멤버정보", tags = "MemberInfo")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "게스트 멤버 가입하기 (전화번호)", notes = "멤버 가입")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Member registerGuestMember(
            @ApiParam(value = "type : android에서 폰번호를 가져올 수 있는 경우엔 MEMBER(정회원)로 받아야 함.", required = true) @RequestBody(required = true) String memberType,
            @ApiParam(value = "phoneNumber", required = true) @RequestBody(required = true) String phoneNumber) {
        boolean result = memberService.registerGuestMember(memberType, phoneNumber);

        Member member = null;
        if (result == true) {
            member = memberService.getMemberInfo(phoneNumber);
        }
        return member;
    }

    @ApiOperation(value = "Member ID로 정보 조회", notes = "멤버 Id로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/{memberId}")
    public Member getMemberInfoById( @ApiParam(value = "memberId", required = true) @PathVariable long memberId) {
        Member member = memberService.getMemberInfo(memberId);
        return member;
    }

    @ApiOperation(value = "전화번호로 멤버 정보 조회", notes = "전화번호로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/phone")
    public Member getMemberInfoByPhoneNum(
        @ApiParam(name="num", required = true, value="0") @RequestParam(value = "num", required = true, defaultValue = "") String phoneNumber) {

        Member member = memberService.getMemberInfo(phoneNumber);
        return member;
    }

}
