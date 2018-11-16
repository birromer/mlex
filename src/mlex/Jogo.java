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
	
	public void atualizaAtributos()
	{
		System.out.println("Digite o que deseja modificar");
		System.out.println("1 - nomeJogo"
						+ "2 - lancamento"
						+ "3 - desenvolvedor"
						+ "4 - versao"
						+ "5 - genero");
		int opcao = scanner.nextInt();
		switch (opcao)
		{
			case 1:
				System.out.println("Digite o nome atualizado do jogo:");
				this.nomeJogo = scanner.next();
				break;
			case 2:
				System.out.println("Digite a data atualizada de lancamento do jogo (DD/MM/AAAA):");
				this.lancamento = scanner.next();
				break;
			case 3:
				System.out.println("Digite o nome atualizado do desenvolvedor do jogo:");
				this.desenvolvedor = scanner.next();
				break;
			case 4:
				System.out.println("Digite a versao atualizada  do jogo:");
				this.versao = scanner.next();
				break;
			case 5:
				System.out.println("Digite o genero atualizado do jogo:");
				this.genero = scanner.next();
				break;
			default:
				break;
		}
	}
	
	
	
	@Override
	public String toString()
	{
		//refazer para ficar bonito
		return ( Integer.toString(this.idJogo) + ',' + this.nomeJogo + ',' + this.lancamento + ',' + this.desenvolvedor); 
	}
	

}
