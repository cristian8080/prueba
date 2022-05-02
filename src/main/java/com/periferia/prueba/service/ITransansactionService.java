package com.periferia.prueba.service;

import com.periferia.prueba.entity.Transaction;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface ITransansactionService {

    Map<String, Object> registrarTransaccion(Transaction transaction, HttpHeaders headers);

    Map<String, Object> anularTransaccion(Long id, Integer reference, Integer totalCompra, HttpHeaders headers);
}
