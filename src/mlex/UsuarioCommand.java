package mlex;

import java.io.IOException;

public class UsuarioCommand
{		
	public static final int CHAR_TO_INT = 48;
	private static Repositorio repositorio = new Repositorio();
	
	public int menuIniciar()
	{
		int opcaoMenu = 0;
		System.out.println("Escolha a acao que deseja realizar:\n"
				+ "0)Mostrar os jogos do repositorio;\n"
				+ "1)Ver informacoes de um jogo;\n"
				+ "2)Adicionar um jogo ao repositorio;\n"
				+ "3)Remover um jogo do repositorio;\n"
				+ "4)Modificar um jogo do repositorio;\n"
				+ "5)Menu de Categorias;\n"
				+ "6)Filtrar jogos;\n"
				+ "7)Configuracoes do usuario;\n"
				+ "8)Sair;");
		
		try
		{
			opcaoMenu = System.in.read() - CHAR_TO_INT;
		}
		catch (IOException e)
		{
			System.out.println("erro de leitura do teclado");
			e.printStackTrace();
		}
		
		switch(opcaoMenu)
		{
			case 0:
				//ve os jogo
				break;
			case 1:
				//ve um jogo
				break;
			case 2:
				//add um jogo
				break;
			case 3:
				//remove um jogo
				break;
			case 4:
				//modifica um jogo
				break;
			case 5:
				//cetegs
				break;
			case 6:
				//filtroo
				break;
			case 7:
				//configs
				break;
			case 8:
				//encerra o programa
				break;
			default: System.out.println("Nao eras, meu bruxo!");
		
		}
		System.out.println(opcaoMenu);
		
		return opcaoMenu;
	}

}
