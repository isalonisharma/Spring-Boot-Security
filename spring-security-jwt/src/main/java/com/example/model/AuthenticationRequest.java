package com.example.model;

public record AuthenticationRequest(String username, String password, String role) {
}