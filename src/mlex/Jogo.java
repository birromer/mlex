package mlex;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Jogo extends FileHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idJogo;
	private String nomeJogo; 
	private String lancamento; 
	private String desenvolvedor;
	private String versao;
	private String genero = "valor inexistente";
	private Comentario comentario;
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

	public String getLancamentoJogo()
  {
		return this.lancamento;
	}
	
	public String getDesenvolvedorJogo()
  {
		return this.desenvolvedor;
	}

	public String getGeneroJogo()
	{
		return this.genero;
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
				break;
			case 2:
				lancamento = atributoAtualizado;
				break;
			case 3:
				desenvolvedor = atributoAtualizado;
				break;
			case 4:
				versao = atributoAtualizado;
				break;
			case 5:
				genero = atributoAtualizado;
				break;
		}		
		caminhoObjetoJogo = "./etc/jogos/" + Integer.toString(idJogo);
		this.salvaObjetoEmArquivo(this, caminhoObjetoJogo);
		
		return this;
	}
	
	
	@Override
	public String toString()
	{
		return ("\nNome: "+ this.nomeJogo
				+ "\nData lancamento: " +this.lancamento
				+ "\nDesenvolvedor: " + this.desenvolvedor
				+ "\nVersao: " + this.versao
				+ "\nGenero: " + this.genero); 
	}
	
	public void addComentario(String texto) {
		this.comentario = new Comentario(texto, this.idJogo);
		this.comentario.salvaComentario();
	}
	
	public void addComentario(String texto, float nota) {
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
