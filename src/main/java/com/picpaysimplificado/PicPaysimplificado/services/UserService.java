package com.picpaysimplificado.PicPaysimplificado.services;

import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import com.picpaysimplificado.PicPaysimplificado.domain.User.UserType;
import com.picpaysimplificado.PicPaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.PicPaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Serviço responsável por manipular operações relacionadas aos usuários.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    /**
     * Valida se um usuário pode realizar uma transação com base no tipo de usuário e saldo.
     *
     * @param sender O usuário remetente da transação.
     * @param amount O valor da transação.
     * @throws Exception Se o usuário não estiver autorizado ou se o saldo for insuficiente.
     */
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        // Verifica se o usuário é um lojista (comerciante)
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação");
        }

        // Verifica se o saldo do usuário é suficiente para a transação
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    /**
     * Encontra um usuário pelo ID.
     *
     * @param id O ID do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws Exception Se o usuário não for encontrado.
     */
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    /**
     * Cria um novo usuário com base nos dados fornecidos.
     *
     * @param data Os dados do usuário a serem utilizados para a criação.
     * @return O novo usuário criado.
     */
    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    /**
     * Obtém uma lista de todos os usuários.
     *
     * @return Uma lista de todos os usuários.
     */
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    /**
     * Salva um usuário no banco de dados.
     *
     * @param user O usuário a ser salvo.
     */
    public void saveUser(User user) {
        this.repository.save(user);
    }
}
