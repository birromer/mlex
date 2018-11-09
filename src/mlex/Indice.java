package mlex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indice extends FileHandler
{
	String path = "./etc/indice.txt";
	private Map<Integer, String> indiceLocal = new HashMap<Integer, String>(); //objeto local do indice
	private File ind = new File("./etc/indice.txt"); //arquivo do indice
	private static Map<Integer, String> mapaJogoCategorias = new HashMap<Integer, String>(); //dicionario que relaciona id do jogo com string contendo mapaJogoCategorias relacionadas a ele
	private List<String> listaCategorias = new ArrayList<String>(); //lista de mapaJogoCategorias disponiveis
	
	
	public Indice()
	{
		;
	}
	
	public void adicionaJogoNoIndice(Jogo jogo) throws Exception
	{
		if (this.testaJogoNoIndice(jogo.getIdJogo()) == true)
		{
			System.out.println("Jogo ja existe no indice, sera ignorado");
			throw new Exception("Jogo ja existe");
		} 
		
		String linhaIndiceAdicionada = jogo.toString() + ',' + this.getCategorias(jogo.getIdJogo()) + '\n';
		indiceLocal.put(jogo.getIdJogo(), linhaIndiceAdicionada);
	}
	
	public void modificaJogoNoIndice(int id, Jogo jogo)
	{
		;
	}
	
	public void deletaJogoNoIndice(int id)
	{
		;
	}
	
	public String getCategorias(int id)
	{
		String categs = mapaJogoCategorias.get(id);
		if ( categs == null)
		{
			if (listaCategorias.size() == 0)
			{
				return "0";
			}
			else
			{
				return new String(new char[listaCategorias.size()]).replace("\0", Integer.toString(0));
			}
		}
		else
		{
			return categs;
		}
	}
	
	public int getPosicaoCategoria(String categoria)
	{
		return listaCategorias.indexOf(categoria);
	}
	
	public boolean testaCategoria(int id, String categoria)
	{
		int posicao = this.getPosicaoCategoria(categoria);
		String categoriasDoJogo = this.getCategorias(id);
		
		return(categoriasDoJogo.charAt(posicao) == '1');
	}
	
	public int getNumeroJogos()
	{
		 return indiceLocal.size();
	}
	
	public int getNumeroCategorias()
	{
		return listaCategorias.size();
	}
	
	public boolean testaJogoNoIndice(int id)
	{
		return indiceLocal.containsKey(id);
	}
	
	public void adicionaCategoriaAoIndice(String novaCategoria)
	{
		if (listaCategorias.contains(novaCategoria) == true)
		{
			return;
		}
		
		listaCategorias.add(novaCategoria);
		
		for (Integer key : mapaJogoCategorias.keySet())
		{
			if (this.getNumeroCategorias() > 1)
			{
				String novaListaCategorias = mapaJogoCategorias.get(key) + "0";
				mapaJogoCategorias.replace(key, novaListaCategorias);
			}
		}
	}
	
	public List<String> getListaCategorias()
	{
		return listaCategorias;
	}
	
	public Map<Integer, String> getMapaCategorias()
	{
		return mapaJogoCategorias;
	}
	
	public void adicionaCategoriaAoJogo(int id, String categoria) throws Exception
	{
		if (listaCategorias.contains(categoria) == false)
		{
			throw new Exception();
		}
		
		int posicaoCategoria = this.getPosicaoCategoria(categoria);
		String listaCategoriasDoJogo = mapaJogoCategorias.get(id);
		char[] listaAtualizadaDeCategoriasDoJogo = listaCategoriasDoJogo.toCharArray();
		listaAtualizadaDeCategoriasDoJogo[posicaoCategoria] = '1';
		mapaJogoCategorias.replace(id, String.valueOf(listaAtualizadaDeCategoriasDoJogo));
			
	}
	
	public void removeCategoriaDoJogo(int id, String categoria)
	{
		if (listaCategorias.contains(categoria) == false)
		{
			return;
		}
		
		int posicaoCategoria = this.getPosicaoCategoria(categoria);
		String listaCategoriasDoJogo = mapaJogoCategorias.get(id);
		char[] listaAtualizadaDeCategoriasDoJogo = listaCategoriasDoJogo.toCharArray();
		listaAtualizadaDeCategoriasDoJogo[posicaoCategoria] = '0';
		mapaJogoCategorias.replace(id, String.valueOf(listaAtualizadaDeCategoriasDoJogo));
	}
	
	public void novoJogoSendoAdicionado(int id)
	{
		mapaJogoCategorias.put(id, "0");
	}
}
