package com.picpaysimplificado.PicPaysimplificado.controllers;

import com.picpaysimplificado.PicPaysimplificado.domain.User.User;
import com.picpaysimplificado.PicPaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.PicPaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para lidar com operações relacionadas a usuários.
 */
@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Cria um novo usuário com base nos dados fornecidos.
     *
     * @param user Os dados do usuário a serem criados.
     * @return ResponseEntity contendo o novo usuário e o status HTTP.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Obtém todos os usuários cadastrados.
     *
     * @return ResponseEntity contendo a lista de usuários e o status HTTP.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}