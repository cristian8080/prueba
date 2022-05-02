package com.periferia.prueba.service.impl;

import com.periferia.prueba.entity.DatabaseSequence;
import com.periferia.prueba.entity.Transaction;
import com.periferia.prueba.repository.ITransansactionRespository;
import com.periferia.prueba.service.ITarjetaService;
import com.periferia.prueba.service.ITransansactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TransansactionServiceImpl implements ITransansactionService {

    @Autowired
    ITransansactionRespository transansactionRespository;

    @Autowired
    ITarjetaService tarjetaService;

    @Autowired
    private MongoOperations mongoOperations;


    @Override
    public Map<String, Object> registrarTransaccion(Transaction transaction, HttpServletRequest headers){
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> tarjeta = tarjetaService.consultarTarjeta(transaction.getIdTarjeta(),headers);
            if (!tarjeta.get("estado").equals("Enrolada")){
                response.put("codigo","02");
                response.put("mensaje","Tarjeta no enrolada");
                return response;
            }
            transaction.setId(generateSequence(Transaction.SEQUENCE_NAME));
            transaction.setFecha(new Date());
            transaction.setEstado("Aprobada");
            Transaction transactioncreada = transansactionRespository.save(transaction);
            response.put("codigo","00");
            response.put("mensaje","Compra exitosa");
            response.put("estado",transactioncreada.getEstado());
            response.put("referencia",transactioncreada.getReference());
            return response;
        }catch (Exception e){
            response.put("codigo","01");
            response.put("mensaje","Tarjeta no existe");
            return response;
        }
    }

    @Override
    public Map<String, Object> anularTransaccion(Long id, Integer reference, Integer totalCompra, HttpServletRequest headers) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Transaction> transaction = transansactionRespository.findById(id);
            if (transaction.isPresent()){
                if (!transaction.get().getReference().equals(reference)){
                    response.put("codigo","01");
                    response.put("mensaje","numero de referencia inválido");
                    return response;
                }
                if (evaluarfecha(transaction.get().getFecha(),new Date())){
                    transaction.get().setEstado("Anulado");
                    transansactionRespository.save(transaction.get());
                    response.put("codigo","00");
                    response.put("mensaje","Compra anulada");
                    return response;
                }
            }
            response.put("codigo","02");
            response.put("mensaje","No se puede anular transacción");
            return response;
        }catch (Exception e){
            response.put("codigo","02");
            response.put("mensaje","No se puede anular transacción");
            return response;
        }
    }

    private long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    private  boolean evaluarfecha(Date date1, Date date2) {
        boolean correcto = false;
        long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;
        long limit = (300 * 1000) / 1000L;//limite de tiempo

        if (diferencia <= limit) {
            correcto= true;
        }
        return correcto;
    }
}
