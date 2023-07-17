package com.lab.base.dto.sso.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BasicMessage
{
	private String type;

	private String token;

	public LoginResponse( Boolean result, String message, String type, String token )
	{
		super( result, message );
		this.type = type;
		this.token = token;
	}
}
