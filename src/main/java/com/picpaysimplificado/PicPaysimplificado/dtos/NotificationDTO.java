package com.picpaysimplificado.PicPaysimplificado.dtos;

/**
 * Data Transfer Object (DTO) para representar informações de notificação.
 * Utilizado para transportar detalhes de notificação, como endereço de e-mail e mensagem.
 */
public record NotificationDTO(String email, String message) {
    // O uso da palavra-chave 'record' cria automaticamente construtores, getters e toString.
    // Este DTO é imutável, significando que seus valores não podem ser alterados após a criação.
}
