package com.periferia.prueba.repository;

import com.periferia.prueba.entity.Tarjeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarjetaRepository extends MongoRepository<Tarjeta,Long> {

}
