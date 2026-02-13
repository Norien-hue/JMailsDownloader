package com.jmails.api.service;

import com.jmails.api.entity.Jmail;
import com.jmails.api.repository.jmails.JmailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JmailsService {

    @Autowired
    private JmailsRepository jmailsRepository;

    @Autowired
    @Qualifier("jmailsMongoTemplate")
    private MongoTemplate jmailsMongoTemplate;

    public Page<Jmail> listarTodos(int page, int size) {
        return jmailsRepository.findAll(PageRequest.of(page, size));
    }

    public List<Jmail> buscar(String texto) {
        String textoEscapado = texto.replaceAll("([.*+?^${}()|\\[\\]\\\\])", "\\\\$1");

        textoEscapado = textoEscapado.replace("\\", "\\\\");
        textoEscapado = textoEscapado.replace("\"", "\\\"");
        textoEscapado = textoEscapado.replace("'", "\\'");

        String queryStr = String.format(
                "{ $where: \"JSON.stringify(this.json_data).match(/%s/i) != null\" }",
                textoEscapado
        );

        BasicQuery query = new BasicQuery(queryStr);
        return jmailsMongoTemplate.find(query, Jmail.class);
    }

    public long contarTotal() {
        return jmailsRepository.count();
    }
}