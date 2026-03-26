package app.menus.cadastros;

import java.util.Scanner;
import dao.*;
import app.Main;

public class deletar {
	public static void iniciar(){
		Scanner sc = Main.sc;
		System.out.println("--DELETAR DADOS--");
		while(true) {
			System.out.println("Deseja fazer a exclusão de : ");
			System.out.println("1 - Cliente");
			System.out.println("2 - Carro");
			int opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				System.out.println("--DELETAR CLIENTE--");
				break;
			case 2:
				System.out.println("--DELETAR CARRO--");
				break;
			default: 
				System.out.println("Selecione uma opção válida");
				continue;
			}
			
		}
	}
}
