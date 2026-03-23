package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
	            if(resultado.next()) {
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

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, modelo.getNome());
            stmt.setInt(2, modelo.getIdMarca());
            stmt.setInt(3, modelo.getIdModelo());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM MODELO WHERE idModelo = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}