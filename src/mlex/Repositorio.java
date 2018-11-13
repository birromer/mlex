package mlex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repositorio extends FileHandler
{
	private static List<Jogo> listaJogosObj = new ArrayList<Jogo>();
	private static Map<String, Integer> tabelaJogos = new HashMap<String, Integer>();
	private java.io.Console cnsl = System.console();
	private static Indice indice =  new Indice();
	
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
		
		indice.novoJogoSendoAdicionado(idNovoJogo);
		
		return idNovoJogo;
	}
		
	public int tamanho()
	{
		return listaJogosObj.size();
	}

}
