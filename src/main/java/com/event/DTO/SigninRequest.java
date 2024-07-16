package com.event.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    private String password;

    @NotNull
    @NotBlank
    @NotEmpty
    private String role;
}