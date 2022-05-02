package com.periferia.prueba.service;

import com.periferia.prueba.entity.Transaction;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ITransansactionService {

    Map<String, Object> registrarTransaccion(Transaction transaction, HttpServletRequest headers);

    Map<String, Object> anularTransaccion(Long id, Integer reference, Integer totalCompra, HttpServletRequest headers);
}
