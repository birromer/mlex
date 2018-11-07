package mlex;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indice extends FileHandler
{
	String path = "./etc/indice.txt";
	//private static Map //objeto local do indice
	private static File ind = new File("./etc/indice.txt");
	private static Map<Integer, String> categorias = new HashMap<Integer, String>();
	private static List<String> listaCategorias = new ArrayList<String>(); 
	
	
	public Indice()
	{
		;
	}
	
	public void adicionaJogoNoIndice(Jogo jogo)
	{
		String categoriasDoJogo = categorias.get(jogo.getIdJogo());
		if (categoriasDoJogo == null)
		{
			categoriasDoJogo = new String(new char[listaCategorias.size()]).replace("\0", "0");
		}

		try 
		{
			PrintWriter escrevedor  = new PrintWriter(path, "UTF-8");
			String linhaIndiceAdicionada = jogo.toString() + ',' + categorias.get(jogo.getIdJogo() + '\n');
			escrevedor.println(linhaIndiceAdicionada);
			escrevedor.close();
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
		 try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(ind), 1024)) {

		        byte[] c = new byte[1024];
		        boolean empty = true,
		                lastEmpty = false;
		        long count = 0;
		        int read;
		        while ((read = is.read(c)) != -1) {
		            for (int i = 0; i < read; i++) {
		                if (c[i] == '\n') {
		                    count++;
		                    lastEmpty = true;
		                } else if (lastEmpty) {
		                    lastEmpty = false;
		                }
		            }
		            empty = false;
		        }

		        if (!empty) {
		            if (count == 0) {
		                count = 1;
		            } else if (!lastEmpty) {
		                count++;
		            }
		        }

		        return count;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
