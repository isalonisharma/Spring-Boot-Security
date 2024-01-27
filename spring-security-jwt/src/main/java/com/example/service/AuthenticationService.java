package com.example.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.model.AuthenticationRequest;
import com.example.model.AuthenticationResponse;
import com.example.model.AuthenticationUserDetails;
import com.example.model.RegisterRequest;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import com.example.utils.JwtUtility;

@Service
public record AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
		UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,
		AuthenticationManager authenticationManager) {
	public AuthenticationResponse register(RegisterRequest request) throws Exception {
		Optional<User> existingUser = userRepository.findByUsername(request.username());
		if (existingUser.isPresent()) {
			throw new Exception("User already exist with given username.");
		}
		User user = new User(request.username(), passwordEncoder.encode(request.password()));
		User savedUser = userRepository.save(user);
		Role role = roleRepository.findByName(request.role()).orElseThrow();
		UserRole userRole = new UserRole(savedUser, role);
		userRoleRepository.save(userRole);
		AuthenticationUserDetails userDetail = new AuthenticationUserDetails(user, userRole);
		final var token = JwtUtility.generateToken(userDetail);
		return new AuthenticationResponse(token, null);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		User user = userRepository.findByUsername(request.username()).orElseThrow();
		UserRole userRole = userRoleRepository.findByuser(user);
		AuthenticationUserDetails userDetail = new AuthenticationUserDetails(user, userRole);
		final var token = JwtUtility.generateToken(userDetail);
		return new AuthenticationResponse(token, null);
	}
}