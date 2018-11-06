package mlex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

abstract public class FileHandler
{
	String path = "./etc/";
	String extensao = ".txt";
	
	public File abreArquivo(int nome)
	{
		File arquivo;
		try
		{
			arquivo =  new File(path + nome + extensao);
		}
		catch (FileNotFoundException e) 
		{
		    e.printStackTrace();
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
		finally
		{
			return arquivo;
		}
	}

	
	abstract public File buscaArquivo(int nome, int op);
	
	abstract public void atualizaArquivo(int nome, int op);
	
}
