package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteRepositorioIntegridade {

	private static Repositorio repo = new Repositorio();
	Jogo j0;
	Jogo j1;
	Jogo j2;
	
	@Before
	public void setUp() {
		j0 = new Jogo(0,"e","ece","effgfg");
		j1 = new Jogo(1, "Tetris","1980","a");
		j2 = new Jogo(2, "Tetris Effect","2018","b");
		repo.setInfoJogo(j0);
		repo.adicionaJogo();
		repo.setInfoJogo(j1);
		repo.adicionaJogo();
		repo.setInfoJogo(j2);
		repo.adicionaJogo();
		repo.atualizaVersaoJogo(j0.getIdJogo(),"v1.0");
		repo.atualizaVersaoJogo(j1.getIdJogo(),"v0.8");
		repo.atualizaVersaoJogo(j2.getIdJogo(),"v1.1");

	}
	
	@Test
	public void testVerificaIntegridade() 
	{	
		System.out.println(repo.getVersaoJogo(0));
		System.out.println(repo.getVersaoJogo(1));
		System.out.println(repo.getVersaoJogo(2));

		repo.verificaIntegridade();
		
		
		System.out.println(repo.getVersaoJogo(0));
		System.out.println(repo.getVersaoJogo(1));
		System.out.println(repo.getVersaoJogo(2));

	}
	
}
