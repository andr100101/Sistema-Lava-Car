package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
	public void atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE CLIENTE SET Nome = ?, WhatsApp = ? WHERE idCliente = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getWhats());
			stmt.setInt(3, cliente.getIdCliente());
			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletar(int id) throws SQLException {
		String sql = "DELETE FROM CLIENTE WHERE idCliente = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
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
}