package com.picpaysimplificado.PicPaysimplificado.dtos;

import com.picpaysimplificado.PicPaysimplificado.domain.User.UserType;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) para representar informações de usuário.
 * Utilizado para transportar dados do usuário entre diferentes camadas da aplicação.
 */
public record UserDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType
) {
    // O uso da palavra-chave 'record' cria automaticamente construtores, getters e toString.
    // Este DTO é imutável, significando que seus valores não podem ser alterados após a criação.
}
