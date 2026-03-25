package app;

import java.sql.SQLException;
import java.util.Scanner;

public class Inicial {

	public static void exibir() throws SQLException {
		Scanner sc = Main.sc;
		int opcao = 0;

		while (opcao != 9) {
			System.out.println("   __   ___ _   _____     ________   ___ ");
			System.out.println("  / /  / _ | | / / _ |   / ___/ _ | / _ \\");
			System.out.println(" / /__/ __ | |/ / __ |  / /__/ __ |/ , _/");
			System.out.println("/____/_/ |_|___/_/ |_|  \\___/_/ |_/_/|_| ");
			System.out.println("                                          \n");

			System.out.println("--MENU--");
			System.out.println("1 - Nova Ordem de Serviço");
			System.out.println("2 - Cadastros");
			System.out.println("3 - Registros");
			System.out.println("4 - Consultar Informações");
			System.out.println("\n\n9 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:

				break;

			case 2:
				app.menus.Cadastros.Cadastrar();
				break;

			case 3:

				break;

			case 4:

				break;

			case 9:
				System.exit(0);
				break;

			default:
				System.out.println("Opção inválida.");
				System.out.println("\n\nPressione enter para continuar...");

				sc.nextLine();
				sc.nextLine();
			}
		}
	}

}
