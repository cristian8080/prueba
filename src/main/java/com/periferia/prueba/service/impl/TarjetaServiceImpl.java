package com.periferia.prueba.service.impl;

import com.periferia.prueba.model.Tarjeta;
import com.periferia.prueba.repository.ITarjetaRepository;
import com.periferia.prueba.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

    @Autowired
    ITarjetaRepository tarjetaRepository;

    @Override
    @Transactional
    public Tarjeta crearTarjeta(Tarjeta tarjeta, HttpHeaders headers) throws Exception {
        try{
            Random rd = new Random();
            int numeroAleatorio = rd.nextInt(101);
            tarjeta.setNumValidacion(numeroAleatorio);
            tarjeta.setEstado("creada");
            tarjeta = tarjetaRepository.save(tarjeta);
            return tarjeta;
        }catch (Exception e){
            throw new Exception();
        }
    }
}
