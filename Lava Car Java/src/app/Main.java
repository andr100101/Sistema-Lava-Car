package app;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		Inicial.exibir();
	}
	
	public static void aguardarEnter() {
	    System.out.print("\n[Pressione ENTER para continuar]");
	    if (sc.hasNextLine()) {
	        sc.nextLine(); 
	    }
	    sc.nextLine(); 
	}

}
