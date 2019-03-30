package com.colaborador.repository;

import com.colaborador.model.Colaborador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ColaboradorRepository extends MongoRepository<Colaborador, String> {

    List<Colaborador> findAllBySetorId(String id);

    Long countColaboradorBy();

    Long countColaboradorByDataNascimentoBefore(LocalDate dataNascimento);

    List<Colaborador> findAllByDataNascimentoAfter(LocalDate dataNascimento);

    Long countAllByDataNascimentoAfterAndSetor(LocalDate dataNascimento, String id);

}
