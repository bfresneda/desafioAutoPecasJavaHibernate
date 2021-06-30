package autopecas;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import autopecas.dao.PecaDao;
import autopecas.dao.VendaDao;
import autopecas.pojo.Peca;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		EntityManager em = factory.createEntityManager();
		
		int opcaoMenu = 0;
		int codigoDeBarras = 0;
		int quantidade = 0;
		do {
			PecaDao pecaDao = new PecaDao(em);
			VendaDao vendaDao = new VendaDao(pecaDao);
			Peca novaPeca = new Peca();
			
			System.out.println("____________________________________");
			System.out.println("|                                   |");
			System.out.println("|    ##  Auto Peças Malaquias  ##   |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("|            ##  MENU  ##           |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("| 1 - cadastrar peça.               |");
			System.out.println("| 2 - alterar peça.                 |");
			System.out.println("| 3 - remover peça.                 |");
			System.out.println("| 4 - buscar peça.                  |");
			System.out.println("| 5 - listar todas as peças.        |");
			System.out.println("| 6 - vender.                       |");
			System.out.println("| 0 - SAIR DO SISTEMA               |");
			System.out.println("|                                   |");
			System.out.println("|___________________________________|");
			System.out.print("Opcao:");
			opcaoMenu = entrada.nextInt();
			switch (opcaoMenu) {
			case 1:
				pecaDao.inserePeca(entrada);
				break;
			case 2:
				pecaDao.alteraPeca(entrada);
				break;
			case 3:
				pecaDao.removePeca(entrada);
				break;
			case 4:
				System.out.println("codigo de barras da peça:");
				codigoDeBarras = entrada.nextInt();
				pecaDao.consultaPeca(codigoDeBarras);
				break;
			case 5:
				pecaDao.listaPeca();
				break;
			case 6:
				vendaDao.realizaVenda(codigoDeBarras, quantidade);
				break;
			default:
				System.out.println("OPCAO INCORRETA DIGITE NOVAMENTE:");
				break;
			}
			
		} while (opcaoMenu != 0);
		
		
		
		em.close();
		factory.close();
		entrada.close();
		
	}

}
