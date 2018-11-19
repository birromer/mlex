package mlex;

import java.io.File;
import java.util.Scanner;

public class Main
{
	private static UsuarioCommand usuario = new UsuarioCommand();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		criaDiretorios(); //cria diretorios /etc/ e /etc/jogos/ caso nao existam
		
		int opcaoMenu = -1;
		do
		{
			usuario.menuInicial(opcaoMenu);
			opcaoMenu = scanner.nextInt(); //pega input do usuario sobre como proceder dentro do menu inicial
			scanner.nextLine();
		} while (opcaoMenu != 666);
		
		System.out.println("\n\nAte a proxima! :)\n");
	}
	
	/**
	 * Cria diretorios.
	 */
	public static void criaDiretorios()
	{
		File diretorio1 = new File("./etc/");
		File diretorio2 = new File("./etc/jogos/");
		//testa existencia dos diretorios
		if (diretorio1.exists() == false)
		{
			diretorio1.mkdir();
			if (diretorio2.exists() == false)
			{
				diretorio2.mkdir();
			}
		}
	}
}
