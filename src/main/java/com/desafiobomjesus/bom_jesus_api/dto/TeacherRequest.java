package com.desafiobomjesus.bom_jesus_api.dto;

import java.math.BigDecimal;
import java.util.List;

public record TeacherRequest(
        String name,
        String phoneNumber,
        String emailAddress,
        BigDecimal salary,
        List<AddressRequest> addresses
) {
}
