package com.colaborador.ws;

import com.colaborador.model.Setor;
import com.colaborador.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("setor")
public class SetorRest {

    private final SetorService setorService;

    @Autowired
    public SetorRest(SetorService setorService) {
        this.setorService = setorService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Setor> lista() {
        return this.setorService.lista();
    }
}
