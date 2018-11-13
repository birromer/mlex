package test;

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
		assertTrue(usuarioCommand.menuInicial()==0);
		assertTrue(usuarioCommand.menuInicial()==1);

	}
	
	/*public void testUsuarioCommandMenuCategorias()
	{
		assertTrue(usuarioCommand.menuCategorias()==0);
	}*/

}
