package com.colaborador.ws;

import com.colaborador.model.Colaborador;
import com.colaborador.model.dto.ColaboradorDTO;
import com.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("colaborador")
public class ColaboradorRest {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorRest(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Colaborador insere(@RequestBody ColaboradorDTO colaboradorDTO) {
         return this.colaboradorService.insere(colaboradorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void atualiza(@RequestBody ColaboradorDTO colaboradorDTO) {
        this.colaboradorService.atualiza(colaboradorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Colaborador> lista() {
        return this.colaboradorService.lista();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Colaborador> busca(@PathVariable("id") String id) {
        return this.colaboradorService.busca(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{id}")
    public void remove(@PathVariable("id") String id) {
        this.colaboradorService.remove(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/setor", produces = APPLICATION_JSON_VALUE)
    public List<Colaborador> listaBySetor() {
        return this.colaboradorService.lista();
    }
}
