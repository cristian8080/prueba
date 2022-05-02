package com.periferia.prueba.service;

import com.periferia.prueba.entity.Tarjeta;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ITarjetaService {

    Tarjeta crearTarjeta(Tarjeta tarjeta, HttpServletRequest headers) throws Exception;

    Map<String, Object> activarTarjeta(Long id, Integer numValidacion, HttpServletRequest headers) throws Exception;

    Map<String, Object> consultarTarjeta(Long id, HttpServletRequest headers) throws Exception;

    Map<String, Object> eliminarTarjeta(Long id, Integer numValidacion, String pan, HttpServletRequest headers) throws Exception;
}
