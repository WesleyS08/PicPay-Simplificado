package com.picpaysimplificado.PicPaysimplificado.dtos;

/**
 * Data Transfer Object (DTO) para representar informações de exceção.
 * Utilizado para transportar mensagens de erro e códigos de status.
 */
public record ExceptionDTO(String message, String statusCode) {
    // O uso da palavra-chave 'record' cria automaticamente construtores, getters e toString.
    // Este DTO é imutável, significando que seus valores não podem ser alterados após a criação.
}
