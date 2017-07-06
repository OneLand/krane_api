package com.gachaland.api.common.exception;

import com.gachaland.api.common.constants.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class BindingResultHandler {
	public void checkBindingError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorMessage.append(((FieldError) error).getField()).append(" : ")
					.append(error.getDefaultMessage()).append("\n");
			}
			throw new RuntimeExceptionBase(ResultCode.INVALID_INPUT, "요청 입력값 오류 - " + errorMessage.toString(), bindingResult.getAllErrors());
		}
	}
}
