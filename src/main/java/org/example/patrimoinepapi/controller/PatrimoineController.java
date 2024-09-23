package org.example.patrimoinepapi.controller;

import lombok.AllArgsConstructor;
import org.example.patrimoinepapi.model.Patrimoine;
import org.example.patrimoinepapi.service.PatrimoineService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/patrimoines")
@AllArgsConstructor
public class PatrimoineController {

    private PatrimoineService patrimoineService;

    @GetMapping("/{id}")
    public Patrimoine getPatrimoine(@PathVariable String id) throws IOException {
        return patrimoineService.getPatrimoine(id);
    }

    @PutMapping("/{id}")
    public Patrimoine savePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) {
        patrimoine.setDerniereModification(LocalDateTime.now());
        return patrimoineService.savePatrimoine(id, patrimoine);
    }
}
