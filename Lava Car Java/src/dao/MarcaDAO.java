package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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