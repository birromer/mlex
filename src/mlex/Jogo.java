package mlex;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Jogo extends FileHandler {
	private int idJogo;
	private String nomeJogo; 
	private String lancamento; 
	private String desenvolvedor;
	private String versao = "valor indexistente";
	private String genero = "valor indexistente";
	private Comentario comentarios;
	Scanner scanner = new Scanner(System.in);
	
	
	public Jogo(int id, String nomeJogo, String lancamento, String desenvolvedor)
	{
		this.idJogo = id;
		this.nomeJogo = nomeJogo; 
		this.lancamento = lancamento; 
		this.desenvolvedor = desenvolvedor;
	}
	
	public String getNomeJogo()
	{
		return this.nomeJogo;
	}
	
	public int getIdJogo()
	{
		return this.idJogo;
	}
	
	public List<String> retornaListaAtributosRelevantes()
	{
		String[] atributosRelevantes = new String[] {(Integer.toString(this.idJogo)), this.nomeJogo, this.lancamento, this.desenvolvedor};
		List<String> listaAtributosRelevantes= Arrays.asList(atributosRelevantes);
		return listaAtributosRelevantes;
	}
	
	public void atualizaAtributos(int opcao, String atributoAtualizado)
	{
		switch (opcao)
		{
			case 1:
				this.nomeJogo = atributoAtualizado;
			case 2:
				this.lancamento = atributoAtualizado;
			case 3:
				this.desenvolvedor = atributoAtualizado;
			case 4:
				this.versao = atributoAtualizado;
			case 5:
				this.genero = atributoAtualizado;
		}
		
		//salva objeto no arquivo
	}
	
	
	
	@Override
	public String toString()
	{
		//refazer para ficar bonito
		return ( Integer.toString(this.idJogo) + ',' + this.nomeJogo + ',' + this.lancamento + ',' + this.desenvolvedor); 
	}
	
	public void addComentario(String texto) {
		this.comentario = new Comentario(texto, this.idJogo);
		comentario.salvaComentario();
	}
	
	public void addComentario(String texto, float nota) {
		this.comentario = new Comentario("",this.idJogo);
		this.comentario = new Comentario(texto, this.idJogo, nota);
		this.comentario.salvaComentario();
	}
	
	public void removeComentarios() {
		this.comentario = new Comentario("",this.idJogo);
		this.comentario.removeComentarios();
	}
	
	public void exibeComentarios() {
		this.comentario = new Comentario("",this.idJogo);
		this.comentario.exibeComentarios();
	}
	

}
