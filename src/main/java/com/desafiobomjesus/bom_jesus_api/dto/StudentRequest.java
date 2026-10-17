package com.desafiobomjesus.bom_jesus_api.dto;

import java.util.List;

public record StudentRequest(
        String name,
        String phoneNumber,
        String emailAddress,
        String studentNumber,
        byte[] photo, // no JSON vira texto em Base64
        List<AddressRequest> address
) {
}
