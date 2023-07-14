package com.openclassrooms.api.dtos.user;

import com.openclassrooms.api.entities.User;

import java.time.LocalDateTime;

public class GetUserResponseDTO {

    private String email;
    private String name;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Integer id;

    public GetUserResponseDTO(User user) {
        super();
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updatedAt) {
        this.updated_at = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
