package com.picpaysimplificado.PicPaysimplificado.repositories;

import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de repositório Spring Data JPA para a entidade User.
 * Permite realizar operações de persistência relacionadas a usuários no banco de dados.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Procura um usuário pelo número de documento.
     *
     * @param document O número de documento do usuário a ser pesquisado.
     * @return Um objeto Optional contendo o usuário, se encontrado.
     */
    Optional<User> findUserByDocument(String document);

    /**
     * Procura um usuário pelo ID.
     *
     * @param id O ID do usuário a ser pesquisado.
     * @return Um objeto Optional contendo o usuário, se encontrado.
     */
    Optional<User> findUserById(Long id);
}
