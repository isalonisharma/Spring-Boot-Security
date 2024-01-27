package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AuthenticationRequest;
import com.example.model.AuthenticationResponse;
import com.example.model.RegisterRequest;
import com.example.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/")
public record HomeController(AuthenticationService authenticationService) {
	@PostMapping("register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
		AuthenticationResponse authenticationResponse = null;
		try {
			authenticationResponse = authenticationService.register(request);
		} catch (Exception e) {
			authenticationResponse = new AuthenticationResponse(null, e.getMessage());
		}
		return ResponseEntity.ok(authenticationResponse);
	}

	@PostMapping("authenticate")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}