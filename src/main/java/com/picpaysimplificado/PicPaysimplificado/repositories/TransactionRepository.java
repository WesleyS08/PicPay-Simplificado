package com.picpaysimplificado.PicPaysimplificado.repositories;

import com.picpaysimplificado.PicPaysimplificado.domain.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório Spring Data JPA para a entidade Transaction.
 * Permite realizar operações de persistência relacionadas a transações no banco de dados.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // O Spring Data JPA fornece métodos CRUD básicos, que são herdados desta interface.
    // Não é necessário escrever a implementação, pois o Spring Data JPA cria automaticamente
    // consultas com base nos nomes dos métodos, como findByXXX, save, delete, etc.
}
