package mlex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mlex.UsuarioCommand;

public class TestUsuarioCommand
{
	UsuarioCommand usuarioCommand;
	
	@Before
	public void setUp() throws Exception
	{
		usuarioCommand = new UsuarioCommand();
	}

	@Test
	public void testUsuarioCommandMenuInicial()
	{
		assertTrue(usuarioCommand.menuInicial(0)==0);
		assertTrue(usuarioCommand.menuInicial(666)==666);
	}
	
	public void testUsuarioCommandMenuCategorias()
	{
		assertTrue(usuarioCommand.menuCategorias(0)==0);
		assertTrue(usuarioCommand.menuCategorias(5)==5);
	}
	
	public void testUsuarioCommandMenuJogo()
	{
		assertTrue(usuarioCommand.menuJogo(0)==0);
		assertTrue(usuarioCommand.menuJogo(5)==5);
	}
	
	

}
