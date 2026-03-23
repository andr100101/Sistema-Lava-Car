package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.*;
import database.*;


	// INSERT
	public class ItemOSDAO {
	    public int inserir(ItemOS itemOS) throws SQLException {
	        String sql = "INSERT INTO ITEMOS(idOrdemServico, idServico, valorCobrado) VALUES (?, ?, ?)";
	        try (Connection conn = Conexao.getConnection();
	        	PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
	            stmt.setInt(1, itemOS.getIdOrdemDeServico());
	            stmt.setInt(2, itemOS.getIdServico());
	            stmt.setBigDecimal(3, itemOS.getValorCobrado());
	            
	            
	            stmt.executeUpdate();
	            
	            ResultSet resultado = stmt.getGeneratedKeys();
	            if(resultado.next()) {
	            	return resultado.getInt(1);
	            }
	        }
	        return 0;
	 }
    
    // SELECT
    public List<ItemOS> listar() throws SQLException {
        List<ItemOS> lista = new ArrayList<>();
        String sql = "SELECT * FROM ITEMOS";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemOS itemOS = new ItemOS();
                itemOS.setIdItemOS(rs.getInt("idItemOS"));
                itemOS.setIdOrdemDeServico(rs.getInt("idOrdemServico"));
                itemOS.setIdServico(rs.getInt("idServico"));
                itemOS.setValorCobrado(rs.getBigDecimal("valorCobrado"));
                lista.add(itemOS);
            }
        }

        return lista;
    }

    // UPDATE
    public void atualizar(ItemOS itemOS) throws SQLException {
        String sql = "UPDATE ITEMOS SET idOrdemServico = ?, idServico = ?, valorCobrado = ? WHERE idItemOS = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, itemOS.getIdOrdemDeServico());
        	stmt.setInt(2, itemOS.getIdServico());
        	stmt.setBigDecimal(2, itemOS.getValorCobrado());
        	stmt.setInt(4, itemOS.getIdItemOS());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM ITEMOS WHERE idItemOS = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}