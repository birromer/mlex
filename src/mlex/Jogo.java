package mlex;

import java.io.File;
import java.util.Date;

public class Jogo extends FileHandler {
	private int id;
	private String nomeJogo; 
	private String lancamento; 
	private String desenvolvedor;
	private double tamanho;
	private double nota;
	private String versao;
	private String genero;
	private String comentarios;
	private int tempoJogado;
	
	public Jogo(int id, String nomeJogo, String lancamento, String desenvolvedor)
	{
		this.id = id;
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
		return this.id;
	}
	
	@Override
	public String toString()
	{
		return ( Integer.toString(this.id) + ',' + this.nomeJogo + ',' + this.lancamento + ',' + this.desenvolvedor); 
	}
	

}
