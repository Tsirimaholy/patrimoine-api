package org.example.patrimoinepapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Patrimoine implements Serializable {
    private String possesseur;
    private LocalDateTime derniereModification;
}
