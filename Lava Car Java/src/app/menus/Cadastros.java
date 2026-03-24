package app.menus;
import java.util.Scanner;

import app.Inicial;
import app.Main;
public class Cadastros {
	public static void Cadastrar() {
		        Scanner sc = Main.sc;
		        int opcao = 0;
		        while (opcao != 9) {
		        	System.out.println("--CADASTRO--");
			        System.out.println("1 - Novo Cliente");
			        System.out.println("2 - Novo Carro");
			        System.out.println("3 - Atualizar dados");
			        System.out.println("4 - Deletar dados");
			        System.out.println("9 - Voltar");
			        
			        opcao = sc.nextInt();
			        
			        switch(opcao) {
			        	case 1 :
			        		app.menus.cadastros.novoCliente.cadastrar();
			        		break;
			        	case 2 :
			        		break;
			        	case 3 :
			        		break;
			        	case 4 : 
			        		break;
			        	case 9 : Inicial.exibir();
			        		break;
			        	default : 
			        		System.out.println("Opção inválida.");
				        System.out.println("\n\nPressione enter para continuar...");
				        
				        sc.nextLine();
				        sc.nextLine();
			        }
		        }
	    }
	}
