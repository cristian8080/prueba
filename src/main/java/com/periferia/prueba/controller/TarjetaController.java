package com.periferia.prueba.controller;

import com.periferia.prueba.model.Tarjeta;
import com.periferia.prueba.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {

    @Autowired
    ITarjetaService tarjetaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearTarjeta(@RequestBody Tarjeta tarjeta, HttpHeaders headers){
        Map<String,Object> response = new HashMap<>();
        try{
            Tarjeta tarjetaCreada = tarjetaService.crearTarjeta(tarjeta, headers);
            response.put("codigo","00");
            response.put("mensaje","Éxito");
            response.put("tarjeta",tarjetaCreada);
        }catch (Exception e){
            System.out.println(e.getCause());
            response.put("codigo","01");
            response.put("mensaje","Fallido");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/activar")
    public ResponseEntity<?> activarTarjeta(HttpHeaders headers){
        return null;
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarTarjeta(HttpHeaders headers){
        return null;
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTarjeta(HttpHeaders headers){
        return null;
    }
}
