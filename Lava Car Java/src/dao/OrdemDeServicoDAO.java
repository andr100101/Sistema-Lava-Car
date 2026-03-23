package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.*;
import database.*;


	// INSERT
	public class OrdemDeServicoDAO {
	    public int inserir(OrdemDeServico ordemDeServico) throws SQLException {
	        String sql = "INSERT INTO ORDEMSERVICO (idCarro, idStatusOS, DataAbertura) VALUES (?, ?, NOW())";
	
	        try (Connection conn = Conexao.getConnection();
		        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)  {
	            stmt.setInt(1, ordemDeServico.getIdCarro());
	            stmt.setInt(2, ordemDeServico.getStatusOS());
	
	            stmt.executeUpdate();
	            ResultSet resultado = stmt.getGeneratedKeys();
	            if(resultado.next()) {
	            	return resultado.getInt(1);
	            }
	        }
	        return 0;
	 }
    
    // SELECT
    public List<OrdemDeServico> listar() throws SQLException {
        List<OrdemDeServico> lista = new ArrayList<>();
        String sql = "SELECT * FROM ORDEMSERVICO";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OrdemDeServico ordemDeServico = new OrdemDeServico();
                ordemDeServico.setIdOrdemServico(rs.getInt("idOrdemServico"));
                ordemDeServico.setIdCarro(rs.getInt("idCarro"));
                ordemDeServico.setStatusOS(rs.getInt("idStatusOS"));
                ordemDeServico.setDataCadastro(rs.getDate("DataAbertura"));
                lista.add(ordemDeServico);
            }
        }

        return lista;
    }

    // UPDATE
    public void atualizar(OrdemDeServico ordemDeServico) throws SQLException {
        String sql = "UPDATE ORDEMSERVICO SET idCarro = ?, idStatusOS= ? WHERE idOrdemDeServico = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setInt(1, ordemDeServico.getIdCarro());
            stmt.setInt(2, ordemDeServico.getStatusOS());
            stmt.setInt(3, ordemDeServico.getIdOrdemServico());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma OS encontrada com esse id!");
            }
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM ORDEMSERVICO WHERE idOrdemDeServico = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma OS encontrada com esse id!");
            }
        }
    }
}