package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

	/* User Creation & Authentication */
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		/* InMemory */
		UserDetails admin = User.withUsername("user")
				.password(encoder.encode("user"))
				.roles("USER")
				.authorities("USER")
				.build();

		UserDetails user = User.withUsername("admin")
				.password(encoder.encode("admin"))
				.roles("USER", "ADMIN")
				.authorities("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	/* Authorization */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
        .authorizeHttpRequests(
        	authorizeRequests -> authorizeRequests
        	.requestMatchers("/admin").hasAuthority("ADMIN")
	        .requestMatchers("/user").hasAnyAuthority("ADMIN", "USER")
	        .requestMatchers("/").permitAll()
         )
        .formLogin(withDefaults())
        .build();
	}

	/* Password Encoding */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}