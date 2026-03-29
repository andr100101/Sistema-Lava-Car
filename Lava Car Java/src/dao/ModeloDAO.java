package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.Main;
import models.*;
import database.*;

// INSERT
public class ModeloDAO {
	public int inserir(Modelo modelo) throws SQLException {
		String sql = "INSERT INTO MODELO(Nome, idMarca) VALUES (?, ?)";
		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, modelo.getNome());
			stmt.setInt(2, modelo.getIdMarca());
			stmt.executeUpdate();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				return resultado.getInt(1);
			}
		}
		return 0;
	}

	// SELECT
	public List<Modelo> listar() throws SQLException {
		List<Modelo> lista = new ArrayList<>();
		String sql = "SELECT * FROM MODELO";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Modelo modelo = new Modelo();
				modelo.setIdModelo(rs.getInt("idModelo"));
				modelo.setNome(rs.getString("Nome"));
				modelo.setIdMarca(rs.getInt("idMarca"));
				lista.add(modelo);
			}
		}

		return lista;
	}

	// UPDATE
	public void atualizar(Modelo modelo) throws SQLException {
		String sql = "UPDATE MODELO SET Nome = ?, idMarca = ? WHERE idModelo = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, modelo.getNome());
			stmt.setInt(2, modelo.getIdMarca());
			stmt.setInt(3, modelo.getIdModelo());
			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletar(int id) throws SQLException {
		String sql = "DELETE FROM MODELO WHERE idModelo = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public static List<Modelo> listarPorMarca(int idMarca) throws SQLException {
		List<Modelo> lista = new ArrayList<>();
		String sql = "SELECT idModelo, Nome, idMarca FROM MODELO WHERE idMarca = ? ORDER BY idMarca ASC";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idMarca);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Modelo modelo = new Modelo();
				modelo.setIdMarca(rs.getInt("idMarca"));
				modelo.setIdModelo(rs.getInt("idModelo"));
				modelo.setNome(rs.getString("Nome"));
				lista.add(modelo);
			}
		}

		return lista;

	}
	
	public static List<Modelo> listarPorPagina(int pagina, int idMarca) throws SQLException {
		int limite = 15;
		pagina = (pagina <= 0) ? 1 : pagina;
		
		int offset = (pagina - 1) * limite;
		List<Modelo> lista = new ArrayList<>();
		String sql = "SELECT * FROM MODELO WHERE idMarca = ? ORDER BY idModelo LIMIT ? OFFSET ?;";
		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, idMarca);
			stmt.setInt(2, limite);
			stmt.setInt(3, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Modelo modelo = new Modelo();
				modelo.setIdModelo(rs.getInt("idModelo"));
				modelo.setIdMarca(rs.getInt("idMarca"));
				modelo.setNome(rs.getString("Nome"));
				lista.add(modelo);
			}
			return lista;
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar modelos" + e);
			Main.aguardarEnter();
			throw e;
		}
		

	}
	
	public static Modelo selecionarModelo(int idMarca) throws SQLException {
		Scanner sc = Main.sc;
		int pagina = 1;
		List<Modelo> lista;
		
		try {
			while (true) {
				lista = ModeloDAO.listarPorPagina(pagina, idMarca);
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
					for (Modelo i : lista) {
						System.out.println(i.getIdModelo() + " - " + i.getNome());
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
		                    int idModelo = Integer.parseInt(entrada);
		                    for (Modelo i : lista) {
		                        if (i.getIdModelo() == idModelo) {
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
			System.out.println("Erro ao listar modelos" + e);
			Main.aguardarEnter();
			return null;
		}
	}
	
	public static Modelo getModelo(int idModelo) throws SQLException {
		String sql = "SELECT * FROM MODELO WHERE idModelo = ?";
		try (Connection conn = Conexao.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idModelo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            Modelo modelo = new Modelo();
	            modelo.setIdModelo(rs.getInt("idModelo"));
	            modelo.setIdMarca(rs.getInt("idMarca"));
	            modelo.setNome(rs.getString("Nome"));
	            return modelo;
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao selecionar o modelo: " + e);
	        return null;
	    }
	}
	
}



