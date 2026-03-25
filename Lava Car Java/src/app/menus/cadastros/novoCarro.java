package app.menus.cadastros;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import models.*;
import app.Main;
import dao.*;

public class novoCarro {
	public static void cadastrar() {
		Scanner sc = Main.sc;
		Carro c = new Carro();
		Marca marcaEscolhida = null;
		Cliente clienteEscolhido = null;
		Modelo modeloEscolhido = null;
		MarcaDAO marcaDAO = new MarcaDAO();
		CarroDAO carroDAO = new CarroDAO();
		try {
			List<Marca> marcas = marcaDAO.listar();
			while (true) {
				System.out.println("Selecione a marca do carro");
				for (Marca m : marcas) {
					System.out.println(m.getIdMarca() + "-" + m.getNome());
				}
				System.out.println("Digite o ID da marca do carro: ");
				int idEscolha = sc.nextInt();
				sc.nextLine();

				for (Marca m : marcas) {
					if (m.getIdMarca() == idEscolha) {
						marcaEscolhida = m;
						break;
					}
				}

				if (marcaEscolhida != null) {
					System.out.println("Marca escolhida : " + marcaEscolhida.getNome());
					break;
				} else {
					System.out.println("Não existe marca com o ID referênciado.");
				}

			}
		} catch (SQLException e) {
			System.out.println("Não foi possível listar as marcas de carros do sistema...");
		}

		try {
			List<Cliente> clientes = ClienteDAO.listar();

			while (true) {
				System.out.println("Selecione o dono do carro : ");
				for (Cliente l : clientes) {
					System.out.println(l.getIdCliente() + "-" + l.getNome());
				}
				System.out.println("Digite o ID do dono do carro : ");
				int idDoCliente = sc.nextInt();

				for (Cliente l : clientes) {
					if (l.getIdCliente() == idDoCliente) {
						clienteEscolhido = l;

					}
				}
				if (clienteEscolhido != null) {
					System.out.println("Cliente " + clienteEscolhido.getNome() + " escolhido!");
					c.setIdCliente(clienteEscolhido.getIdCliente());
					break;
				} else {
					System.out.println("O ID referenciado não está associado à\nnenhum cliente.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível listar os clientes registrados no sistema...");
		}
		try {
			int idMarca = marcaEscolhida.getIdMarca();
			List<Modelo> modelos = ModeloDAO.listarPorMarca(idMarca);

			while (true) {
				System.out.println("Selecione o modelo do carro :");
				for (Modelo m : modelos) {
					System.out.println(m.getIdModelo() + " - " + m.getNome());
				}
				System.out.println("Digite o ID referente ao modelo do carro : ");
				int idModelo = sc.nextInt();
				sc.nextLine();

				for (Modelo m : modelos) {
					if (m.getIdModelo() == idModelo) {
						modeloEscolhido = m;
						break;
					}
				}
				if (modeloEscolhido != null) {
					c.setIdModelo(modeloEscolhido.getIdModelo());
					break;
				} else {
					System.out.println("O ID digitado é inválido, digite novamente.");
				}
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível listar os modelos associados à marca...");
		}
		while (true) {
			System.out.println("Digite a placa do carro");
			String placaInserida = sc.nextLine().toUpperCase();

			if (!placaInserida.matches("[A-z]{3}-\\d[A-j0-9]\\d{2}")) {
				System.out.println("Digite uma placa válida no formato ABC1234");
			} else if (placaInserida.length() != 7) {
				System.out.println("Digite uma placa válida");
			} else if (carroDAO.placaExiste(placaInserida)) {
				System.out.println("Digite uma placa que não tenha sido cadastrada!");
			} else {
				c.setPlaca(placaInserida);
				break;
			}

		}
		try {
			carroDAO.inserir(c);
			System.out.println("Carro cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar novo carro...\nTente Novamente!");
		}

	}
}
