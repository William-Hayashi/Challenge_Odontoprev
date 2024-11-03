package com.example.C_Odontoprev.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRecordDto(@NotNull Integer numero, @NotNull String ponto_referencia, @NotNull String bairro, @NotNull String rua) {
}
