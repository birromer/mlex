package mlex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indice extends FileHandler
{
	String path = "./etc/indice.txt";
	private Map<Integer, String> indiceLocal = new HashMap<Integer, String>(); //objeto local do indice
	private static File ind = new File("./etc/indice.txt");
	private static Map<Integer, String> categorias = new HashMap<Integer, String>();
	private static List<String> listaCategorias = new ArrayList<String>(); 
	
	
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
		
		
		String categoriasDoJogo = categorias.get(jogo.getIdJogo());
		if (categoriasDoJogo == null)
		{
			categoriasDoJogo = new String(new char[listaCategorias.size()]).replace('\0', '0');
		}

		try 
		{
			Writer saida = new BufferedWriter(new FileWriter(path));
			String linhaIndiceAdicionada = jogo.toString() + ',' + categorias.get(jogo.getIdJogo() + '\n');
			saida.append(linhaIndiceAdicionada);
			saida.close();
			indiceLocal.put(jogo.getIdJogo(), linhaIndiceAdicionada);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
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
		 return indiceLocal.size();
	}
	
	public boolean testaJogoNoIndice(int id)
	{
		return indiceLocal.containsKey(id);
	}
}
