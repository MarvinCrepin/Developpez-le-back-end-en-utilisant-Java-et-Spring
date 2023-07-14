package com.openclassrooms.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.dtos.rental.CreateRentalRequestDTO;
import com.openclassrooms.api.dtos.rental.RentalDTO;
import com.openclassrooms.api.entities.Rental;
import com.openclassrooms.api.services.RentalService;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;

	@Autowired
	private UserService userService;


	@PostMapping("")
	public ResponseEntity<HashMap<String, String>> create(@RequestBody CreateRentalRequestDTO dto, Authentication authentication) {
		final String email = authentication.getName();
		final Integer ownerId = userService.findByEmail(email).getId();
		return ResponseEntity.ok(rentalService.create(dto, ownerId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RentalDTO> findById(@PathVariable Integer id) {
		final Rental rental = rentalService.findById(id);
		return ResponseEntity.ok(new RentalDTO(rental));
	}

	@GetMapping("")
	public ResponseEntity<Map<String, List<RentalDTO>>> findAll() {
		List<Rental> rentals = new ArrayList<>(rentalService.findAll());
		Map<String, List<RentalDTO>> result = new HashMap<>();
		final List<RentalDTO> dtos = rentals.stream()
				.map(RentalDTO::new)
				.collect(Collectors.toList());
		result.put("rentals", dtos);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HashMap<String, String>> update(@PathVariable Integer id, @ModelAttribute CreateRentalRequestDTO dto, Authentication authentication) {
		final Rental rental = rentalService.findById(id);
		final Integer ownerId = userService.findByEmail(authentication.getName()).getId();
		if(!Objects.equals(rental.getOwner_id(), ownerId)) {
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.UNAUTHORIZED);
		}
		dto.setPicture(rental.getPicture());
		ObjectMapper objectMapper = new ObjectMapper();
		Rental mergedEntity = objectMapper.convertValue(dto, Rental.class);
		mergedEntity.setId(rental.getId());
		mergedEntity.setOwner_id(rental.getOwner_id());
		return ResponseEntity.ok(rentalService.update(mergedEntity));
	}
}
