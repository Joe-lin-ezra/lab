package com.lab.base.dto.exception;

import com.lab.base.dto.response.ValidationErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodArgumentErrorResponseDTO
{
	private HttpStatus status;
	private String message;
	private List<ValidationErrorDTO> errors;
}
