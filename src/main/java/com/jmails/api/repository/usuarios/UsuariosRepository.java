package com.jmails.api.repository.usuarios;

import com.jmails.api.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuariosRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);
}