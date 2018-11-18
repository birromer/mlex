package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testeJogoComentario {

	Jogo j;
	
	@Before
	public void setUp() {
		j = new Jogo(222, "Tetris","1980","a");
	}
	
	@Test
	public void testeAddComentarioJogo(){
		j.addComentario("melhor jogo", 10);
	}
	
	@Test
	public void testeExibicao() {
		j.exibeComentarios();
	}
	
	@Test
	public void testeRemocao() {
		j.removeComentarios();
	}
	
}
