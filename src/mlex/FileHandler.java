package mlex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

abstract public class FileHandler
{
	static String path = "./etc/";
	private transient FileOutputStream saidaArquivoEscrita;
	private transient ObjectOutputStream saidaObjetoEscrita;
	File arquivoUsado;
	
	static public boolean verificaArquivo(int nome)
	{
		File arquivo =  new File(path + nome);
		
		return arquivo.exists();
	}
	
	public Object leArquivo(String nome, String caminhoParaArquivo)
	{
		File arquivo =  new File(caminhoParaArquivo + nome);
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
	
	public void salvaObjetoEmArquivo (Object objeto, String caminhoParaArquivo)
	{
		arquivoUsado = new File(caminhoParaArquivo);
		
		if (arquivoUsado.exists() == false)
		{
			try
			{
				arquivoUsado.createNewFile();
			}
			catch (IOException e)
			{
				System.out.println("erro ao criar arquivo indice");
			}
		}
		
		try
		{
			saidaArquivoEscrita = new FileOutputStream(caminhoParaArquivo);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("arquivo nao encontrado");
		}
		
		try
		{
			saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
			saidaObjetoEscrita.writeObject(objeto);
		}
		catch (IOException e)
		{
			System.out.println("erro ao escrever objeto no arquivo");
			e.printStackTrace();
		}
		
		try
		{
			saidaArquivoEscrita.close();
		}
		catch (IOException e)
		{
			System.out.println("erro ao fechar stream de saida do arquivo");
		}

	}
	
	public File buscaArquivo(int nome, int op)
	{
		return null;
	}
	
}
