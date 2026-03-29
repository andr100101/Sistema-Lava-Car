package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.Main;
import models.*;
import database.*;

// INSERT
public class CarroDAO {
	public int inserir(Carro carro) throws SQLException {
		String sql = "INSERT INTO CARRO (idCliente, idModelo, Placa) VALUES (?, ?, ?)";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setInt(1, carro.getIdCliente());
			stmt.setInt(2, carro.getIdModelo());
			stmt.setString(3, carro.getPlaca());

			stmt.executeUpdate();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				return resultado.getInt(1);
			}
		}
		return 0;
	}

	// SELECT
	public List<Carro> listar() throws SQLException {
		List<Carro> lista = new ArrayList<>();
		String sql = "SELECT * FROM CARRO";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Carro carro = new Carro();
				carro.setIdCarro(rs.getInt("id"));
				carro.setIdModelo(rs.getInt("idModelo"));
				carro.setIdCliente(rs.getInt("idCliente"));
				carro.setPlaca(rs.getString("Placa"));
				lista.add(carro);
			}
		}

		return lista;
	}

	// UPDATE
	public void atualizar(Carro carro) throws SQLException {
		String sql = "UPDATE CARRO SET idModelo = ?, idCliente = ?, Placa = ? WHERE idCarro = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, carro.getIdModelo());
			stmt.setInt(2, carro.getIdCliente());
			stmt.setString(3, carro.getPlaca());
			stmt.setInt(4, carro.getIdCarro());

			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletar() throws SQLException {
		String sql = "DELETE FROM CARRO WHERE idCarro = ?";
		Scanner sc = Main.sc;
		Carro del = selecionarCarro();
		if (del == null) {
			System.out.println("Nenhum carro selecionado");
			return;
		}
		while (true) {
			System.out.println("Deseja realmente deletar o carro com a placa " + del.getPlaca() + "?");
			System.out.println("Atenção: todos os serviços vinculados a esse carro também serão deletados!");
			System.out.println("Confirmação (Y/n):");

			String opt = sc.nextLine().toUpperCase();
			if (opt.equals("Y")) {
				try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setInt(1, del.getIdCarro());
					stmt.executeUpdate();
				}
				break;
			} else if (!opt.equals("N")) {
				System.out.println("Digite um valor válido");

			} else {
				break;
			}
		}
	}

	public boolean placaExiste(String Placa) {
		String sql = "SELECT COUNT(*) FROM CARRO WHERE Placa = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, Placa);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int total = rs.getInt(1);
				return total > 0;
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível verificar as placas no banco...");
			Main.aguardarEnter();
		}
		return false;
	}

	public static List<Carro> listarPorPagina(int pagina) throws SQLException {
		int limite = 15;
		pagina = (pagina <= 0) ? 1 : pagina;
		int offset = (pagina - 1) * limite;
		List<Carro> lista = new ArrayList<>();

		String sql = "SELECT * FROM CARRO ORDER BY idCarro LIMIT ? OFFSET ?;";
		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, limite);
			stmt.setInt(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setIdCarro(rs.getInt("idCarro"));
				carro.setIdCliente(rs.getInt("idCliente"));
				carro.setIdModelo(rs.getInt("idModelo"));
				carro.setPlaca(rs.getString("Placa"));
				lista.add(carro);
			}
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao listar carros" + e);
			Main.aguardarEnter();
			throw e;
		}

	}

	public static Carro selecionarCarro() throws SQLException {
		Scanner sc = Main.sc;
		int pagina = 1;

		List<Carro> lista;

		try {
			while (true) {
				lista = CarroDAO.listarPorPagina(pagina);
				int inicio = ((pagina - 1) * 15) + 1;
				int fim = inicio + lista.size() - 1;
				if (lista.isEmpty() && pagina == 1) {
					System.out.println("Não há registros...");
					Main.aguardarEnter();
					return null;
				}
				if (lista.isEmpty()) {
					System.out.println("Não há registros nessa página.");
					Main.aguardarEnter();
					pagina--;
					continue;
				} else {
					System.out.println("Selecione o carro : (" + inicio + "-" + fim + ")");
					for (Carro i : lista) {
						Cliente c = ClienteDAO.getCliente(i.getIdCliente());
						Modelo m = ModeloDAO.getModelo(i.getIdModelo());
						System.out.println(i.getIdCarro() + " - " + m.getNome() + " - " + c.getNome());
					}
					System.out.println("Digite o id do modelo, '>' e '<' para mudar de página ou 'S' para sair.");
					String entrada = sc.next().toUpperCase();
					if (entrada.equals(">")) {
						pagina++;
					} else if (entrada.equals("<")) {
						if (pagina > 1) {
							pagina--;
						}
					} else if (entrada.equals("S")) {
						return null;
					} else {
						try {
							int idCarro = Integer.parseInt(entrada);
							for (Carro i : lista) {
								if (i.getIdCarro() == idCarro) {
									return i;
								}
							}
							System.out.println("ID não encontrado nesta página.");
							Main.aguardarEnter();
						} catch (NumberFormatException e) {
							System.out.println("Entrada inválida! Digite um ID numérico.");
							Main.aguardarEnter();
						}
					}
					break;
				}

			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar veículos" + e);
			Main.aguardarEnter();
			return null;
		}
		return null;
	}
}