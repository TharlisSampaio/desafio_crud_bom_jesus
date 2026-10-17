package com.desafiobomjesus.bom_jesus_api.dto;

public record AddressRequest(
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {}
