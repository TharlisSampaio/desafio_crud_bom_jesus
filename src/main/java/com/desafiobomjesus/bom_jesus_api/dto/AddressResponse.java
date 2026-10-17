package com.desafiobomjesus.bom_jesus_api.dto;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {}
