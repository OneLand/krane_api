package com.gachaland.api.controller;

import com.gachaland.api.service.RelayControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chris.j3 on 2017. 5. 26..
 */
@RestController
@RequestMapping("/control")
@Api(description = "방향키 Relay", tags = "Joystick")
public class DirectionController {

    @Autowired
    private RelayControlService relayControlService;

    @ApiOperation(value = "방향키 릴레이", notes = "방향키 릴레이 API")
    @RequestMapping(method = RequestMethod.GET, value = "/direction")
    public String controlDirectionAPI( @ApiParam(name="value", required = true, value="UP/DOWN/LEFT/RIGHT") @RequestParam(value = "value", required = true, defaultValue = "") String value,
                                       @ApiParam(name="move", required = true, value="true/false") @RequestParam(value = "move", required = true, defaultValue = "") Boolean onOff) {

        boolean result = relayControlService.relayController(value, onOff);
        return (result ? "SUCCESS" : "FAIL");
    }

}
