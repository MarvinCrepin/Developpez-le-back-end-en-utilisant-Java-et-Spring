package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dtos.auth.AuthRequestDTO;
import com.openclassrooms.api.dtos.auth.AuthResponseDTO;
import com.openclassrooms.api.dtos.user.GetUserResponseDTO;
import com.openclassrooms.api.dtos.auth.RegisterRequestDTO;
import com.openclassrooms.api.entities.User;
import com.openclassrooms.api.services.AuthService;
import com.openclassrooms.api.services.JwtService;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}

	@GetMapping("/me")
	@SecurityRequirement(name = "Bearer Authentication")
	public GetUserResponseDTO me() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final User user = userService.findByEmail(auth.getName());
		return new GetUserResponseDTO(user);
	}
}
