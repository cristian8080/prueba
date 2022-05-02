package com.periferia.prueba.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "tarjeta")
public class Tarjeta {
    @Transient
    public static final String SEQUENCE_NAME = "tarjeta_sequence";

    @Id
    private Long id;
    private Integer numValidacion;
    private String pan;
    private String titular;
    private String cedula;
    private String tipo;
    private Integer telefono;
    private String estado;
}
