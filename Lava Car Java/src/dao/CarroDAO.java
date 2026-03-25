package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
	public void deletar(int id) throws SQLException {
		String sql = "DELETE FROM CARRO WHERE idCarro = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
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
		}
		return false;
	}
}