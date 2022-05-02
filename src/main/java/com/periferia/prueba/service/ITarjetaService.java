package com.periferia.prueba.service;

import com.periferia.prueba.entity.Tarjeta;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface ITarjetaService {

    Tarjeta crearTarjeta(Tarjeta tarjeta, HttpHeaders headers) throws Exception;

    Map<String, Object> activarTarjeta(Long id, Integer numValidacion, HttpHeaders headers) throws Exception;

    Map<String, Object> consultarTarjeta(Long id, HttpHeaders headers) throws Exception;

    Map<String, Object> eliminarTarjeta(Long id, Integer numValidacion, String pan, HttpHeaders headers) throws Exception;
}
