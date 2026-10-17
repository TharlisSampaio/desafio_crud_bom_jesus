package com.desafiobomjesus.bom_jesus_api.dto;

import java.util.List;

public record TeacherRequest(
        String name,
        String phoneNumber,
        String emailAddress,
        double salary,
        List<AddressRequest> addresses
) {
}
