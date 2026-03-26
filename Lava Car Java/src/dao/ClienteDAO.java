package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.Main;
import models.*;
import database.*;

// INSERT
public class ClienteDAO {

	public int inserir(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO CLIENTE(Nome, WhatsApp, DataCadastro) VALUES (?, ?, CURDATE())";
		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getWhats());
			stmt.executeUpdate();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				return resultado.getInt(1);
			}
			return 0;
		}
	}

	// SELECT
	public static List<Cliente> listar() throws SQLException {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CLIENTE";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setWhats(rs.getString("WhatsApp"));
				cliente.setDataCadastro(rs.getDate("DataCadastro"));
				lista.add(cliente);
			}
		}

		return lista;
	}

	// UPDATE
	public void atualizar(Cliente cliente, String nome, String whats) throws SQLException {
		String sql = "UPDATE CLIENTE SET Nome = ?, WhatsApp = ? WHERE idCliente = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nome);
			stmt.setString(2, whats);
			stmt.setInt(3, cliente.getIdCliente());
			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletar() throws SQLException {
		Scanner sc = Main.sc;
		String sql = "DELETE FROM CLIENTE WHERE idCliente = ?";
		
		Cliente cliente = selecionarCliente();
		
		while (true) {
			System.out.println("Deseja deletar " + cliente.getNome() + " de fato? (Y/N)");
			String opt = sc.nextLine().toUpperCase();
			if (opt.equals("Y")) {
				try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setInt(1, cliente.getIdCliente());
					stmt.executeUpdate();
				}
				break;
			}else if(!opt.equals("N")) {
				System.out.println("Digite um valor válido");

			}
		}

		
		
	}

	public boolean clienteExiste(String nome) throws SQLException {
		String sql = "SELECT COUNT(*) FROM CLIENTE WHERE nome = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}

		return false;
	}

	public Cliente selecionarCliente() throws SQLException {
		Scanner sc = Main.sc;
		int pagina = 1;
		
		
		List<Cliente> lista;
		
		try {
			while (true) {
				lista = ClienteDAO.listarPorPagina(pagina);
				int inicio = ((pagina - 1) * 15) + 1;
				int fim = inicio + lista.size() - 1;
				if (lista.isEmpty()) {
					System.out.println("Não há registros nessa página.");
					pagina--;
					continue;
				} else {
					System.out.println("Selecione o cliente : (" + inicio + "-" + fim);
					for (Cliente i : lista) {
						System.out.println(i.getIdCliente() + " - " + i.getNome() + " - " + i.getWhats());
					}
					System.out.println("Digite o id do cliente, '>' e '<' para mudar de página ou 'S' para sair.");
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
		                    int idCliente = Integer.parseInt(entrada);
		                    for (Cliente i : lista) {
		                        if (i.getIdCliente() == idCliente) {
		                            return i;
		                        }
		                    }
		                    System.out.println("ID não encontrado nesta página.");
		                } catch (NumberFormatException e) {
		                    System.out.println("Entrada inválida! Digite um ID numérico.");
		                }
					}

				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar clientes" + e);
			return null;
		}
	}

	public static List<Cliente> listarPorPagina(int pagina) throws SQLException {
		int limite = 15;
		int offset = (pagina - 1) * limite;
		List<Cliente> lista = new ArrayList<>();

		String sql = "SELECT * FROM CLIENTE ORDER BY idCliente LIMIT ? OFFSET ?";
		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, limite);
			stmt.setInt(2, offset);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setWhats(rs.getString("WhatsApp"));
				cliente.setDataCadastro(rs.getDate("DataCadastro"));
				lista.add(cliente);
			}
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao listar clientes" + e);
			throw e;
		}

	}
	
	public static void atualizarCliente() {
		ClienteDAO dao = new ClienteDAO();
		Scanner sc = Main.sc;
		try {
			System.out.println("--ATUALIZAR DADOS DE UM CLIENTE--");
			Cliente clienteSelecionado = dao.selecionarCliente();
			String nome;
			sc.nextLine();
			while (true) {
				System.out.println("Digite um novo nome para " + clienteSelecionado.getNome()
						+ " ou deixe em branco \npara manter o nome atual");
				nome = sc.nextLine().trim();
				if (nome.isEmpty()) {
					nome = clienteSelecionado.getNome();
					break;
				} else {
					System.out.println(
							"O novo nome de '" + clienteSelecionado.getNome() + "' " + nome + " está correto? (Y/n)");
					String confirm = sc.nextLine().toUpperCase();

					if (confirm.equalsIgnoreCase("Y")) {
						clienteSelecionado.setNome(nome);
						break;
					} else {
					}
				}
			}
			String whats;
			while (true) {
				System.out.println("Insira o novo número de WhatsApp de "+ clienteSelecionado.getNome() +" (EX: 42XXXXXXXXX):\nOu deixe em branco para manter o atual (" + clienteSelecionado.getWhats() + ")");
				whats = sc.nextLine();

				if (whats.isEmpty()) {
					break;
				}
				if (!whats.matches("\\d{10,11}")) {
					System.out.println("Número inválido! Digite 10 ou 11 dígitos.\n");
					continue;
				}

				System.out.println("O WhatsApp " + whats + " está correto? (Y/n)");
				String confirm = sc.nextLine();
				if (confirm.equalsIgnoreCase("Y")) {
					break;
				} else if (!confirm.equalsIgnoreCase("N")) {
					System.out.println("Insira um valor válido!\n");
				}
			}
			
			
			dao.atualizar(clienteSelecionado, nome, whats);
			System.out.println("Dados de " + clienteSelecionado + " atualizados!");
				
		} catch (SQLException e) {
			System.out.println("Erro ao abrir o menu de seleção de cliente");
		}

	}


}