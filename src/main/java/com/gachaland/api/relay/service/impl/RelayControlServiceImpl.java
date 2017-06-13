package com.gachaland.api.relay.service.impl;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.config.RestClient;
import com.gachaland.api.relay.service.RelayControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chris.j3 on 2017. 5. 26..
 */
@Service
@Slf4j
public class RelayControlServiceImpl implements RelayControlService {

    @Autowired
    private RestClient restClient;

    private final String requestDirectionUrl = "http://wisefamily.byus.net/gacha/direction.php?direction=";

    @Override
    public boolean relayController(String directionString, boolean onoff) {

        Enumerations.DirectionType directionType;
        try {
            directionType = Enumerations.DirectionType.valueOf(directionString.toUpperCase());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        String requsetUrl = makeDirectionRequestURL(directionType, onoff);
        log.warn(">>>> request : " + requsetUrl);

        String result = restClient.getRequest(requsetUrl);
        log.warn(">>>> response : " + result);

        return true;

    }

    private String makeDirectionRequestURL(Enumerations.DirectionType direction, boolean onoff) {
        return requestDirectionUrl + direction.name() + "&move=" + (onoff == true ? "ON" : "OFF");
    }
}
