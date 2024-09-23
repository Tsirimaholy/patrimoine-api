package org.example.patrimoinepapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.patrimoinepapi.controller.PatrimoineController;
import org.example.patrimoinepapi.model.Patrimoine;
import org.example.patrimoinepapi.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatrimoineController.class)
public class PatrimoineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatrimoineService patrimoineService;

    @Autowired
    private ObjectMapper objectMapper;

    private Patrimoine patrimoine;

    @BeforeEach
    public void setUp() {
        patrimoine = new Patrimoine();
        patrimoine.setPossesseur("John Doe");
        patrimoine.setDerniereModification(LocalDateTime.now());
    }

    @Test
    public void testGetPatrimoine() throws Exception {
        when(patrimoineService.getPatrimoine(anyString())).thenReturn(patrimoine);

        mockMvc.perform(get("/patrimoines/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patrimoine)));
    }

    @Test
    public void testSavePatrimoine() throws Exception {
        mockMvc.perform(put("/patrimoines/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patrimoine)))
                .andExpect(status().isOk());
    }
}
