package org.example.patrimoinepapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.example.patrimoinepapi.model.Patrimoine;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PatrimoineService {
    private static final String DATA_DIR = "data";
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    public PatrimoineService() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public Patrimoine getPatrimoine(String id) {
        try {
            File file = new File(DATA_DIR, id + ".json");
            if (file.exists()) {
                return objectMapper.readValue(file, Patrimoine.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void savePatrimoine(String id, Patrimoine patrimoine) {
        try {
            patrimoine.setDerniereModification(LocalDateTime.now());
            objectMapper.writeValue(new File(DATA_DIR, id + ".json"), patrimoine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
