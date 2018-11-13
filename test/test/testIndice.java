package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import mlex.Indice;
import mlex.Jogo;

public class testIndice {
	Indice indiceTeste;
	Jogo jogoTeste1;
	Jogo jogoTeste2;
	File arquivoIndice;
	String pathObjetoIndice = "./etc/objeto_indice";
	String pathMapaJogoCategorias = "./etc/mapa_jogo_categorias";
	String pathListaCategorias = "./etc/lista_categorias.txt";
	FileOutputStream saidaArquivoEscrita;
	ObjectOutputStream saidaObjetoEscrita;
	
	@Before
	public void setUp()
	{
		arquivoIndice = new File("./etc/indice.txt"); 
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
	public void testRemoveJogoQueNaoEstaNoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	@Test
	public void testRemoveJogoQueEstaNoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		
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
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	@Test
	public void testRemoveMultiplosJogosDoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 2);
		
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste2.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueNaoExiste()
	{
		File arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == true)
		{
			arquivoObjetoIndice.delete();
		}
		assertFalse(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice .exists());
	}
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueExisteVazio()
	{
		File arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == true)
		{
			arquivoObjetoIndice.delete();
		}
		try
		{
			arquivoObjetoIndice.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice.exists());
	}
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueExisteComConteudoAleatorio()
	{
		File arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == false)
		{
			try
			{
				arquivoObjetoIndice.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice.exists());
	}
	
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueNaoExiste()
	{
		File arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == true)
		{
			arquivoMapaJogoCategorias.delete();
		}
		assertFalse(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias .exists());
	}
		
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueExisteVazio()
	{
		File arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == true)
		{
			arquivoMapaJogoCategorias.delete();
		}
		try
		{
			arquivoMapaJogoCategorias.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias.exists());
	}
		
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueExisteComConteudoAleatorio()
	{
		File arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == false)
		{
			try
			{
				arquivoMapaJogoCategorias.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueNaoExiste()
	{
		File arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == true)
		{
			arquivoListaCategorias.delete();
		}
		assertFalse(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueExisteVazio()
	{
		File arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == true)
		{
			arquivoListaCategorias.delete();
		}
		try
		{
			arquivoListaCategorias.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueExisteComConteudoAleatorio()
	{
		File arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == false)
		{
			try
			{
				arquivoListaCategorias.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	
	
	
	@Test
	public void testRestauraObjetoIndiceDoArquivoQueNaoExiste()
	{
		
	}
	
	@Test
	public void testRestauraObjetoIndiceDoArquivoQueExiste()
	{
		
	}
	
	@Test
	public void testRestauraMapaJogoCategoriasDoArquivoQueNaoExiste()
	{
		
	}
	
	@Test
	public void testRestauraMapaJogoCategoriasDoArquivoQueExiste()
	{
		
	}
	
	@Test
	public void testRestauraListaCategoriasDoArquivoQueNaoExiste()
	{
		
	}
	
	@Test
	public void testRestauraListaCategoriasDoArquivoQueExiste()
	{
		
	}
	
	

}
