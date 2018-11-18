package mlex;

import java.io.File;
import java.util.Scanner;

public class Main
{
	private static UsuarioCommand usuario = new UsuarioCommand();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		criaDiretorios();
		
		int opcaoMenu = -1;
		do
		{
			usuario.menuInicial(opcaoMenu);
			opcaoMenu = scanner.nextInt();
			scanner.nextLine();
		} while (opcaoMenu != 666);
	}
	
	public static void criaDiretorios()
	{
		File diretorio1 = new File("./etc/");
		File diretorio2 = new File("./etc/jogos/");
		
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
