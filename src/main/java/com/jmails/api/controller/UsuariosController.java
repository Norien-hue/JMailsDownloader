package com.jmails.api.controller;

import com.jmails.api.entity.Usuario;
import com.jmails.api.security.JwtUtil;
import com.jmails.api.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        try {
            Usuario usuario = usuariosService.registrar(
                    body.get("username"),
                    body.get("password")
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "mensaje", "Usuario creado correctamente",
                    "username", usuario.getUsername()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            Usuario usuario = usuariosService.login(
                    body.get("username"),
                    body.get("password")
            );
            String token = jwtUtil.generarToken(usuario.getUsername());
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "username", usuario.getUsername()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }
}