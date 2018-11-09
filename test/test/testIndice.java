package test;

import static org.junit.Assert.*;

import java.io.File;

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
		File arquivoIndice = new File("./etc/indice.txt"); 
        arquivoIndice.delete();
		indiceTeste = new Indice();
		jogoTeste1 = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
		System.out.println(jogoTeste1.toString());
		jogoTeste2 = new Jogo(666, "Jogo Chato", "31/02/94", "Despacito");
		System.out.println(jogoTeste2.toString());
	}

	@Test
	public void testAdicionaJogoIndiceVazio()
	{
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);

	}

	@Test
	public void testAdicionaJogoQueJaEstaNoIndice()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("segunda adicao de jogo ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
	}
	
	@Test
	public void testAdicionaJogoComUmJogo()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
		}
		catch (Exception e)
		{
			System.out.println("jogo2 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 2);	
	}

	
	public void testeModificaJogo()
	{
		
	}

}
