package com.colaborador.model.dto;

import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;

import java.util.List;

public class AgruparColaborador {

    private Setor setor;
    private List<Colaborador> colaboradores;


    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
