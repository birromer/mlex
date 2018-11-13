package mlex;

import java.io.IOException;
import java.util.Scanner;


public class UsuarioCommand
{		
	public static final int CHAR_TO_INT = 48;
	private static Repositorio repositorio = new Repositorio();
	
	public int menuInicial()
	{
		Scanner scanner = new Scanner(System.in);
		int opcaoMenu = 0;
		System.out.println("\n0)Mostrar os jogos do repositorio;\n"
				+ "1)Selecionar jogo;\n"
				+ "2)Adicionar um jogo ao repositorio;\n"
				+ "3)Acessar colecoes;\n"
				+ "4)Filtrar jogos;\n"
				+ "5)Configuracoes do usuario;\n"
				+ "666)Sair;\n"
				+ "Escolha a acao que deseja realizar: ");
		
		opcaoMenu = scanner.nextInt();
		
		switch(opcaoMenu)
		{
			case 0:
				//ver tds jogos
				break;
			case 1:
				//ve um jogo e pode:
				//remove-lo, modifica-lo, adicionar comentario, verificar integridade, enviar por email
				break;
			case 2:
				//add um jogo
				break;
			case 3:
				//cetegs
				break;
			case 4:
				//filtroo
				break;
			case 5:
				//configs
				break;
			case 666:
				//encerra o programa
				break;
			default: System.out.println("Nao eras, meu bruxo!");
		
		}
		
		return opcaoMenu;
	}

}
