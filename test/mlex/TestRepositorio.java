package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRepositorio {
	Repositorio repositorioTeste;
	
	
	@Before
	public void setUp()
	{
		repositorioTeste = new Repositorio();
		
	}

	@Test
	public void testAdicionaPrimeiroJogo()
	{
		assertTrue(repositorioTeste.adicionaJogo() == 0);
	}
	

}
