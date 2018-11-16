package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mlex.Indice;
import mlex.Jogo;
import mlex.Repositorio;

public class TestFiltro {
	Repositorio repoTeste;
	Jogo jogoTeste1;
	Jogo jogoTeste2;
	Jogo jogoTeste3;
	
	@Before
	public void setUp()
	{
		repoTeste = new Repositorio();
		jogoTeste1 = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
		jogoTeste2 = new Jogo(666, "Jogo Chato", "01/09/94", "Despacito");
		jogoTeste3 = new Jogo(3, "Aladdin", "16/08/94", "Despacito");
	}

	@Test
	public void testGetIdParaVisualizarInfoDeJoProcuradogoEspecificoQueNaoExiste()
	{
		assertTrue(repoTeste.getIdParaVerInfoDeJogo("algum jogo") == -1);
	}
	
	@Test
	public void testVisualizaInfoDeJogoEspecificoQueExiste()
	{
		repoTeste.adicionaJogoPassaTeste(jogoTeste1);
		assertTrue(repoTeste.getIdParaVerInfoDeJogo(jogoTeste1.getNomeJogo()) == jogoTeste1.getIdJogo());
		repoTeste.adicionaJogoPassaTeste(jogoTeste2);
		assertTrue(repoTeste.getIdParaVerInfoDeJogo(jogoTeste2.getNomeJogo()) == jogoTeste2.getIdJogo());
		repoTeste.adicionaJogoPassaTeste(jogoTeste3);
		assertTrue(repoTeste.getIdParaVerInfoDeJogo(jogoTeste3.getNomeJogo()) == jogoTeste3.getIdJogo());
	}
	
	@Test
	public void testGetJogosPorNome()
	{
		repoTeste.adicionaJogoPassaTeste(jogoTeste1);
		repoTeste.adicionaJogoPassaTeste(jogoTeste2);
		repoTeste.adicionaJogoPassaTeste(jogoTeste3);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Jogo Chato", 1).size() == 1);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Jogo Legal", 1).size() == 1);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Aladdin2", 1).size() == 0);
		
	}
	
	@Test
	public void testGetJogosPorLancamento()
	{
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Jogo Chato", 2).size() == 0);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("16/08/94", 2).size() == 1);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("01/09/94", 2).size() == 2);		
	}
	
	@Test
	public void testGetJogosPorDesenvolvedor()
	{
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Jogo Chato", 3).size() == 0);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Klei", 3).size() == 1);
		assertTrue(repoTeste.filtroPorAtributoDoJogo("Despacito", 3).size() == 2);
	}
	
	@Test
	public void testGetJogosPorCategoriaSemSubFiltro()
	{
		repoTeste.criaCateg("Jogos do verao passado");
		repoTeste.criaCateg("Jogos do inverno passado");
		repoTeste.criaCateg("Jogos do equinocio passado");
		repoTeste.addJogoNaCateg(jogoTeste1, "Jogos do verao passado");
		repoTeste.addJogoNaCateg(jogoTeste2, "Jogos do verao passado");
		repoTeste.addJogoNaCateg(jogoTeste2, "Jogos do equinocio passado");
//		System.out.println("\nfiltro por colecao 'Jogo Chato':");
		assertTrue(repoTeste.filtroDasCategorias("Jogo Chato", 0) == -1);
//		System.out.println("\nfiltro por colecao 'Jogos do inverno passado':");
		assertTrue(repoTeste.filtroDasCategorias("Jogos do inverno passado", 0) == 0);
//		System.out.println("\nfiltro por colecao 'Jogos do equinocio passado':");
		assertTrue(repoTeste.filtroDasCategorias("Jogos do equinocio passado", 0) == 1);
//		System.out.println("\nfiltro por colecao 'Jogos do verao passado':");
		assertTrue(repoTeste.filtroDasCategorias("Jogos do verao passado", 0) == 2);
	}
	

	
	
	

}
