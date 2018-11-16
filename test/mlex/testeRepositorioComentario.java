package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testeRepositorioComentario {

	private static Repositorio repo = new Repositorio();
	Jogo j;
	
	@Before
	public void setUp() {
		j = new Jogo(222, "Tetris","1980","a");
	}
	
	@Test
	public void testAddComentarioEmJogo() {
		//repo.adicionaJogo(j);
		
	}

}
