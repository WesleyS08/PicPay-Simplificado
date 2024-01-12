package com.picpaysimplificado.PicPaysimplificado.repositories;
import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import com.picpaysimplificado.PicPaysimplificado.domain.User.UserType;
import com.picpaysimplificado.PicPaysimplificado.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe UserRepository.
 */
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    /**
     * Deve obter um usuário com sucesso do banco de dados.
     */
    @Test
    @DisplayName("Deve obter um usuário com sucesso do banco de dados")
    void findUserByDocumentCase1() {
        // cadastra um novo usuario no banco de dados que está na memoria
        String documento = "99999999901";
        UserDTO dados = new UserDTO("wesley", "Teste", documento, new BigDecimal(10), "test@gmail.com", "44444", UserType.COMMON);
        this.criarUsuario(dados);

        // busca o documento
        Optional<User> resultado = this.userRepository.findUserByDocument(documento);

        // retorna se achou o documento
        assertThat(resultado.isPresent()).isTrue();
    }

    /**
     * Não deve obter um usuário do banco de dados quando o usuário não existe.
     */
    @Test
    @DisplayName("Não deve obter um usuário do banco de dados quando o usuário não existe")
    void findUserByDocumentCase2() {
        // documento que vai ser buscado
        String documento = "99999999901";

        // busca o documento
        Optional<User> resultado = this.userRepository.findUserByDocument(documento);

        // e retorna se ele está vazio
        assertThat(resultado.isEmpty()).isTrue();
    }

    /**
     * Método auxiliar para criar uma entidade de usuário e persisti-la no banco de dados de teste.
     *
     * @param dados O UserDTO contendo as informações do usuário.
     * @return A entidade de usuário criada.
     */
    private User criarUsuario(UserDTO dados) {
        User novoUsuario = new User(dados);
        this.entityManager.persist(novoUsuario);
        return novoUsuario;
    }
}
