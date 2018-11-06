package mlex;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

abstract public class FileHandler
{
	static String path = "./etc/";
	
	static public boolean verificaArquivo(int nome)
	{
		File arquivo =  new File(path + nome);
		
		return arquivo.exists();
	}
	
	static public Object leArquivo(int nome)
	{
		File arquivo =  new File(path + nome);
		try
		{
			FileInputStream fileIn = new FileInputStream(arquivo);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		
			Object obj = objectIn.readObject();
			
			objectIn.close();
			
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public File buscaArquivo(int nome, int op)
	{
		return null;
	}
	
	public void atualizaArquivo(int nome, int op)
	{
		
	}
	
}
