package mlex;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import mlex.UsuarioCommand;

public class TestUsuarioCommand
{
	UsuarioCommand usuarioCommand;
	File arquivoConfiguracao; 
	
	@Before
	public void setUp() throws Exception
	{
		arquivoConfiguracao = new File("./.mlex.conf");
		
		if (arquivoConfiguracao.exists())
		{
			arquivoConfiguracao.delete();
		}
	}

	@Test
	public void testUsuarioCommandMenuInicial()
	{
		usuarioCommand = new UsuarioCommand();
		assertTrue(usuarioCommand.menuInicial(0)==0);
		assertTrue(usuarioCommand.menuInicial(666)==666);
	}
	
	@Test
	public void testUsuarioCommandMenuCategorias()
	{
		usuarioCommand = new UsuarioCommand();
		assertTrue(usuarioCommand.menuCategorias(0)==0);
		assertTrue(usuarioCommand.menuCategorias(5)==5);
	}
	
	@Test
	public void testUsuarioCommandMenuJogo()
	{
		usuarioCommand = new UsuarioCommand();
		assertTrue(usuarioCommand.menuJogo(0)==0);
		assertTrue(usuarioCommand.menuJogo(5)==5);
	}
	
	@Test
	public void testCriaArquivoDeConfiguracaoDefault()
	{
		assertFalse(arquivoConfiguracao.exists());
		usuarioCommand = new UsuarioCommand();
		assertTrue(arquivoConfiguracao.exists());
		assertTrue("admin" == usuarioCommand.getUsuario());
		assertTrue("admin" == usuarioCommand.getSenha());
		assertTrue("n" == usuarioCommand.getOrdenacao());
	}
	
	@Test
	public void testAtualizaComArquivoDeConfigsExistente()
	{
		try
		{			
			FileWriter fw = new FileWriter ("./.mlex.conf");
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("senha=testoso");
			bw.newLine();
			bw.write("ordenacao=y");
			bw.newLine();
			bw.write("usuario=mlex");
			bw.newLine();
			bw.close();
		}
		catch(IOException e)
		{
			System.out.println("problema escrevendo arquivo de configuracao para teste");
		}
		
		assertTrue(arquivoConfiguracao.exists());
		usuarioCommand = new UsuarioCommand();
		assertTrue(arquivoConfiguracao.exists());
		
		System.out.println(usuarioCommand.getUsuario());
		
		assertTrue(new String("mlex").equals(usuarioCommand.getUsuario()));
		assertTrue(new String("testoso").equals (usuarioCommand.getSenha()));
		assertTrue(new String("y").equals(usuarioCommand.getOrdenacao()));
	}
	
}
