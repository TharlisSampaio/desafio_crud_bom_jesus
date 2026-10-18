package com.desafiobomjesus.bom_jesus_api.dto;

import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;

public record TeacherResponse(
        Long id,
        String name,
        String phoneNumber,
        String emailAddress,
        Status status,
        BigDecimal salary,
        List<AddressResponse> address
) {
}
