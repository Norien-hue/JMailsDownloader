package com.jmails.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "emails")
public class Jmail {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nombre;

    @Field("json_data")
    private Map<String, Object> jsonData;

    public Jmail() {
    }

    public Jmail(String nombre, Map<String, Object> jsonData) {
        this.nombre = nombre;
        this.jsonData = jsonData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }
}