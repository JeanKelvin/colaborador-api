package com.colaborador.service;

import com.colaborador.model.Setor;

import java.util.List;

public interface SetorService {

    void save(List<Setor> setorList);

    List<Setor> lista();
}
