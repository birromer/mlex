package mlex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indice extends FileHandler
{
	private static File ind = new File("./etc/indice.txt");
	private static Map<Integer, String> categorias = new HashMap<Integer, String>();
	private static List<String> listaCategorias = new ArrayList<String>(); 
	
	
	public Indice()
	{
		;
	}
	
	public void adicionaJogoNoIndice(Jogo jogo)
	{
		;
	}
	
	public void modificaJogoNoIndice(int id, Jogo jogo)
	{
		;
	}
	
	public void deletaJogoNoIndice(int id)
	{
		;
	}
	
	public String getCategoria(int id)
	{
		return categorias.get(id);
	}
	
	public boolean testaCategoria(int id, String categoria)
	{
		int posicao = listaCategorias.indexOf(categoria);
		String categoriasDoJogo = this.getCategoria(id);
		
		return(categoriasDoJogo.charAt(posicao) == '1');
	}
	
	public int getNumeroJogos()
	{
		return categorias.size();
	}
	
	
}
