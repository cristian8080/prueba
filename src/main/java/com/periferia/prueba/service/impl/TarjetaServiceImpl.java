package com.periferia.prueba.service.impl;

import com.periferia.prueba.entity.DatabaseSequence;
import com.periferia.prueba.entity.Tarjeta;
import com.periferia.prueba.repository.ITarjetaRepository;
import com.periferia.prueba.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

    @Autowired
    ITarjetaRepository tarjetaRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    @Transactional
    public Tarjeta crearTarjeta(Tarjeta tarjeta, HttpServletRequest headers) throws Exception {
        try{
            tarjeta.setId(generateSequence(tarjeta.SEQUENCE_NAME));
            Random rd = new Random();
            int numeroAleatorio = rd.nextInt(101);
            tarjeta.setNumValidacion(numeroAleatorio);
            tarjeta.setEstado("creada");
            if(tarjeta.getPan().length()<16){
                System.out.println("Tamaño pan no valido");
                throw new Exception();
            }
            tarjeta = tarjetaRepository.save(tarjeta);
            tarjeta.setPan(enmascarar(tarjeta.getPan()));
            return tarjeta;
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public Map<String, Object> activarTarjeta(Long id, Integer numValidacion, HttpServletRequest headers) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Tarjeta> tarjeta = tarjetaRepository.findById(id);
            if (tarjeta.isPresent()){
                Tarjeta tarjetaUpdate;
                if (tarjeta.get().getNumValidacion()==numValidacion){
                    tarjeta.get().setEstado("Enrolada");
                    tarjetaUpdate = tarjetaRepository.save(tarjeta.get());
                    response.put("codigo","00");
                    response.put("mensaje","Éxito");
                    tarjetaUpdate.setPan(enmascarar(tarjetaUpdate.getPan()));
                    response.put("PAN",tarjetaUpdate.getPan());
                    return response;
                }else {
                    response.put("codigo","02");
                    response.put("mensaje","Número de validación inválido");
                    return response;
                }
            }
            response.put("codigo","01");
            response.put("mensaje","Tarjeta no existe");
            return response;

        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public Map<String, Object> consultarTarjeta(Long id, HttpServletRequest headers) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Tarjeta> tarjeta = tarjetaRepository.findById(id);
            if (tarjeta.isPresent()){
                response.put("PAN",enmascarar(tarjeta.get().getPan()));
                response.put("titular",tarjeta.get().getTitular());
                response.put("cédula",tarjeta.get().getCedula());
                response.put("telefono",tarjeta.get().getTelefono());
                response.put("estado",tarjeta.get().getEstado());
                return response;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public Map<String, Object> eliminarTarjeta(Long id, Integer numValidacion, String pan, HttpServletRequest headers) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try {
            tarjetaRepository.deleteById(id);
            response.put("codigo","01");
            response.put("mensaje","Se ha eliminado la tarjeta");
        }catch (Exception e){
            response.put("codigo","01");
            response.put("mensaje","No se ha eliminado la tarjeta");
        }
        return response;
    }

    private String enmascarar(String enmascara){
        StringBuilder str = new StringBuilder(enmascara.length());
        for (int i = 0;i<enmascara.length();i++){
            if (i>=6 && i<enmascara.length()-4){
                str.append("*");
            }else {
                str.append(enmascara.charAt(i));
            }
        }
        return str.toString().toUpperCase();
    }

    private long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
