package com.desafiobomjesus.bom_jesus_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public record TeacherRequest(
        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank String emailAddress,
        @NotBlank BigDecimal salary,
        List<AddressRequest> address
) {
}
