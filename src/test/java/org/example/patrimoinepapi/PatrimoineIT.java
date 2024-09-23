package org.example.patrimoinepapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.patrimoinepapi.model.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class PatrimoineIT {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private Patrimoine patrimoine;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        patrimoine = new Patrimoine();
        patrimoine.setPossesseur("John Doe");
        patrimoine.setDerniereModification(LocalDateTime.now());
    }

    @Test
    public void testGetPatrimoine() throws Exception {
        mockMvc.perform(get("/patrimoines/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testGetPatrimoine_ko() throws Exception {
        mockMvc.perform(get("/patrimoines/{id}", "100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
        ;
    }

    @Test
    public void testSavePatrimoine() throws Exception {
        mockMvc.perform(put("/patrimoines/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patrimoine)))
                .andExpect(status().isOk());
    }

}
