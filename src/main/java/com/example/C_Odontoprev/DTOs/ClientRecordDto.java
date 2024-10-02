package com.example.C_Odontoprev.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDto(@NotBlank String name, @NotNull String CPF, @NotNull String CEP, @NotNull String primeiroTratamento) {

}
