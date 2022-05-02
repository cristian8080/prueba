package com.periferia.prueba.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Tarjeta {
    @Id
    private Long id;
    private Integer numValidacion;
    private String titular;
    private String cedula;
    private String tipo;
    private Integer telefono;
    private String estado;
}
