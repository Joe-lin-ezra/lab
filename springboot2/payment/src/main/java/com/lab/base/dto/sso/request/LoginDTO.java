package com.lab.base.dto.sso.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO
{
	@NotBlank
	public String username;

	@NotBlank
	public String password;
}
