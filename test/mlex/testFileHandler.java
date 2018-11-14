package mlex;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import mlex.Jogo;

public class testFileHandler
{
	Jogo jogoTeste;
	
	@Before
	public void setUp()
	{
		jogoTeste = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
		File arquivoTeste = new File("./etc/teste");
	}

	
	@Test
	public void testVerificaArquivo()
	{
		assertFalse(Jogo.verificaArquivo(jogoTeste.getIdJogo()));
		//adiciona arquivo
		//testa de novo
	}
	
	
	@Test
	public void testEscreveArquivo()
	{
		
	}

}
