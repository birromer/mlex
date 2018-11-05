package mlex;

import java.util.ArrayList;
import java.util.List;

public class Repositorio extends FileHandler
{
	List<Jogo> listaJogosObj = new ArrayList<Jogo>();
	java.io.Console cnsl = System.console();
	
	public Repositorio() 
	{
		;
	}

	public void criaJogo(Jogo jogo)
	{
		listaJogosObj.add(jogo);
	}
	
	public int adicionaJogo()
	{
		//coisas para criar novo jogo
		String nomeNovoJogo = cnsl.readLine("Nome do jogo a ser adicionado: ");
		String lancamentoNovoJogo = cnsl.readLine("Data de lancamento do jogo a ser adicionado (DD/MM/AAAA): ");
		String desenvolvedorNovoJogo = cnsl.readLine("Desenvolvedor do jogo a ser adicionado: ");
		int idNovoJogo = this.tam();
		
		Jogo novoJogo = new Jogo(idNovoJogo, nomeNovoJogo, lancamentoNovoJogo, desenvolvedorNovoJogo);
		
		criaJogo(novoJogo);
		
		return idNovoJogo;
	}
		
	public int tam()
	{
		return listaJogosObj.size();
	}

}
