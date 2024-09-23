package org.example.patrimoinepapi.controller;

import lombok.AllArgsConstructor;
import org.example.patrimoinepapi.model.Patrimoine;
import org.example.patrimoinepapi.service.PatrimoineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/patrimoines")
@AllArgsConstructor
public class PatrimoineController {

    private PatrimoineService patrimoineService;

    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable String id) throws IOException {
        var patrimoine = patrimoineService.getPatrimoine(id);
        if(patrimoine==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patrimoine, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Patrimoine savePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) {
        return patrimoineService.savePatrimoine(id, patrimoine);
    }
}
