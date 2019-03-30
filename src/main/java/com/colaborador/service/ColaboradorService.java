package com.colaborador.service;

import com.colaborador.model.Colaborador;

import java.util.List;
import java.util.Optional;

public interface ColaboradorService {

    Colaborador insere(Colaborador colaborador);

    void atualiza(Colaborador colaborador);

    void remove(String id);

    Optional<Colaborador> busca(String id);

    List<Colaborador> lista();
}
