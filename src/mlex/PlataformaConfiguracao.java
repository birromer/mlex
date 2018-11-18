package mlex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class PlataformaConfiguracao extends FileHandler
{
	private Scanner scanner = new Scanner(System.in);
	private Properties configuracoes = new Properties();
	private String usuario = "admin";
	private String senha = "admin";
	private String emailDoUsuario = "admin@mlex";
	private String ordenacao = "n";
	
	
	public PlataformaConfiguracao() 
	{
		inicializaConfiguracao();
	}
	
	public void inicializaConfiguracao()
	{
		if (new File("./.mlex.conf").exists() == false)
		{
			configuracoes.setProperty("usuario", "admin");
			configuracoes.setProperty("senha", "admin");
			configuracoes.setProperty("ordenacao", "n");
			configuracoes.setProperty("emailDoUsuario", "admin@mlex");
			
			try
			{
				configuracoes.store(new FileOutputStream("./.mlex.conf"), null);
			}
			catch (IOException e) 
			{
				System.out.println("Problema escrevendo arquivo de configuracao default");
			}
		}
		else
		{
			this.restauraConfiguracaoDoUsuario();
		}
	}
	
	public void restauraConfiguracaoDoUsuario()
	{
		try
		{
			configuracoes.load(new FileInputStream("./.mlex.conf"));
		}
		catch (IOException e)
		{
			System.out.println("Problema na leitura do arquivo de configuracao");
		}
		
		this.usuario = configuracoes.getProperty("usuario");
		this.senha = configuracoes.getProperty("senha");
		this.ordenacao = configuracoes.getProperty("ordenacao");	
		this.emailDoUsuario = configuracoes.getProperty("emailDoUsuario");
	}
	
	public boolean validacaoUsuario()
	{
		System.out.println("Digite a sua senha para validar a operacao: ");
		String pw = scanner.nextLine();
		return(this.getSenha().equals(pw));
	}
	
	public String getUsuario() 
	{
		return this.usuario;
	}
	public String getSenha() 
	{
		return this.senha;
	}
	public String getEmailDoUsuario() 
	{
		return this.emailDoUsuario;
	}
	public String getOrdenacao() 
	{
		return this.ordenacao;
	}
	
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}
	public void setEmailDoUsuario(String emailDoUsuario) 
	{
		this.emailDoUsuario = emailDoUsuario;
	}
	public void setOrdenacao(String ordenacao) 
	{
		this.ordenacao = ordenacao;
	}


}
