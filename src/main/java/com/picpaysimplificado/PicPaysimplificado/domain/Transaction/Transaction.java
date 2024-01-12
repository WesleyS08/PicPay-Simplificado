package com.picpaysimplificado.PicPaysimplificado.domain.Transaction;

import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade que representa uma transação no sistema.
 */
@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Valor da transação

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // Usuário remetente da transação

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver; // Usuário destinatário da transação

    private LocalDateTime timestamp; // Data e hora em que a transação foi realizada
}
