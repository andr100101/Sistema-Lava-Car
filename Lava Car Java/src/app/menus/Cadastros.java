package app.menus;
import dao.*;
import java.sql.SQLException;
import java.util.Scanner;
import app.*;

public class Cadastros {
	public static void Cadastrar() throws SQLException {
		Scanner sc = Main.sc;
		ClienteDAO daoCliente = new ClienteDAO();
		CarroDAO daoCar = new CarroDAO();
		int opcao = 0;
		while (opcao != 9) {
			System.out.println("--CADASTRO--");
			System.out.println("1 - Novo cliente");
			System.out.println("2 - Novo carro");
			System.out.println("3 - Atualizar cliente");
			System.out.println("4 - Deletar cliente");
			System.out.println("5 - Deletar carro");
			System.out.println("\n9 - Voltar");

			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				app.menus.cadastros.novoCliente.cadastrar();
				break;
			case 2:
				app.menus.cadastros.novoCarro.cadastrar();
				break;
			case 3:
				ClienteDAO.atualizarCliente();
				break;
			case 4:
				daoCliente.deletar();
				break;
			case 5:
				daoCar.deletar();
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
