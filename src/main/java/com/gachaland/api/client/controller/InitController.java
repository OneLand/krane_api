package com.gachaland.api.client.controller;

import com.gachaland.api.client.service.AppInfoService;
import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
@RestController
@RequestMapping("/init")
@Api(description = "클라이언트 초기 정보 요청", tags = "Init")
public class InitController {

    @Autowired
    private AppInfoService appInfoService;

    @ApiOperation(value = "초기 정보 요청하기", notes = "실행 환경 배너 정보 요청")
    @RequestMapping(method = RequestMethod.GET, value = "/env")
    public StandardResponse initializeClient() {
        return new StandardResponse(ResultCode.OK.getCode(), "SUCCESS", appInfoService.getCurrentAppInfo());
    }

}

