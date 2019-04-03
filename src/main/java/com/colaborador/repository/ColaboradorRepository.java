package com.colaborador.repository;

import com.colaborador.model.Colaborador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ColaboradorRepository extends MongoRepository<Colaborador, String> {

    List<Colaborador> findAllBySetorId(String id);

    Long countColaboradorBy();

    Long countColaboradorByDataNascimentoBefore(LocalDateTime dataNascimento);

    List<Colaborador> findAllByDataNascimentoAfter(LocalDateTime dataNascimento);

    Long countAllByDataNascimentoAfterAndSetor(LocalDateTime dataNascimento, String id);

}
