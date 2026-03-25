package app.menus.cadastros;

import java.sql.SQLException;
import java.util.Scanner;

import app.Main;
import models.*;
import dao.*;

public class novoCliente {
	public static void cadastrar() throws SQLException {
		Scanner sc = Main.sc;
		Cliente c = new Cliente();
		String nome;
		String whats;
		System.out.println("--NOVO CLIENTE--");
		sc.nextLine();

		while (true) {
			System.out.println("Insira o nome do novo cliente: ");
			nome = sc.nextLine().trim();

			if (nome.isEmpty()) {
				System.out.println("O nome não pode ser vazio");
				continue;
			}
			System.out.println("O nome '" + nome + "' está correto? (Y/n)");
			String confirm = sc.nextLine();

			if (confirm.equalsIgnoreCase("Y")) {
				c.setNome(nome);
				break;
			}
		}

		while (true) {
			System.out.println("Insira o número de WhatsApp do Cliente (EX: 42XXXXXXXXX):");
			whats = sc.nextLine();

			if (whats.isEmpty()) {
				System.out.println("O telefone do usuário não pode ser vazio");
				continue;
			}
			if (!whats.matches("\\d{10,11}")) {
				System.out.println("Número inválido! Digite 10 ou 11 dígitos.\n");
				continue;
			}

			System.out.println("O WhatsApp " + whats + " está correto? (Y/n)");
			String confirm = sc.nextLine();

			if (confirm.equalsIgnoreCase("Y")) {
				c.setWhats(whats);
				break;
			} else if (!confirm.equalsIgnoreCase("N")) {
				System.out.println("Insira um valor válido!\n");
			}
		}
		ClienteDAO dao = new ClienteDAO();
		boolean existe = dao.clienteExiste(nome);
		if (!existe) {
			try {
				dao.inserir(c);
				System.out.println("\n\nCliente " + nome + " cadastrado com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro ao cadastrar cliente!");
				e.printStackTrace();
			}
		} else {
			System.out.println("Cliente já cadastrado no banco.\nRegistre um novo cliente");
		}

	}
}
