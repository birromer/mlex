package mlex;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Repositorio extends FileHandler
{
	private static List<Jogo> listaJogosObj = new ArrayList<Jogo>();
	private static Map<String, Integer> tabelaJogos = new HashMap<String, Integer>();
	private java.io.Console cnsl = System.console();
	private Indice indice =  new Indice();
	private Scanner scanner = new Scanner(System.in);
	
	public boolean verificaId(String nomeJogo)
	{
		return tabelaJogos.containsKey(nomeJogo);
	}
	
	private int getId(String nomeJogo)
	{
		if(verificaId(nomeJogo) == true)
		{
			return tabelaJogos.get(nomeJogo);
		}
		else
		{
			return 1; //verifica arquivo pelo ultimo id usado
		}
	}
	
	public void criaJogo(Jogo jogo)
	{
		listaJogosObj.add(jogo);
	}
	
	public int adicionaJogo()
	{
		//coisas com arquivo
		//buscaArquivo()
		
		
		//coisas para criar novo jogo
		String nomeNovoJogo = cnsl.readLine("Nome do jogo a ser adicionado: ");
		String lancamentoNovoJogo = cnsl.readLine("Data de lancamento do jogo a ser adicionado (DD/MM/AAAA): ");
		String desenvolvedorNovoJogo = cnsl.readLine("Desenvolvedor do jogo a ser adicionado: ");
		
		int idNovoJogo = this.tamanho();
		
		Jogo novoJogo = new Jogo(idNovoJogo, nomeNovoJogo, lancamentoNovoJogo, desenvolvedorNovoJogo);
		tabelaJogos.put(nomeNovoJogo, idNovoJogo);
		
		criaJogo(novoJogo);
		
		try {
			indice.adicionaJogoNoIndice(novoJogo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		indice.novoJogoSendoAdicionado(idNovoJogo);
		
		return idNovoJogo;
	}
	
	public void adicionaJogoPassaTeste(Jogo novoJogo)
	{
		try {
			indice.adicionaJogoNoIndice(novoJogo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		indice.novoJogoSendoAdicionado(novoJogo.getIdJogo());
	}
	
	public int getIdParaVerInfoDeJogo(String nomeJogoProcurado)
	{
		int id = -1;
		
		if(indice.getNumeroJogos() > 0)
		{
			id = indice.getIdComNome(nomeJogoProcurado);
		}
		return id;
	}
	
	public List<Integer> filtroPorAtributoDoJogo(String nomeOpcaoDeBusca, int opcaoDeBusca)
	{
		List<Integer> ids = new ArrayList<Integer>();
		
		switch(opcaoDeBusca)
		{
			case 1:
				//nome
				ids = indice.filtroPorAtributos(nomeOpcaoDeBusca, opcaoDeBusca);
				if(ids.size() > 0)
				{
					System.out.println("\nfiltro por jogos com nome '" + nomeOpcaoDeBusca + "': ");
					indice.imprimeAlgunsJogos(ids);
				}
				else
					System.out.println("\nNenhum jogo encontrado, verifique se o nome foi digitado corretamente");
				break;
			case 2:
				//lanc
				ids = indice.filtroPorAtributos(nomeOpcaoDeBusca, opcaoDeBusca);
				if(ids.size() > 0)
				{
					System.out.println("\nfiltro por jogos com data de lancamento '" + nomeOpcaoDeBusca + "': ");
					indice.imprimeAlgunsJogos(ids);
				}
				else
					System.out.println("\nNenhum jogo encontrado, verifique se a data foi digitada corretamente");
				break;
			case 3:
				//desenv
				ids = indice.filtroPorAtributos(nomeOpcaoDeBusca, opcaoDeBusca);
				if(ids.size() > 0)
				{
					System.out.println("\nfiltro por jogos do desenvolvedor '" + nomeOpcaoDeBusca + "': ");
					indice.imprimeAlgunsJogos(ids);
				}
				else
					System.out.println("\nNenhum jogo encontrado, verifique se o nome do desenvolvedor foi digitado corretamente");
				break;
			default: 
				System.out.println("opcao invalida!");
		}
	
		return ids;
	}
	
	public int filtroDasCategorias(String nomeDeCategoria, int opcaoDeBuscaCateg)
	{
		List<Integer> ids = new ArrayList<Integer>();
		int resultados = -1;
		switch(opcaoDeBuscaCateg)
		{
			case 0:
				//sem subfiltro
				ids = indice.getIdsDoIndice();
				resultados = indice.filtroPorCategoria(nomeDeCategoria, ids);
				this.mostraResultadosDoFiltroDeCategorias(resultados, ids, nomeDeCategoria);
				break;
			case 1:
				//com subfiltro
				int opcaoDeSubfiltro = menuFiltro();
				System.out.println("\nDigite o parametro do subfiltro.");
				String nomeOpcaoDeSubfiltro = scanner.next();
				ids = indice.filtroPorAtributos(nomeOpcaoDeSubfiltro, opcaoDeSubfiltro);
				resultados = indice.filtroPorCategoria(nomeDeCategoria, ids);
				
				this.mostraResultadosDoFiltroDeCategorias(resultados, ids, nomeDeCategoria);
				break;
		}
		return resultados;
	}
	
	private int mostraResultadosDoFiltroDeCategorias(int nroDeResultados, List<Integer> idsValidos, String nomeDeCategoria)
	{
		if(nroDeResultados == -1)
		{
			System.out.println("\nNenhum jogo encontrado, verifique se o nome da colecao foi digitado corretamente.");
			return nroDeResultados;
		}
		if(nroDeResultados == 0)
		{
			System.out.println("Nao ha jogos na colecao selecionada.");
			return nroDeResultados;
		}
		System.out.println("\nResultados filtrados por colecao '" + nomeDeCategoria + "': ");
		indice.imprimeAlgunsJogos(idsValidos);
		return nroDeResultados;
	}
	
	public void criaCateg(String nomeCateg)
	{
		indice.adicionaCategoriaAoIndice(nomeCateg);
	}
	
	public void addJogoNaCateg(Jogo jogo, String nomeCateg)
	{
		try
		{
			indice.adicionaCategoriaAoJogo(jogo.getIdJogo(), nomeCateg);
		}
		catch (Exception e)
		{
			System.out.println("falhou em adicionar jogo na categoria");
		}
	}
	
	public int tamanho()
	{
		return listaJogosObj.size();
	}
	
	private int menuFiltro()
	{
		System.out.println("\n1)Filtrar por nome do jogo;\n"
				+ "2)Filtrar data do lancamento do jogo;\n"
				+ "3)Filtrar por nome do desenvolvedor do jogo;\n"
				+ "4)Cancela;\n");
		
		int opcaoDeFiltro = scanner.nextInt();
		
		return opcaoDeFiltro;
	}

}
