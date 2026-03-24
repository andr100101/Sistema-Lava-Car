package app.menus.cadastros;
import java.sql.SQLException;
import java.util.Scanner;

import app.Main;
import models.*;
import dao.*;

public class novoCliente {
	public static void cadastrar() {
		Scanner sc = Main.sc;
		Cliente c = new Cliente();
		String nome;
		String whats;
		System.out.println("--NOVO CLIENTE--");
		String confirm;
		
		while (true) {
            System.out.println("Insira o nome do novo cliente: ");
            nome = sc.nextLine();

            System.out.println("O nome do cliente " + nome + " está correto? (Y/n)");
            confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                c.setNome(nome);
                break;
            } else if (!confirm.equalsIgnoreCase("N")) {
                System.out.println("Insira um valor válido!\n");
            }
        }
		
		while (true) {
            System.out.println("Insira o número de WhatsApp do Cliente (EX: 42XXXXXXXXX):");
            whats = sc.nextLine();
            
            if (!whats.matches("\\d{10,11}")) {
                System.out.println("Número inválido! Digite 10 ou 11 dígitos.\n");
                continue;
            }
            
            System.out.println("O WhatsApp " + whats + " está correto? (Y/n)");
            confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                c.setWhats(whats);
                break;
            } else if (!confirm.equalsIgnoreCase("N")) {
                System.out.println("Insira um valor válido!\n");
            }
        }
		
		
		ClienteDAO dao = new ClienteDAO();

		try {
		    int id = dao.inserir(c);
		    System.out.println("Cliente " + nome + " cadastrado com ID: " + id);
		} catch (SQLException e) {
		    System.out.println("Erro ao cadastrar cliente!");
		    e.printStackTrace();
		}

	}
}
