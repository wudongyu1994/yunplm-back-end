package com.wdy.yunplm.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1")
@CrossOrigin()
@Slf4j
public class BaseApiController {
	protected void showBinding(BindingResult bindingResult) {
		bindingResult.getAllErrors().forEach(o -> {
			FieldError error = (FieldError) o;
			log.info(error.getField() + ":" + error.getDefaultMessage());
		});
	}
}
