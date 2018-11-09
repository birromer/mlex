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
		indiceTeste.novoJogoSendoAdicionado(0);
		jogoTeste2 = new Jogo(666, "Jogo Chato", "31/02/94", "Despacito");
		indiceTeste.novoJogoSendoAdicionado(666);
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

	@Test
	public void testAdicionaCategoriaAoIndiceComNenhumaCategoria()
	{
		assertTrue(indiceTeste.getNumeroCategorias() == 0);
		assertTrue(indiceTeste.getMapaCategorias().size() == 2);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 1);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");		
		assertTrue(indiceTeste.getNumeroCategorias() == 1);
		assertTrue(indiceTeste.getMapaCategorias().size() == 2);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 1);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		assertTrue(indiceTeste.getNumeroCategorias() == 2);
		assertTrue(indiceTeste.getMapaCategorias().size() == 2);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 2);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		assertTrue(indiceTeste.getNumeroCategorias() == 3);
		assertTrue(indiceTeste.getMapaCategorias().size() == 2);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 3);
//		for (Integer key : indiceTeste.getMapaCategorias().keySet())
//		{
//			System.out.println(indiceTeste.getCategorias(key));
//		}
	}
	
	@Test
	public void testAdicionaCategoriaAoJogo()
	{	
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado") == true);
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do equinocio passado") == true);
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do verao passado") == true);
	}
	
	@Test
	public void testRemoveCategoriaDoJogo()
	{	
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		
		indiceTeste.removeCategoriaDoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		indiceTeste.removeCategoriaDoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		
		assertTrue(indiceTeste.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado") == false);
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do equinocio passado") == false);
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
		
			System.out.println("categoria2 nao existe");
		}
		
		indiceTeste.removeCategoriaDoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do verao passado") == false);
	}

	
	@Test
	public void testmodificaJogoNoIndice()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		
		System.out.println(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()));
		assertTrue(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()).get(3) == "Klei");
		Jogo temp = new Jogo(0, "Jogo Legal", "01/09/94", "Cacatua");
		
		try
		{
			indiceTeste.modificaJogoNoIndice(temp);
		}
		catch (Exception e)
		{
			System.out.println("Jogo nao existe no indice");
		}
	
		assertTrue(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()).get(3) == "Cacatua");
		System.out.println(indiceTeste.getInformacoesJogoNoIndice(temp.getIdJogo()));
		
	}
	
	@Test
	public void testRemoveJogo()
	{
		
	}

}
