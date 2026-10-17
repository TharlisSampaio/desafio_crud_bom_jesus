package com.desafiobomjesus.bom_jesus_api.dto;

import java.util.List;

public record StudentResponse(
        Long id,
        String name,
        String phoneNumber,
        String emailAddress,
        String studentNumber,
        byte[] photo, // no JSON vira texto em Base64
        List<AddressRequest> addresses
) {
}
