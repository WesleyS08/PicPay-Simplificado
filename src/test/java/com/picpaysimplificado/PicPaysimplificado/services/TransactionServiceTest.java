package com.picpaysimplificado.PicPaysimplificado.services;


import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import com.picpaysimplificado.PicPaysimplificado.domain.User.UserType;
import com.picpaysimplificado.PicPaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.PicPaysimplificado.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe TransactionService.
 * Utiliza mocks para simular o comportamento de componentes externos.
 */
class TransactionServiceTest {

    // Mocks são utilizados para simular o comportamento de objetos reais.
    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository repository;

    @Mock
    private AuthorizationService authService;

    @Mock
    private NotificationService notificationService;

    // As instâncias de mocks são injetadas na classe sob teste.
    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testa a criação de uma transação bem-sucedida quando tudo está correto.
     *
     * @throws Exception Se ocorrer um erro durante a execução do teste.
     */
    @Test
    @DisplayName("Deve criar uma transação com sucesso quando tudo estiver correto")
    void createTransactionCase1() throws Exception {
        // Arrange
        User sender = new User(1L, "Maria", "Souza", "99999999901", "maria@gmail.com", "12345", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Joao", "Souza", "99999999902", "joao@gmail.com", "12345", new BigDecimal(10), UserType.COMMON);

        // Configuração dos mocks para simular o comportamento esperado.
        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        // Act
        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        // Assert
        // Verifica se os métodos esperados foram chamados corretamente.
        verify(repository, times(1)).save(any());
        verify(userService, times(1)).saveUser(sender);
        verify(userService, times(1)).saveUser(receiver);
        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso");
    }

    /**
     * Testa se uma exceção é lançada quando a transação não é autorizada.
     *
     * @throws Exception Se ocorrer um erro durante a execução do teste.
     */
    @Test
    @DisplayName("Deve lançar uma exceção quando a transação não é autorizada")
    void createTransactionCase2() throws Exception {
        // Arrange
        User sender = new User(1L, "Maria", "Souza", "99999999901", "maria@gmail.com", "12345", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Joao", "Souza", "99999999902", "joao@gmail.com", "12345", new BigDecimal(10), UserType.COMMON);

        // Configuração dos mocks para simular o comportamento de transação não autorizada.
        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);
        when(authService.authorizeTransaction(any(), any())).thenReturn(false);

        // Act & Assert
        // Verifica se uma exceção específica é lançada.
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        // Verifica a mensagem da exceção.
        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());
    }
}
