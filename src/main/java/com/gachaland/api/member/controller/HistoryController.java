package com.gachaland.api.member.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.member.dto.model.MemberHistoryDTO;
import com.gachaland.api.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
@RestController
@RequestMapping("/logging")
@Api(description = "멤버 History 정보", tags = "Logging")
public class HistoryController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "유저 행위 정보 로깅", notes = "멤버 로깅")
    @RequestMapping(method = RequestMethod.POST, value = "/user/{memberId}")
    public StandardResponse loggingMemberActions(
            @ApiParam(value = "memberId  ", required = true) @PathVariable(required = true) long memberId,
            @ApiParam(value = "Action : LOGIN,START,PURCHASE,EXCHANGE,END", required = true) @RequestBody(required = true) String action) {

        memberService.loggingMemberHistory(memberId, action);
        return new StandardResponse(ResultCode.OK.getCode(), "SUCCESS");
    }

    @ApiOperation(value = "유저 행위 정보 로깅 조회", notes = "멤버 로깅 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/user/{memberId}")
    public StandardResponse getLoggingMemberActions(
            @ApiParam(value = "memberId  ", required = true) @PathVariable(required = true) long memberId) {

        MemberHistoryDTO historyDTO = memberService.getLoggingMemberHistory(memberId);
        return new StandardResponse(ResultCode.OK.getCode(), "SUCCESS", historyDTO);
    }

}
