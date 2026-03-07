package conexao;

import java.sql.Connection;

public class Teste {
    public static void main(String[] args) {
        Connection c = Conexao.getConexao();

        if (c != null) {
            System.out.println("Conexão realizada com sucesso!");
        } else {
            System.out.println("Falha na conexão!");
        }
    }
}