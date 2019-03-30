package com.colaborador.ws;

import com.colaborador.model.Colaborador;
import com.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Colaborador insere(@RequestBody Colaborador colaborador) {
         return this.colaboradorService.insere(colaborador);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json")
    public void atualiza(@RequestBody Colaborador colaborador) {
        this.colaboradorService.atualiza(colaborador);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Colaborador> lista() {
        return this.colaboradorService.lista();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public List<Colaborador> busca(@PathVariable("id") String id) {
        Optional<Colaborador> busca = this.colaboradorService.busca(id);
        return Collections.singletonList(busca.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/setor", produces = "application/json")
    public List<Colaborador> listaBySetor() {
        return this.colaboradorService.lista();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{id}")
    public void remove(@PathVariable("id") String id) {
        this.colaboradorService.remove(id);
    }
}
