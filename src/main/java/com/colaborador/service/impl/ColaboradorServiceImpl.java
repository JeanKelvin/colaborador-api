package com.colaborador.service.impl;

import com.colaborador.exception.ColaboradorException;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;
import com.colaborador.model.dto.AgruparColaborador;
import com.colaborador.model.dto.ColaboradorDTO;
import com.colaborador.repository.ColaboradorRepository;
import com.colaborador.repository.SetorRepository;
import com.colaborador.service.ColaboradorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Colaborador insere(ColaboradorDTO colaboradorDTO) {
        //TODO create a validator service

        Optional<Setor> setor = this.setorRepository.findById(colaboradorDTO.getSetorId());

        if (!setor.isPresent()) {
            throw new ColaboradorException("Setor não encontrado.");
        }

        Colaborador colaborador = new Colaborador();
        colaborador.setSetor(setor.get());

        BeanUtils.copyProperties(colaboradorDTO, colaborador);

        verificaColaboradoresMaiores65anos(colaborador);

        verificaColaboradoresMenores18anos(colaborador);

        return this.colaboradorRepository.save(colaborador);
    }

    @Override
    public void atualiza(ColaboradorDTO colaboradorDTO) {

        Optional<Colaborador> busca = this.busca(colaboradorDTO.getId());
        if (!busca.isPresent()) {
            throw new ColaboradorException("Colaborador não encontrado.");
        }

        this.insere(colaboradorDTO);
    }

    private void verificaColaboradoresMaiores65anos(Colaborador colaborador) {
        Long totalColaborador = this.colaboradorRepository.countColaboradorBy();

        LocalDateTime minus65years = LocalDateTime.now().minusYears(65);
        Long maioresDe65Anos = this.colaboradorRepository.countColaboradorByDataNascimentoBefore(minus65years);

        if (colaborador.getDataNascimento().isBefore(minus65years)) {
            maioresDe65Anos += 1;
        }

        if (maioresDe65Anos > 0) {
            Long porcentagem = calculaPorcentagem(maioresDe65Anos, totalColaborador);

            if (porcentagem > 20L) {
                throw new ColaboradorException("Limite de colaboradores maiores de 65 anos atingido.");
            }
        }
    }

    private void verificaColaboradoresMenores18anos(Colaborador colaborador) {
        LocalDateTime minus18Years = LocalDateTime.now().minusYears(18);

        List<Colaborador> menoresDe18Anos = this.colaboradorRepository.findAllByDataNascimentoAfter(minus18Years);

        if (!menoresDe18Anos.isEmpty()) {

            menoresDe18Anos.forEach(c -> {

                Long totalColaboradorPorSetor = this.setorRepository.countAllById(c.getSetor().getId());

                Long totalMenoresDe18AnosPorSetor = this.colaboradorRepository.countAllByDataNascimentoAfterAndSetor(minus18Years, c.getSetor().getId());

                if (colaborador.getDataNascimento().isAfter(minus18Years)
                        && colaborador.getSetor().getId().equals(c.getSetor().getId())) {
                    totalMenoresDe18AnosPorSetor += 1L;
                }

                Long porcentagem = calculaPorcentagem(totalMenoresDe18AnosPorSetor, totalColaboradorPorSetor);

                if (porcentagem > 20L) {
                    throw new ColaboradorException("Limite de menores de 18 anos atingido no setor: " + c.getSetor().getDescricao());
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
