package com.lab.base.controller;

import com.lab.base.dto.sso.request.LoginDTO;
import com.lab.base.dto.sso.request.LogoutDTO;
import com.lab.base.dto.sso.request.RegisterDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class Auth
{
	@PostMapping("login")
	public Mono<Object> login( @RequestBody @Validated LoginDTO loginDTO ) throws Exception {


	}

	@PostMapping("logout")
	public Mono<Object> logout( @RequestBody @Validated LogoutDTO logoutDTO ) throws Exception {


	}

	@PostMapping("register")
	public Mono<Object> register( @RequestBody @Validated RegisterDTO registerDTO ) throws Exception {

	}
}
