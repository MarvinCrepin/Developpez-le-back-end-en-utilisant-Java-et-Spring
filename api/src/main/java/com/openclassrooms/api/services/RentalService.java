package com.openclassrooms.api.services;

import com.openclassrooms.api.dtos.rental.CreateRentalRequestDTO;
import com.openclassrooms.api.entities.Rental;
import com.openclassrooms.api.exceptions.NotFoundException;
import com.openclassrooms.api.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repository;

    public List<Rental> findAll() {
        return repository.findAll();
    }

    public Rental findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Rental not found: " + id));
    }

    public HashMap<String, String> create(CreateRentalRequestDTO dto, Integer ownerId) {
        Rental rental = new Rental(dto, ownerId);
        repository.save(rental);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");
        return response;
    }

    public HashMap<String, String> update(Rental rental) {
        repository.save(rental);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Rental updated !");
        return response;
    }


}
