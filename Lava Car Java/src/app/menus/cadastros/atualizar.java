package app.menus.cadastros;

import java.sql.SQLException;
import java.util.List;
import dao.*;
import models.*;

public class atualizar {

	public static void iniciar() {
		try {
			List<Cliente> clientes = ClienteDAO.listar();

			System.out.println("Selecione o cliente que deseja atualizar os dados : ");
			for (Cliente c : clientes) {
				System.out.println(c.getIdCliente() + " - " + c.getNome() + " - " + c.getWhats());
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar os clientes...");
		}

	}

}
