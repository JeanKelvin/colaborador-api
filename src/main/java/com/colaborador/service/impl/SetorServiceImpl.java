package com.colaborador.service.impl;

import com.colaborador.repository.SetorRepository;
import com.colaborador.service.SetorService;
import com.colaborador.model.Setor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    @Autowired
    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    public void save(List<Setor> setorList) {
        this.setorRepository.saveAll(setorList);
    }
}
