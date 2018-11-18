package mlex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PlataformaConfiguracao extends FileHandler
{
	private Properties configuracoes = new Properties();
	private String usuario = "admin";
	private String senha = "admin";
	private String emailDoUsuario;
	private String ordenacao = "n";
	
	
	public PlataformaConfiguracao() {
		inicializaConfiguracaoDoUsuario();
	}
	
	public void inicializaConfiguracaoDoUsuario()
	{
		configuracoes.setProperty("usuario", "admin");
		configuracoes.setProperty("senha", "admin");
		configuracoes.setProperty("ordenado", "n");
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
	
	public boolean validacaoUsuario(String pw)
	{
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
