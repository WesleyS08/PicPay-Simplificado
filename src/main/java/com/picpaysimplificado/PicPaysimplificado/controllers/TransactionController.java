package com.picpaysimplificado.PicPaysimplificado.controllers;

import com.picpaysimplificado.PicPaysimplificado.domain.Transaction.Transaction;
import com.picpaysimplificado.PicPaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.PicPaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para lidar com operações relacionadas a transações.
 */

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * Cria uma nova transação com base nos dados fornecidos.
     *
     * @param transaction Os dados da transação a serem processados.
     * @return ResponseEntity contendo a nova transação e o status HTTP.
     * @throws Exception Se ocorrer um erro durante o processamento da transação.
     */
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}