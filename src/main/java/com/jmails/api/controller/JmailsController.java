package com.jmails.api.controller;

import com.jmails.api.entity.Jmail;
import com.jmails.api.service.JmailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jmails")
public class JmailsController {

    @Autowired
    private JmailsService jmailsService;

    /**
     * Listar todos con paginación.
     * GET /api/jmails?page=0&size=20
     */
    @GetMapping
    public ResponseEntity<Page<Jmail>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(jmailsService.listarTodos(page, size));
    }

     //Buscar texto dentro de json_data (convierte a string y aplica regex).
     //api/jmails/buscar?texto=hello
    @GetMapping("/buscar")
    public ResponseEntity<List<Jmail>> buscar(@RequestParam String texto) {
        return ResponseEntity.ok(jmailsService.buscar(texto));
    }

     //Contar total de correo

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> contarTotal() {
        return ResponseEntity.ok(Map.of("total", jmailsService.contarTotal()));
    }
}