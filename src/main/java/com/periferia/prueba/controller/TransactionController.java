package com.periferia.prueba.controller;

import com.periferia.prueba.entity.Transaction;
import com.periferia.prueba.service.ITransansactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "transaccion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {

    @Autowired
    ITransansactionService transansactionService;

    @PostMapping(value = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTransaccion(@RequestBody Transaction transaction, HttpServletRequest headers){
        return new ResponseEntity<>(transansactionService.registrarTransaccion(transaction,headers), HttpStatus.OK);
    }

    @PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> anularTransaccion(@RequestHeader Long id, @RequestHeader Integer reference, @RequestHeader Integer totalCompra, HttpServletRequest headers){
        return new ResponseEntity<>(transansactionService.anularTransaccion(id,reference, totalCompra,headers),HttpStatus.OK);
    }
}
