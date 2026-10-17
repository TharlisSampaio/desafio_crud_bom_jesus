package com.desafiobomjesus.bom_jesus_api.mapper;

import com.desafiobomjesus.bom_jesus_api.dto.AddressRequest;
import com.desafiobomjesus.bom_jesus_api.dto.AddressResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private AddressMapper() {}

    public static Address toEntity(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        address.setZipCode(addressRequest.zipCode());
        address.setCountry(addressRequest.country());
        return address;
    }

    public static AddressResponse toAddressResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
        );
    }
}
/*
@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail()
        );
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(dto.id());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        return usuario;
    }
}
 */