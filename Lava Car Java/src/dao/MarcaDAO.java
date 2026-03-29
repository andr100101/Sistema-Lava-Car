package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.Main;
import models.*;
import database.*;

// INSERT
public class MarcaDAO {
	public int inserir(Marca marca) throws SQLException {
		String sql = "INSERT INTO MARCA(Nome) VALUES (?)";
		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, marca.getNome());
			stmt.executeUpdate();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				return resultado.getInt(1);
			}

		}
		return 0;
	}
	
	public static List<Marca> listarPorPagina(int pagina) throws SQLException {
		int limite = 15;
		pagina = (pagina <= 0) ? 1 : pagina;
		int offset = (pagina - 1) * limite;
		List<Marca> lista = new ArrayList<>();

		String sql = "SELECT * FROM MARCA ORDER BY idMarca LIMIT ? OFFSET ?;";
		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, limite);
			stmt.setInt(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Marca marca = new Marca();
				marca.setIdMarca(rs.getInt("idMarca"));
				marca.setNome(rs.getString("Nome"));
				
				lista.add(marca);
			}
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao listar marcas" + e);
			Main.aguardarEnter();
			throw e;
		}

	}
	
	public static Marca selecionarMarca() throws SQLException {
		Scanner sc = Main.sc;
		int pagina = 1;
		List<Marca> lista;
		
		try {
			while (true) {
				lista = MarcaDAO.listarPorPagina(pagina);
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
					System.out.println("Selecione o modelo : (" + inicio + "-" + fim + ")");
					for (Marca i : lista) {
						System.out.println(i.getIdMarca() + " - " + i.getNome());
					}
					System.out.println("Digite o id da marca, '>' e '<' para mudar de página ou 'S' para sair.");
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
		                    int idModelo = Integer.parseInt(entrada);
		                    for (Marca i : lista) {
		                        if (i.getIdMarca() == idModelo) {
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

				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar marca" + e);
			Main.aguardarEnter();
			return null;
		}
	}

	// SELECT
	public List<Marca> listar() throws SQLException {
		List<Marca> lista = new ArrayList<>();
		String sql = "SELECT idMarca, Nome FROM MARCA ORDER BY idMarca ASC;";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Marca marca = new Marca();
				marca.setIdMarca(rs.getInt("idMarca"));
				marca.setNome(rs.getString("Nome"));
				lista.add(marca);
			}
		}

		return lista;
	}

	// UPDATE
	public void atualizar(Marca marca) throws SQLException {
		String sql = "UPDATE MARCA SET Nome = ? WHERE idMarca = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, marca.getNome());
			stmt.setInt(2, marca.getIdMarca());
			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletar(int id) throws SQLException {
		String sql = "DELETE FROM MARCA WHERE idMarca = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}