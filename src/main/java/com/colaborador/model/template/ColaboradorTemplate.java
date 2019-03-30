package com.colaborador.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ColaboradorTemplate implements TemplateLoader {

    public static final String COLABORADOR = "colaborador";
    public static final String COLABORADOR_MAIOR_65_ANOS = "colaboradorMaior65Anos";
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

        Rule ruleColaborador = new Rule();
        ruleColaborador.add(ColaboradorFields.ID, "5c92728aa88eb9645b08f471");
        ruleColaborador.add(ColaboradorFields.NOME, "Joao da Silva");
        ruleColaborador.add(ColaboradorFields.CPF, "00000000000");
        ruleColaborador.add(ColaboradorFields.EMAIL, "joaodasilva@gmail.com");
        ruleColaborador.add(ColaboradorFields.DATA_NASCIMENTO, Date.from(LocalDate.now().minusYears(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ruleColaborador.add(ColaboradorFields.SETOR, new Rule().one(Setor.class, SetorTemplate.SETOR_TI));

        Rule ruleColaboradorMaior65Anos = new Rule();
        ruleColaboradorMaior65Anos.add(ColaboradorFields.ID, "5c8fe15ca88eb92fbffbffec");
        ruleColaboradorMaior65Anos.add(ColaboradorFields.NOME, "Maria da Silva");
        ruleColaboradorMaior65Anos.add(ColaboradorFields.CPF, "11111111111");
        ruleColaboradorMaior65Anos.add(ColaboradorFields.EMAIL, "mariadasilva@gmail.com");
        ruleColaboradorMaior65Anos.add(ColaboradorFields.DATA_NASCIMENTO, Date.from(LocalDate.now().minusYears(66).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ruleColaboradorMaior65Anos.add(ColaboradorFields.SETOR, new Rule().one(Setor.class, SetorTemplate.SETOR_CONTABILIDADE));

        Rule ruleColaboradorMenor18Anos = new Rule();
        ruleColaboradorMenor18Anos.add(ColaboradorFields.ID, "5c903a50a88eb92fbffc2c24");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.NOME, "Luana da Silva");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.CPF, "22222222222");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.EMAIL, "luanadasilva@gmail.com");
        ruleColaboradorMenor18Anos.add(ColaboradorFields.DATA_NASCIMENTO, Date.from(LocalDate.now().minusYears(17).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ruleColaboradorMenor18Anos.add(ColaboradorFields.SETOR, new Rule().one(Setor.class, SetorTemplate.SETOR_FISCAL));


        Fixture.of(Colaborador.class)
                .addTemplate(COLABORADOR, ruleColaborador)
                .addTemplate(COLABORADOR_MAIOR_65_ANOS, ruleColaboradorMaior65Anos)
                .addTemplate(COLABORADOR_MENOR_18_ANOS, ruleColaboradorMenor18Anos);
    }
}
