package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mlex.Jogo;
import mlex.Repositorio;

public class TestAdicionaJogo
{
	private Jogo jogo;
	private Repositorio repo;

	@Before
	public void setUp()
	{
		repo = new Repositorio();
		jogo = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
	}

	@Test
	public void testAdicaoJogo()
	{
		;
	}

	@Test
	public void testAdicionaNovoJogo()
	{
		assertTrue(repo.tam() == 0);
		repo.criaJogo(jogo);
		assertTrue(repo.tam() == 1);
	}
	
	
	
	/*public void testRemoveJogo()
	{	
		assertTrue(bl.tam() == 0);
		bl.novaFunc("tapatam");
		assertTrue(bl.tam() == 1);
		bl.removeFunc("tapatam");
		assertTrue(bl.tam() == 0);
	}*/

}
