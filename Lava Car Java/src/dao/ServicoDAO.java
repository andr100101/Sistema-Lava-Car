package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.*;
import database.*;


	// INSERT
	public class ServicoDAO {
	    public int inserir(Servico servico) throws SQLException {
	        String sql = "INSERT INTO SERVICO (Nome, Valor) VALUES (?, ?)";
	
	        try (Connection conn = Conexao.getConnection();
		        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)  {
	            stmt.setString(1, servico.getNome());
	            stmt.setBigDecimal(2, servico.getValor());
	
	            stmt.executeUpdate();
	            ResultSet resultado = stmt.getGeneratedKeys();
	            if(resultado.next()) {
	            	return resultado.getInt(1);
	            }
	        }
	        return 0;
	 }
    
    // SELECT
    public List<Servico> listar() throws SQLException {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM SERVICO";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("idServico"));
                servico.setNome(rs.getString("Nome"));
                servico.setValor(rs.getBigDecimal("Valor"));
                lista.add(servico);
            }
        }

        return lista;
    }

    // UPDATE
    public void atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE SERVICO SET Nome = ?, Valor= ? WHERE idServico = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setString(1, servico.getNome());
            stmt.setBigDecimal(2, servico.getValor());
            stmt.setInt(3, servico.getIdServico());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhum serviço encontrada com esse id!");
            }
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM SERVICO WHERE idServico = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhum serviço encontrada com esse id!");
            }
        }
    }
}