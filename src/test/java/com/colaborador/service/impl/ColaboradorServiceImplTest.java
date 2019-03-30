package com.colaborador.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.colaborador.exception.ColaboradorException;
import com.colaborador.model.Colaborador;
import com.colaborador.model.template.ColaboradorTemplate;
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

import java.time.LocalDate;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class ColaboradorServiceImplTest {

    @InjectMocks
    private ColaboradorServiceImpl colaboradorService;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private SetorRepository setorRepository;

    private Colaborador colaborador;

    private Colaborador colaboradorMaior65Anos;

    private Colaborador colaboradorMenor18Anos;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.colaborador.model.template");
        this.colaborador = Fixture.from(Colaborador.class).gimme(ColaboradorTemplate.COLABORADOR);
        this.colaboradorMaior65Anos = Fixture.from(Colaborador.class).gimme(ColaboradorTemplate.COLABORADOR_MAIOR_65_ANOS);
        this.colaboradorMenor18Anos = Fixture.from(Colaborador.class).gimme(ColaboradorTemplate.COLABORADOR_MENOR_18_ANOS);
    }

    @Test
    public void test_insere_success() {
        when(this.colaboradorRepository.countColaboradorBy()).thenReturn(10L);
        this.colaboradorService.insere(colaborador);
    }

    @Test
    public void test_insere_valida_maiores_65_anos() {
        when(this.colaboradorRepository.countColaboradorBy()).thenReturn(1L);
        when(this.colaboradorRepository.countColaboradorByDataNascimentoBefore(LocalDate.now().minusYears(65))).thenReturn(1L);

        try {
            this.colaboradorService.insere(colaboradorMaior65Anos);
        } catch (ColaboradorException ce) {
            assertNotNull(ce.getMessage());
            assertEquals("Limite de colaboradores maiores de 65 anos atingido.", ce.getMessage());
        }
    }

    @Test
    public void test_insere_valida_menores_18_anos() {
        when(this.colaboradorRepository.findAllByDataNascimentoAfter(LocalDate.now().minusYears(18))).thenReturn(Collections.singletonList(colaboradorMenor18Anos));
        when(this.setorRepository.countAllById(colaboradorMenor18Anos.getSetor().getId())).thenReturn(1L);
        when(this.colaboradorRepository.countAllByDataNascimentoAfterAndSetor(LocalDate.now().minusYears(18), colaboradorMenor18Anos.getSetor().getId())).thenReturn(1L);
        try {
            this.colaboradorService.insere(colaboradorMenor18Anos);
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