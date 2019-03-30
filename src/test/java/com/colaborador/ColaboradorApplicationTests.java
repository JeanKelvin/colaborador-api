package com.colaborador;

import com.colaborador.model.Colaborador;
import com.colaborador.repository.ColaboradorRepository;
import com.colaborador.service.impl.ColaboradorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColaboradorApplicationTests {

	@InjectMocks
	private ColaboradorServiceImpl colaboradorService;

	@Mock
	private ColaboradorRepository colaboradorRepository;

	@Test
	public void contextLoads() {
		when(colaboradorRepository.countColaboradorBy()).thenReturn(10L);
		this.colaboradorService.insere(new Colaborador());
	}

}
