package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mlex.Indice;
import mlex.Jogo;

public class testIndice {
	Indice indiceTeste;
	Jogo jogoTeste1;
	Jogo jogoTeste2;
	
	@Before
	public void setUp()
	{
		indiceTeste = new Indice();
		jogoTeste1 = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
		jogoTeste2 = new Jogo(666, "Jogo Chato", "31/02/94", "Klei");
	}

	@Test
	public void testAdicionaJogoIndiceVazio()
	{
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		assertTrue(indiceTeste.getNumeroJogos() == 1);

	}
	
	@Test
	public void testAdicionaJogoComUmJogo()
	{
		indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		indiceTeste.adicionaJogoNoIndice(jogoTeste2);
		assertTrue(indiceTeste.getNumeroJogos() == 2);
		
	}
	
	@Test
	public void testAdicionaJogoQueJaEstaNoIndice()
	{
		indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		assertTrue(indiceTeste.getNumeroJogos() == 1);
	}
	
	public void testeModificaJogo()
	{
		
	}

}
