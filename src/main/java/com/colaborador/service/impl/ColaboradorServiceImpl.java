package com.colaborador.service.impl;

import com.colaborador.exception.ColaboradorException;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;
import com.colaborador.model.dto.AgruparColaborador;
import com.colaborador.repository.ColaboradorRepository;
import com.colaborador.repository.SetorRepository;
import com.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    private final SetorRepository setorRepository;

    @Autowired
    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository, SetorRepository setorRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    public Colaborador insere(Colaborador colaborador) {
        verificaColaboradoresMaiores65anos();

        verificaColaboradoresMenores18anos();

        return this.colaboradorRepository.save(colaborador);
    }

    @Override
    public void atualiza(Colaborador colaborador) {

        Optional<Colaborador> busca = this.busca(colaborador.getId());
        if (!busca.isPresent()) {
            throw new ColaboradorException("Colaborador não encontrado.");
        }

        this.insere(colaborador);
    }

    private void verificaColaboradoresMaiores65anos() {
        Long totalColaborador = this.colaboradorRepository.countColaboradorBy();

        Long maioresDe65Anos = this.colaboradorRepository.countColaboradorByDataNascimentoBefore(LocalDate.now().minusYears(65));

        if (maioresDe65Anos > 0) {
            Long porcentagem = calculaPorcentagem(maioresDe65Anos, totalColaborador);

            if (porcentagem > 20L) {
                throw new ColaboradorException("Limite de colaboradores maiores de 65 anos atingido.");
            }
        }
    }

    private void verificaColaboradoresMenores18anos() {
        List<Colaborador> menoresDe18Anos = this.colaboradorRepository.findAllByDataNascimentoAfter(LocalDate.now().minusYears(18));

        if (!menoresDe18Anos.isEmpty()) {

            menoresDe18Anos.forEach(colaborador -> {

                Long totalColaboradorPorSetor = this.setorRepository.countAllById(colaborador.getSetor().getId());

                Long totalMenoresDe18AnosPorSetor = this.colaboradorRepository.countAllByDataNascimentoAfterAndSetor(LocalDate.now().minusYears(18), colaborador.getSetor().getId());

                Long porcentagem = calculaPorcentagem(totalMenoresDe18AnosPorSetor, totalColaboradorPorSetor);

                if (porcentagem > 20L) {
                    throw new ColaboradorException("Limite de menores de 18 anos atingido no setor: " + colaborador.getSetor().getDescricao());
                }
            });
        }
    }

    private Long calculaPorcentagem(Long total, Long size) {
        if (size <= 0) {
            return 0L;
        }
        return (total * 100L) / size;
    }

    @Override
    public void remove(String id) {
        this.colaboradorRepository.deleteById(id);
    }

    @Override
    public Optional<Colaborador> busca(String id) {
        return this.colaboradorRepository.findById(id);
    }

    @Override
    public List<Colaborador> lista() {
        return this.colaboradorRepository.findAll();

        /*List<AgruparColaborador> agruparColaboradorList = new ArrayList<>();
        List<Setor> setorList = this.setorRepository.findAll();

        setorList.forEach(setor -> {
            List<Colaborador> result = this.colaboradorRepository.findAllBySetorId(setor.getId());
            AgruparColaborador agruparColaborador = new AgruparColaborador();
            agruparColaborador.setSetor(setor);
            agruparColaborador.setColaboradores(result);
            agruparColaboradorList.add(agruparColaborador);
        });

        return agruparColaboradorList;*/
    }
}
