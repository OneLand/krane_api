package com.gachaland.api.common.model;

import com.gachaland.api.common.constants.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jboss.logging.MDC;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
public class ErrorHeader extends StandardHeader {
    private String userRequestId;

    public ErrorHeader(int code, String message) {
        super(code, message);
        this.userRequestId = UUID.randomUUID().toString();
		MDC.put(Constants.MDC_REQUEST_ID, this.userRequestId);
	}

}
