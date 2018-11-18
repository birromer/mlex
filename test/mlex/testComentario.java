package mlex;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class testComentario {
	Comentario comentario0;
	Comentario comentario1;
	
	@Before
	public void setUp() {
		comentario0 = new Comentario("Jogo ok.", 0);
		comentario1 = new Comentario("Jogo maravilhoso",2,3.5f);
	}
	
	@Test
	public void testeArquivoComentario() {
		
		//salva comentario 0 duas vezes
		comentario0.salvaComentario();
		comentario0.salvaComentario();
		comentario0.salvaComentario();
		
		//verifica se comentario esta correto
		try {
			FileReader fr = new FileReader(comentario0.getPath());
			BufferedReader r = new BufferedReader(fr);
			assertTrue( r.readLine() != null);
			r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comentario1.salvaComentario();
		try {
			FileReader fr = new FileReader(comentario1.getPath());
			BufferedReader r = new BufferedReader(fr);
			assertTrue( r.readLine() != null);
			r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testeExibicao() {
		comentario0.exibeComentarios();
	}

	@Test
	public void testeRemocao() {
		comentario0.removeComentarios();
	}
}


