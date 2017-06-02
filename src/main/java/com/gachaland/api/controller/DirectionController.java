package com.gachaland.api.controller;

import com.gachaland.api.service.RelayControlService;
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
public class DirectionController {

    @Autowired
    private RelayControlService relayControlService;

    @RequestMapping(method = RequestMethod.GET, value = "/direction")
    public String controlDirectionAPI( @RequestParam(value = "value", required = true, defaultValue = "") String value,
                                       @RequestParam(value = "move", required = true, defaultValue = "") Boolean onOff) {

        boolean result = relayControlService.relayController(value, onOff);
        return (result ? "SUCCESS" : "FAIL");
    }

}
