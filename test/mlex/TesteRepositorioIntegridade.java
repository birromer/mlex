package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteRepositorioIntegridade {

	private static Repositorio repo = new Repositorio();
	Jogo j0;
	Jogo j1;
	Jogo j2;
	Jogo j3;
	
	@Before
	public void setUp() {
		j0 = new Jogo(0,"e","ece","effgfg");
		j1 = new Jogo(1, "Tetris","1980","a");
		j2 = new Jogo(2, "Tetris Effect","2018","b");
		j3 = new Jogo(3, "Mario","2077","jogsc");
		
		repo.setInfoJogo(j0);
		repo.adicionaJogo();
		
		repo.setInfoJogo(j1);
		repo.adicionaJogo();
		
		repo.setInfoJogo(j2);
		repo.adicionaJogo();
		
		repo.setInfoJogo(j3);
		repo.adicionaJogo();
		
		repo.atualizaVersaoJogo(j0.getIdJogo(),"v1.0");
		repo.atualizaVersaoJogo(j1.getIdJogo(),"v0.8");
		repo.atualizaVersaoJogo(j2.getIdJogo(),"v1.1");
		repo.atualizaVersaoJogo(j3.getIdJogo(),"v2.0");
	}
	
	@Test
	public void testVerificaIntegridade() 
	{	

		repo.verificaIntegridade();

		assertTrue(repo.getVersaoJogo(0).equals("v1.0"));
		assertTrue(repo.getVersaoJogo(1).equals("v1.0"));
		assertTrue(repo.getVersaoJogo(2).equals("v1.1"));
		assertTrue(repo.getVersaoJogo(3).equals("v2.0"));

	}
	
}
