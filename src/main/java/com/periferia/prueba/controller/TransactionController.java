package com.periferia.prueba.controller;

import com.periferia.prueba.entity.Transaction;
import com.periferia.prueba.service.ITransansactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transaccion")
public class TransactionController {

    @Autowired
    ITransansactionService transansactionService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearTransaccion(@RequestBody Transaction transaction, HttpHeaders headers){
        return new ResponseEntity<>(transansactionService.registrarTransaccion(transaction,headers), HttpStatus.OK);
    }

    @PutMapping("/anular")
    public ResponseEntity<?> anularTransaccion(@RequestHeader Long id, @RequestHeader Integer reference, @RequestHeader Integer totalCompra, HttpHeaders headers){
        return new ResponseEntity<>(transansactionService.anularTransaccion(id,reference, totalCompra,headers),HttpStatus.OK);
    }
}
