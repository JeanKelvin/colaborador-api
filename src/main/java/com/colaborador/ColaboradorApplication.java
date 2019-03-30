package com.colaborador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ColaboradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColaboradorApplication.class, args);
	}

	/*@Bean
	CommandLineRunner lookup(SetorService setorService, ColaboradorService colaboradorService) {
		return args -> {
			List<Setor> setorList = new ArrayList<>();

			Setor setorFinanceiro = new Setor();
			setorFinanceiro.setDescricao("Financeiro");

			Setor setorTI = new Setor();
			setorTI.setDescricao("TI");

			Setor setorContabilidade = new Setor();
			setorContabilidade.setDescricao("Contabilidade");

			setorList.add(setorFinanceiro);
			setorList.add(setorTI);
			setorList.add(setorContabilidade);

			setorService.save(setorList);
		};
	}*/
}
