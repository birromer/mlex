package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testeRepositorioComentario {

	private static Repositorio repo = new Repositorio();
	Jogo j0;
	Jogo j1;
	Jogo j2;
	
	@Before
	public void setUp() {
		j0 = new Jogo(0,"e","ece","effgfg");
		j1 = new Jogo(1, "Tetris","1980","a");
		j2 = new Jogo(2, "Tetris Effect","2018","b");

	}
	
	@Test
	public void testAddComentarioEmJogo() 
	{
		repo.setInfoJogo(j1);
		repo.adicionaJogo();
		repo.setInfoJogo(j2);
		repo.adicionaJogo();
		repo.addComentarioEmJogo(j1.getIdJogo(), "tetris eh muito bom");
	}
	
	@Test
	public void testRemocao()
	{
		repo.removeComentariosDeJogo(j0.getIdJogo());
	}
	
	@Test
	public void testExibicao()
	{
		repo.setInfoJogo(j0);
		repo.adicionaJogo();
		repo.addComentarioEmJogo(j0.getIdJogo(), "jogo real oficial");
		repo.exibeComentariosDeJogo(0);
	}

}
