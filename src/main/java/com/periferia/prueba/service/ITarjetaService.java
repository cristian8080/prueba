package com.periferia.prueba.service;

import com.periferia.prueba.model.Tarjeta;

import org.springframework.http.HttpHeaders;

public interface ITarjetaService {

    Tarjeta crearTarjeta(Tarjeta tarjeta, HttpHeaders headers) throws Exception;
}
