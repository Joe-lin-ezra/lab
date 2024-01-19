package com.lab.base.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorDTO
{
	String fieldName;

	Object rejectedValue;

	String defaultMessage;
}
