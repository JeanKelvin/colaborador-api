package com.colaborador.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.colaborador.model.Setor;

public class SetorTemplate implements TemplateLoader {

    public static final String SETOR_TI = "setorTI";
    public static final String SETOR_CONTABILIDADE = "setorContabilidade";
    public static final String SETOR_FISCAL = "setorFiscal";



    private class SetorFields {
        private static final String ID = "id";
        private static final String DESCRICAO = "descricao";
    }

    @Override
    public void load() {

        Rule ruleSetorTI = new Rule();
        ruleSetorTI.add(SetorFields.ID, "5c927265a88eb9645b08f45c");
        ruleSetorTI.add(SetorFields.DESCRICAO, "TI");

        Rule ruleSetorContabilidade = new Rule();
        ruleSetorContabilidade.add(SetorFields.ID, "5c903954a88eb92fbffc2be4");
        ruleSetorContabilidade.add(SetorFields.DESCRICAO, "Contabilidade");

        Rule ruleSetorFiscal = new Rule();
        ruleSetorFiscal.add(SetorFields.ID, "5c90ef2a403d80de5826ee75");
        ruleSetorFiscal.add(SetorFields.DESCRICAO, "Fiscal");

        Fixture.of(Setor.class)
                .addTemplate(SETOR_TI, ruleSetorTI)
                .addTemplate(SETOR_CONTABILIDADE, ruleSetorContabilidade)
                .addTemplate(SETOR_FISCAL, ruleSetorFiscal);

    }
}
