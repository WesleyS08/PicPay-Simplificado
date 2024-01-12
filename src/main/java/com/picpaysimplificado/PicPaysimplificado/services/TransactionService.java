package com.picpaysimplificado.PicPaysimplificado.services;

import com.picpaysimplificado.PicPaysimplificado.domain.Transaction.Transaction;
import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import com.picpaysimplificado.PicPaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.PicPaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Serviço responsável por lidar com a criação de transações.
 */
@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AuthorizationService authService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Cria uma nova transação com base nos detalhes fornecidos.
     *
     * @param transaction Os detalhes da transação a serem criados.
     * @return A transação recém-criada.
     * @throws Exception Se a transação não puder ser autorizada ou se houver validação de usuário falhada.
     */
    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        // Obtém os usuários remetente e destinatário
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        // Valida a transação do usuário remetente
        userService.validateTransaction(sender, transaction.value());

        // Autoriza a transação
        boolean isAuthorized = this.authService.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        // Cria a transação
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        // Atualiza os saldos dos usuários
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        // Salva a transação e atualiza os usuários no banco de dados
        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        // Envia notificações aos usuários
        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }
}
