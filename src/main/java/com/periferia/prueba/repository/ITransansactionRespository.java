package com.periferia.prueba.repository;

import com.periferia.prueba.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransansactionRespository extends MongoRepository<Transaction, Long> {
}
