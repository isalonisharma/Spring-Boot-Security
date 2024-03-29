package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include. NON_NULL) 
public record AuthenticationResponse(String token, String errorMessage) {
	
}