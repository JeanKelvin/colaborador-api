package com.colaborador.service;

import com.colaborador.model.Colaborador;
import com.colaborador.model.dto.ColaboradorDTO;

import java.util.List;
import java.util.Optional;

public interface ColaboradorService {

    Colaborador insere(ColaboradorDTO colaboradorDTO);

    void atualiza(ColaboradorDTO colaboradorDTO);

    void remove(String id);

    Optional<Colaborador> busca(String id);

    List<Colaborador> lista();
}
