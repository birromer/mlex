package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mlex.Jogo;
import mlex.Repositorio;
import mlex.UsuarioCommand;

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

	public void testGetIt()
	{
		
	}
	
	
	@Test
	public void testAdicaoJogo()
	{
		;
	}

	@Test
	public void testAdicionaNovoJogo()
	{
		assertTrue(repo.tamanho() == 0);
		repo.criaJogo(jogo);
		assertTrue(repo.tamanho() == 1);
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
