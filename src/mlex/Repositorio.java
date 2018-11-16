package mlex;

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
	private static Indice indice =  new Indice();
	private Scanner scanner = new Scanner(System.in);
	
	private int idNovoJogo;
	private String nomeNovoJogo;
	private String lancamentoNovoJogo;
	private String desenvolvedorNovoJogo;
	
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
			return tabelaJogos.size(); //verifica arquivo pelo ultimo id usado
		}
	}
	
	public void getInformacoesJogo()
	{
		nomeNovoJogo = cnsl.readLine("Nome do jogo a ser adicionado: ");
		lancamentoNovoJogo = cnsl.readLine("Data de lancamento do jogo a ser adicionado (DD/MM/AAAA): ");
		desenvolvedorNovoJogo = cnsl.readLine("Desenvolvedor do jogo a ser adicionado: ");
	}
	
	public void criaJogo(Jogo jogo)
	{
		listaJogosObj.add(jogo);
	}
	
	public int adicionaJogo()
	{
		idNovoJogo = indice.getIdComNome(nomeNovoJogo);
		
		
		if (indice.getIdsDoIndice().contains(idNovoJogo))
		{
			System.out.println("tentativa de adicao de novo jogo falhou pois jogo ja existe");
		}
		else
		{
			Jogo novoJogo = new Jogo(idNovoJogo, nomeNovoJogo, lancamentoNovoJogo, desenvolvedorNovoJogo);
			tabelaJogos.put(nomeNovoJogo, idNovoJogo);
		
			criaJogo(novoJogo);
		
			try
			{
				indice.adicionaJogoNoIndice(novoJogo);
			}
			catch (Exception e)
			{
				System.out.println("falha ao adicionar novo jogo no indice");
			}
		
			indice.novoJogoSendoAdicionado(idNovoJogo);
		}
		
		return idNovoJogo;
	}
	
	public void adicionaJogoPassaTeste(Jogo novoJogo)
	{
		try 
		{
			indice.adicionaJogoNoIndice(novoJogo);
		}
		catch (Exception e)
		{
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
				if(resultados == -1)
				{
					System.out.println("\nNenhum jogo encontrado, verifique se o nome da colecao foi digitado corretamente.");
					return resultados;
				}
				if(resultados == 0)
				{
					System.out.println("Nao ha jogos na colecao selecionada.");
					return resultados;
				}
				System.out.println("\nResultados filtrados por colecao '" + nomeDeCategoria + "': ");
				indice.imprimeAlgunsJogos(ids);
				break;
//			case 1:
//				//com subfiltro
				//menu do filtro
//				ids = indice.filtroPorAtributos(nomeOpcaoDeBusca, opcaoDeBusca)
//				int resultados = indice.filtroPorCategoria(nomeDeCategoria, ids);
//				if(resultados == -1)
//				{
//					System.out.println("\nNenhum jogo encontrado, verifique se o nome da colecao foi digitado corretamente.");
//					return ids;
//				}
//				if(resultados == 0)
//				{
//					System.out.println("Nao ha jogos na colecao selecionada.");
//					return ids;
//				}
//				System.out.println("\nResultados filtrados por colecao '" + nomeDeCategoria + "': ");
//				indice.imprimeAlgunsJogos(ids);
//				break;
		}
		return resultados;
	}
	
	
	public void criaCateg(String nomeCateg)
	{
		indice.adicionaCategoriaAoIndice(nomeCateg);
	}
	
	public void addJogoNaCateg(Jogo jogo, String nomeCateg)
	{
		try {
			indice.adicionaCategoriaAoJogo(jogo.getIdJogo(), nomeCateg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int tamanho()
	{
		return listaJogosObj.size();
	}
	
<<<<<<< HEAD
	public void addComentarioEmJogo(int jogoId, String txt){
		for (Jogo j: this.listaJogosObj) {
			if (j.getIdJogo() == jogoId){
				j.addComentario(txt);
				break;
			}
		}
	}
	
	public void addComentarioEmJogo(int jogoId, String txt, float nota){
		for (Jogo j: this.listaJogosObj) {
			if (j.getIdJogo() == jogoId){
				j.addComentario(txt, nota);
				break;
			}
		}
		
	}
	
	public void removeComentariosDeJogo() {};
	

	public void exibeComentariosDeJogo() {};
}
