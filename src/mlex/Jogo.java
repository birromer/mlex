package mlex;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo extends FileHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idJogo;
	private String nomeJogo; 
	private String lancamento; 
	private String desenvolvedor;
	private String versao = "v1.0";
	private String genero = "valor inexistente";
	private Comentario comentarios;
	String caminhoObjetoJogo;
	
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
	
	public String getLancamento()
	{
		return this.lancamento;
	}
	
	public String getDesenvolvedor()
	{
		return this.desenvolvedor;
	}
	
	public List<String> retornaListaAtributosRelevantes()
	{
		String[] atributosRelevantes = new String[] {(Integer.toString(this.idJogo)), this.nomeJogo, this.lancamento, this.desenvolvedor};
		List<String> listaAtributosRelevantes= Arrays.asList(atributosRelevantes);
		return listaAtributosRelevantes;
	}
	
	public Jogo atualizaAtributos(int opcao, String atributoAtualizado)
	{
		switch (opcao)
		{
			case 1:
				nomeJogo = atributoAtualizado;
			case 2:
				lancamento = atributoAtualizado;
			case 3:
				desenvolvedor = atributoAtualizado;
			case 4:
				versao = atributoAtualizado;
			case 5:
				genero = atributoAtualizado;
		}		
		caminhoObjetoJogo = "./etc/jogos/" + Integer.toString(idJogo);
		this.salvaObjetoEmArquivo(this, caminhoObjetoJogo);
		
		return this;
	}
	
	
	@Override
	public String toString()
	{
		return ("Nome: "+ this.nomeJogo
				+ "Data lancamento: " +this.lancamento
				+ "Desenvolvedor: " + this.desenvolvedor
				+ "Versao: " + this.versao
				+ "Genero: " + this.genero); 
	}
	

}
