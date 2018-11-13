package mlex;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
	private static UsuarioCommand usuario = new UsuarioCommand();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		int opcaoMenu = -1;
		do
		{
			usuario.menuInicial(opcaoMenu);
			opcaoMenu = scanner.nextInt();
		} while (opcaoMenu != 666);
	}

}
