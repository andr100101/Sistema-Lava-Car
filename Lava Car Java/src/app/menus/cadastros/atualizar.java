package app.menus.cadastros;

import dao.ClienteDAO;

public class atualizar {

	public static void iniciar() {
		ClienteDAO.atualizarCliente();
	}

}
