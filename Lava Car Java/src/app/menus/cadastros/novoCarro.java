package app.menus.cadastros;

import java.sql.SQLException;
import java.util.Scanner;
import models.*;
import app.Main;
import dao.*;

public class novoCarro {
	public static void cadastrar() {
		Scanner sc = Main.sc;
		Carro c = new Carro();
		Marca marcaEscolhida = null;
		Modelo modeloSelecionado = null;
		CarroDAO carroDAO = new CarroDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		try {
			marcaEscolhida = MarcaDAO.selecionarMarca();
			if (marcaEscolhida == null) {
				return;
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível selecionar a marca do veículo");
		}

		try {
			Cliente clienteSelecionado =  clienteDAO.selecionarCliente();
			if (clienteSelecionado== null) {
				return;
			}
			c.setIdCliente(clienteSelecionado.getIdCliente());
		} catch (SQLException e) {
			System.out.println("Não foi possível listar os clientes registrados no sistema...");
		}
		try {
			modeloSelecionado = ModeloDAO.selecionarModelo(marcaEscolhida.getIdMarca());
			if (modeloSelecionado== null) {
				return;
			}
			c.setIdModelo(modeloSelecionado.getIdModelo());
		} catch (SQLException e) {
			System.out.println("Não foi possível listar os modelos associados à marca...");
		}
		while (true) {
			sc.nextLine();
			System.out.println("Digite a placa do carro");
			String placaInserida = sc.nextLine();


		    if (placaInserida == null || placaInserida.trim().isEmpty()) {
		        System.out.println("Entrada inválida! Operação cancelada.");
		        return;
		    }

		    placaInserida = placaInserida.toUpperCase().trim();

			if (placaInserida.length() != 7) {
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
			Main.aguardarEnter();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar novo carro...\nTente Novamente!");
			Main.aguardarEnter();
		}
		return;

	}
}
