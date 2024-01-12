package com.picpaysimplificado.PicPaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuração da aplicação.
 */
@Configuration
public class AppConfig {

    /**
     * Cria um bean RestTemplate.
     *
     * @return Uma instância de RestTemplate configurada.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
