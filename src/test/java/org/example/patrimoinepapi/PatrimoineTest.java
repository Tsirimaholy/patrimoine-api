package org.example.patrimoinepapi;

import org.example.patrimoinepapi.model.Patrimoine;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatrimoineTest {

    @Test
    public void testGettersAndSetters() {
        Patrimoine patrimoine = new Patrimoine();
        patrimoine.setPossesseur("John Doe");
        patrimoine.setDerniereModification(LocalDateTime.of(2023, 10, 1, 12, 0));

        assertEquals("John Doe", patrimoine.getPossesseur());
        assertEquals(LocalDateTime.of(2023, 10, 1, 12, 0), patrimoine.getDerniereModification());
    }

    @Test
    public void testToString() {
        Patrimoine patrimoine = new Patrimoine();
        patrimoine.setPossesseur("John Doe");
        patrimoine.setDerniereModification(LocalDateTime.of(2023, 10, 1, 12, 0));

        String expected = "Patrimoine(possesseur=John Doe, derniereModification=2023-10-01T12:00)";
        assertEquals(expected, patrimoine.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Patrimoine patrimoine1 = new Patrimoine();
        patrimoine1.setPossesseur("John Doe");
        patrimoine1.setDerniereModification(LocalDateTime.of(2023, 10, 1, 12, 0));

        Patrimoine patrimoine2 = new Patrimoine();
        patrimoine2.setPossesseur("John Doe");
        patrimoine2.setDerniereModification(LocalDateTime.of(2023, 10, 1, 12, 0));

        Patrimoine patrimoine3 = new Patrimoine();
        patrimoine3.setPossesseur("Jane Doe");
        patrimoine3.setDerniereModification(LocalDateTime.of(2023, 10, 1, 12, 0));

        assertEquals(patrimoine1, patrimoine2);
        assertNotEquals(patrimoine1, patrimoine3);
        assertEquals(patrimoine1.hashCode(), patrimoine2.hashCode());
        assertNotEquals(patrimoine1.hashCode(), patrimoine3.hashCode());
    }
}