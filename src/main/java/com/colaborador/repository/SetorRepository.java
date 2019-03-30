package com.colaborador.repository;

import com.colaborador.model.Setor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SetorRepository extends MongoRepository<Setor, String> {

    Long countAllById(String id);
}
