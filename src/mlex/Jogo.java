package mlex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo extends FileHandler{
	private int idJogo;
	private String nomeJogo; 
	private String lancamento; 
	private String desenvolvedor;
	private String versao;
	private String genero = "valor inexistente";
	private Comentario comentario;

	Scanner scanner = new Scanner(System.in);
	String caminhoObjetoJogo;
	
	public Jogo(int id, String nomeJogo, String lancamento, String desenvolvedor)
	{
		this.idJogo = id;
		this.nomeJogo = nomeJogo; 
		this.lancamento = lancamento; 
		this.desenvolvedor = desenvolvedor;
		this.versao = "v1.0";
	}
	
	public String getVersao() {
		return this.versao;
	}
	
	public void setVersao(String novaVersao) {
		this.versao = novaVersao;
	}
	
	public String getNomeJogo()
	{
		return this.nomeJogo;
	}
	
	public int getIdJogo()
	{
		return this.idJogo;
	}
	
	public String getLancamentoJogo() {
		return this.lancamento;
	}
	
	public String getDesenvolvedorJogo() {
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
		System.out.println(caminhoObjetoJogo);
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
