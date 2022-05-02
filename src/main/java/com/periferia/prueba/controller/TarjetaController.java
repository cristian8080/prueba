package com.periferia.prueba.controller;

import com.periferia.prueba.entity.Tarjeta;
import com.periferia.prueba.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "tarjeta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarjetaController {

    @Autowired
    ITarjetaService tarjetaService;

    @PostMapping(value = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTarjeta(@RequestBody Tarjeta tarjeta, HttpServletRequest headers){
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

    @PutMapping(value = "/activar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activarTarjeta(@RequestHeader Long id, @RequestHeader Integer numValidacion, HttpServletRequest headers){
        try {
            return new ResponseEntity<>(tarjetaService.activarTarjeta(id,numValidacion,headers), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarTarjeta(@RequestParam("id") Long id, HttpServletRequest headers){
        try {
            return new ResponseEntity<>(tarjetaService.consultarTarjeta(id,headers), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarTarjeta(@RequestHeader Long id, @RequestHeader Integer numValidacion, @RequestHeader String pan, HttpServletRequest headers){
        try {
            return new ResponseEntity<>(tarjetaService.eliminarTarjeta(id,numValidacion,pan,headers), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
