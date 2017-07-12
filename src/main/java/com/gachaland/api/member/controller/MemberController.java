package com.gachaland.api.member.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.Constants;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.model.UserSession;
import com.gachaland.api.member.dao.model.MemberToken;
import com.gachaland.api.member.dto.model.LoginDTO;
import com.gachaland.api.member.dto.model.MemberDTO;
import com.gachaland.api.member.dto.model.RegisterBody;
import com.gachaland.api.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jhpark1220 on 2017. 6. 7..
 */
@Slf4j
@RestController
@RequestMapping("/member")
@Api(description = "멤버정보", tags = "MemberInfo")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "게스트 멤버 가입하기 (전화번호)", notes = "멤버 가입")
    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StandardResponse registerGuestMember(
            @ApiParam(name="body", value = "가입 정보 JSON", required = true) @RequestBody RegisterBody registerBody) {

        MemberToken memberToken = memberService.registerGuestMember(registerBody);

        if (memberToken == null) {
            return new StandardResponse(ResultCode.UNAUTHORIZED);
        }
        else {
            log.info(">>>> register member_id {} ", memberToken.getMemberId());
            LoginDTO loginDTO = new LoginDTO(memberToken.getMemberId(), memberToken.getToken());
            return new StandardResponse(ResultCode.OK, loginDTO);
        }
    }

    @ApiOperation(value = "로그인 하기", notes = "로그인")
    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StandardResponse loginMember(@RequestAttribute(Constants.REQ_ATTR_USER) UserSession session,
                                        @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN) String userToken,
                                        @RequestHeader(value=Constants.REQ_ATTR_USER_ID) String userId,
                                        @RequestHeader(value=Constants.REQ__DEBUG_USER_ID_PARAM) String headerStr,
        @ApiParam(name="body", value = "가입 정보 JSON", required = true) @RequestBody RegisterBody registerBody) {

        log.info("header > " + headerStr);
        log.info("userToken > " + userToken);
        log.info("userId > " + userId);
        log.info("session > " + session.toString());
        log.info("body > " + registerBody.toString());

        if (memberService.memberLogin(session, registerBody) == true) {
            LoginDTO loginDTO = new LoginDTO(session.getMember().getId(), session.getToken().getToken());
            return new StandardResponse(ResultCode.OK, loginDTO);
        }
        else
            return new StandardResponse(ResultCode.UNAUTHORIZED);
    }

    @ApiOperation(value = "Member ID로 정보 조회", notes = "멤버 Id로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/{memberId}")
    public StandardResponse getMemberInfoById(@ApiParam(value = "memberId", required = true) @PathVariable long memberId) {
        MemberDTO member = memberService.getMemberInfo(memberId);
        return new StandardResponse(ResultCode.OK.getCode(), "SUCCESS", member);
    }

    @ApiOperation(value = "전화번호로 멤버 정보 조회", notes = "전화번호로 멤버 조회 ")
    @RequestMapping(method = RequestMethod.GET, value = "/phone")
    public StandardResponse getMemberInfoByPhoneNum(
        @ApiParam(name="num", required = true, value="0") @RequestParam(value = "num", required = true, defaultValue = "") String phoneNumber) {

        MemberDTO member = memberService.getMemberInfo(phoneNumber);
        return new StandardResponse(ResultCode.OK.getCode(), "SUCCESS", member);
    }

}
