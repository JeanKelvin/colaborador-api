package com.colaborador.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.colaborador.model.Colaborador;
import com.colaborador.model.Setor;
import com.colaborador.model.dto.ColaboradorDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ColaboradorDTOTemplate implements TemplateLoader {

    public static final String COLABORADOR_DTO = "colaboradorDTO";
    public static final String COLABORADOR_DTO_MAIOR_65_ANOS = "colaboradorDTOMaior65Anos";
    public static final String COLABORADOR_DTO_MENOR_18_ANOS = "colaboradorDTOMenor18Anos";

    private class ColaboradorDTOFields {
        private static final String ID = "id";
        private static final String NOME = "nome";
        private static final String CPF = "cpf";
        private static final String EMAIL = "email";
        private static final String DATA_NASCIMENTO = "dataNascimento";
        private static final String SETOR_ID = "setorId";
    }

    @Override
    public void load() {

        Rule ruleColaborador = new Rule();
        ruleColaborador.add(ColaboradorDTOFields.ID, "5c92728aa88eb9645b08f471");
        ruleColaborador.add(ColaboradorDTOFields.NOME, "Joao da Silva");
        ruleColaborador.add(ColaboradorDTOFields.CPF, "00000000000");
        ruleColaborador.add(ColaboradorDTOFields.EMAIL, "joaodasilva@gmail.com");
        ruleColaborador.add(ColaboradorDTOFields.DATA_NASCIMENTO, LocalDateTime.now().minusYears(20));
        ruleColaborador.add(ColaboradorDTOFields.SETOR_ID, "5c927265a88eb9645b08f45c");

        Rule ruleColaboradorMaior65Anos = new Rule();
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.ID, "5c8fe15ca88eb92fbffbffec");
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.NOME, "Maria da Silva");
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.CPF, "11111111111");
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.EMAIL, "mariadasilva@gmail.com");
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.DATA_NASCIMENTO, LocalDateTime.now().minusYears(66));
        ruleColaboradorMaior65Anos.add(ColaboradorDTOFields.SETOR_ID, "5c903954a88eb92fbffc2be4");

        Rule ruleColaboradorMenor18Anos = new Rule();
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.ID, "5c903a50a88eb92fbffc2c24");
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.NOME, "Luana da Silva");
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.CPF, "22222222222");
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.EMAIL, "luanadasilva@gmail.com");
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.DATA_NASCIMENTO, LocalDateTime.now().minusYears(17));
        ruleColaboradorMenor18Anos.add(ColaboradorDTOFields.SETOR_ID, "5c90ef2a403d80de5826ee75,z");


        Fixture.of(ColaboradorDTO.class)
                .addTemplate(COLABORADOR_DTO, ruleColaborador)
                .addTemplate(COLABORADOR_DTO_MAIOR_65_ANOS, ruleColaboradorMaior65Anos)
                .addTemplate(COLABORADOR_DTO_MENOR_18_ANOS, ruleColaboradorMenor18Anos);
    }
}
