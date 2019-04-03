package com.colaborador.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.colaborador.exception.ColaboradorException;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;
import com.colaborador.model.dto.ColaboradorDTO;
import com.colaborador.model.template.ColaboradorDTOTemplate;
import com.colaborador.model.template.ColaboradorTemplate;
import com.colaborador.model.template.SetorTemplate;
import com.colaborador.repository.ColaboradorRepository;
import com.colaborador.repository.SetorRepository;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ColaboradorServiceImplTest {

    @InjectMocks
    private ColaboradorServiceImpl colaboradorService;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private SetorRepository setorRepository;

    private ColaboradorDTO colaboradorDTO;

    private ColaboradorDTO colaboradorDTOMaior65Anos;

    private ColaboradorDTO colaboradorDTOMenor18Anos;

    private Colaborador colaboradorMenor18Anos;

    private Setor setorTI;

    private Setor setorContabilidade;

    private Setor setorFiscal;


    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.colaborador.model.template");
        this.colaboradorDTO = Fixture.from(ColaboradorDTO.class).gimme(ColaboradorDTOTemplate.COLABORADOR_DTO);
        this.colaboradorDTOMaior65Anos = Fixture.from(ColaboradorDTO.class).gimme(ColaboradorDTOTemplate.COLABORADOR_DTO_MAIOR_65_ANOS);
        this.colaboradorDTOMenor18Anos = Fixture.from(ColaboradorDTO.class).gimme(ColaboradorDTOTemplate.COLABORADOR_DTO_MENOR_18_ANOS);
        this.colaboradorMenor18Anos = Fixture.from(Colaborador.class).gimme(ColaboradorTemplate.COLABORADOR_MENOR_18_ANOS);
        this.setorTI = Fixture.from(Setor.class).gimme(SetorTemplate.SETOR_TI);
        this.setorContabilidade = Fixture.from(Setor.class).gimme(SetorTemplate.SETOR_CONTABILIDADE);
        this.setorFiscal = Fixture.from(Setor.class).gimme(SetorTemplate.SETOR_FISCAL);

    }

    @Test
    public void test_insere_success() {
        when(this.setorRepository.findById(colaboradorDTO.getSetorId())).thenReturn(Optional.of(setorTI));
        when(this.colaboradorRepository.countColaboradorBy()).thenReturn(10L);
        this.colaboradorService.insere(colaboradorDTO);
    }

    @Test
    public void test_insere_valida_maiores_65_anos() {
        when(this.colaboradorRepository.countColaboradorBy()).thenReturn(1L);
        when(this.setorRepository.findById(colaboradorDTOMaior65Anos.getSetorId())).thenReturn(Optional.of(setorContabilidade));

        try {
            this.colaboradorService.insere(colaboradorDTOMaior65Anos);
        } catch (ColaboradorException ce) {
            assertNotNull(ce.getMessage());
            assertEquals("Limite de colaboradores maiores de 65 anos atingido.", ce.getMessage());
        }
    }

    @Test
    public void test_insere_valida_menores_18_anos() {
        when(this.setorRepository.findById(colaboradorDTOMenor18Anos.getSetorId())).thenReturn(Optional.of(setorFiscal));
        when(this.colaboradorRepository.findAllByDataNascimentoAfter(any(LocalDateTime.class))).thenReturn(Collections.singletonList(colaboradorMenor18Anos));
        when(this.setorRepository.countAllById(colaboradorMenor18Anos.getSetor().getId())).thenReturn(1L);

        try {
            this.colaboradorService.insere(colaboradorDTOMenor18Anos);
        } catch (ColaboradorException ce) {
            assertNotNull(ce.getMessage());
            assertEquals("Limite de menores de 18 anos atingido no setor: " + colaboradorMenor18Anos.getSetor().getDescricao(), ce.getMessage());
        }
    }

    @Test
    public void remove() {
    }

    @Test
    public void busca() {
    }

    @Test
    public void lista() {
    }
}