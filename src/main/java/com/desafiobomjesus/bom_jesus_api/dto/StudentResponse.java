package com.desafiobomjesus.bom_jesus_api.dto;

import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;

import java.util.List;

public record StudentResponse(
        Long id,
        String name,
        String phoneNumber,
        String emailAddress,
        String studentNumber,
        Status status,
        byte[] photo, // no JSON vira texto em Base64
        List<AddressResponse> addresses
) {
}
