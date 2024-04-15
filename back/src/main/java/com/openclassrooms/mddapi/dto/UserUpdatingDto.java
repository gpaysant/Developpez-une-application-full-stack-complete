package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdatingDto {

    private Long id;
    private String username;
    @Email
    private String email;
}
