package com.gachaland.api.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by chris.j3 on 2017. 5. 24..
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/api")
    public String getTestApi( @RequestParam(value = "value", required = false, defaultValue = "") String value ) {
        if (value.isEmpty()) {
            return "OK - HELLO LAND 대박납시다~ ";
        } else {
            return "OK - HELLO LAND : " + value;
        }
    }

}
