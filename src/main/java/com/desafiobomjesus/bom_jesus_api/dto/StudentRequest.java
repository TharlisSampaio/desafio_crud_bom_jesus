package com.desafiobomjesus.bom_jesus_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record StudentRequest(
        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank String emailAddress,
        @NotBlank String studentNumber,
        byte[] photo, // no JSON vira texto em Base64
        List<AddressRequest> address
) {
}
