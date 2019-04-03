package com.colaborador.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;
import com.colaborador.model.dto.ColaboradorDTO;

import java.time.LocalDateTime;

public class ColaboradorTemplate implements TemplateLoader {

    public static final String COLABORADOR_MENOR_18_ANOS = "colaboradorMenor18Anos";

    private class ColaboradorFields {
        private static final String ID = "id";
        private static final String NOME = "nome";
        private static final String CPF = "cpf";
        private static final String EMAIL = "email";
        private static final String DATA_NASCIMENTO = "dataNascimento";
        private static final String SETOR = "setor";
    }

    @Override
    public void load() {

        Rule ruleColaboradorMenor18Anos = new Rule();
        ruleColaboradorMenor18Anos.add(ColaboradorFields.ID, "5c92728aa88eb9645b08f471");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.NOME, "Joao da Silva");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.CPF, "00000000000");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.EMAIL, "joaodasilva@gmail.com");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.DATA_NASCIMENTO, LocalDateTime.now().minusYears(20));
        ruleColaboradorMenor18Anos.add(ColaboradorFields.SETOR, new Rule().one(Setor.class, SetorTemplate.SETOR_FISCAL));

        Fixture.of(Colaborador.class)
                .addTemplate(COLABORADOR_MENOR_18_ANOS, ruleColaboradorMenor18Anos);

    }
}
