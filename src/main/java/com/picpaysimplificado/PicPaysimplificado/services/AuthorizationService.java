package com.picpaysimplificado.PicPaysimplificado.services;

import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Serviço responsável por autorizar transações através de uma API externa.
 */
@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authorizationApi}")
    private String authApiUrl;

    /**
     * Autoriza uma transação para o usuário remetente com um determinado valor.
     *
     * @param sender O usuário remetente da transação.
     * @param value O valor da transação a ser autorizado.
     * @return true se a transação for autorizada, false caso contrário.
     */
    public boolean authorizeTransaction(User sender, BigDecimal value) {
        // Envia uma requisição GET para a API de autorização
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.authApiUrl, Map.class);

        // Verifica se a resposta da API de autorização é bem-sucedida (status OK)
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            // Verifica se a mensagem da API é "Autorizado"
            return "Autorizado".equalsIgnoreCase(message);
        } else {
            // Retorna false se a resposta não for bem-sucedida
            return false;
        }
    }
}
