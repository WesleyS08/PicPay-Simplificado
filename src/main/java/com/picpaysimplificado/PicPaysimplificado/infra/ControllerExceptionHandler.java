package com.picpaysimplificado.PicPaysimplificado.infra;

import com.picpaysimplificado.PicPaysimplificado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controlador global de exceções para lidar com diferentes tipos de erros na aplicação.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Manipula exceções relacionadas a violações de integridade de dados, como duplicidade.
     *
     * @param exception A exceção a ser manipulada.
     * @return ResponseEntity com informações de exceção e status 400 (Bad Request).
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    /**
     * Manipula exceções relacionadas a entidades não encontradas.
     *
     * @param exception A exceção a ser manipulada.
     * @return ResponseEntity com status 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFound(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    /**
     * Manipula exceções gerais que não foram tratadas de outra forma.
     *
     * @param exception A exceção a ser manipulada.
     * @return ResponseEntity com informações de exceção e status 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
