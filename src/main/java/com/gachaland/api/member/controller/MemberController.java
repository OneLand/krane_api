package com.gachaland.api.member.controller;

import com.gachaland.api.member.dto.model.MemberDTO;
import com.gachaland.api.member.dto.model.RegisterBody;
import com.gachaland.api.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String registerGuestMember(
            @ApiParam(name="body", value = "가입 정보 JSON", required = true) @RequestBody RegisterBody registerBody) {
        boolean result = memberService.registerGuestMember(registerBody.getMemberType(), registerBody.getPhoneNumber());
        return (result?"SUCCESS":"FALSE");
    }

    @ApiOperation(value = "Member ID로 정보 조회", notes = "멤버 Id로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/{memberId}")
    public MemberDTO getMemberInfoById(@ApiParam(value = "memberId", required = true) @PathVariable long memberId) {
        MemberDTO member = memberService.getMemberInfo(memberId);
        return member;
    }

    @ApiOperation(value = "전화번호로 멤버 정보 조회", notes = "전화번호로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/phone")
    public MemberDTO getMemberInfoByPhoneNum(
        @ApiParam(name="num", required = true, value="0") @RequestParam(value = "num", required = true, defaultValue = "") String phoneNumber) {

        MemberDTO member = memberService.getMemberInfo(phoneNumber);
        return member;
    }

}
