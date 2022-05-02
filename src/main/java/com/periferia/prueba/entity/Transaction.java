package com.periferia.prueba.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "transaction")
public class Transaction {

    @Transient
    public static final String SEQUENCE_NAME = "transaction_sequence";

    @Id
    private Long id;
    private Long idTarjeta;
    private Integer reference;
    private Integer totalCompra;
    private String direction;
    private String estado;
    private Date fecha;

}
