package autopecas.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import autopecas.pojo.Peca;
import autopecas.pojo.Venda;

public class VendaDao {
	
	private EntityManager em;
	private List<Venda> vendas;
//	private PecaDao pecaDao;
	
	Scanner entrada = new Scanner(System.in);
	
	public VendaDao(EntityManager em) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.em = factory.createEntityManager();
	}
	
	public VendaDao(PecaDao pecaDao) {
		this.vendas = new ArrayList<Venda>();
	}
	
	Venda novaVenda = new Venda();
	PecaDao pecaDao = new PecaDao(em);
	Peca novaPecaVenda = new Peca();
	
	public void realizaVenda(int codigoDeBarras, int quantidadeR) {
		
		File file = new File("nota_fiscal.txt");
				
		int opcaoVenda = 0;
		do {
			System.out.println("____________________________________");
			System.out.println("|                                   |");
			System.out.println("|    ##  Auto Peças Malaquias  ##   |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("|            ##  MENU  ##           |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("| 1 - vender peça.                  |");
			System.out.println("| 2 - Finalizar venda.              |");
			System.out.println("| 0 - VOLTAR AO MENU ANTERIOR.      |");
			System.out.println("|                                   |");
			System.out.println("|___________________________________|");
			System.out.print("Opcao:");
			opcaoVenda = entrada.nextInt();
			float totalPecas = 0;
			
			switch(opcaoVenda) {
			case 1:
		
				System.out.print("codigo de barras da peça:");
				codigoDeBarras = entrada.nextInt();
				System.out.print("quantidade de peças:");
				int quantidade = entrada.nextInt();
				
				int codBarConsulta = codigoDeBarras;
				novaVenda.setQuantidade(quantidade);
				int item = 0;
				
				
				novaPecaVenda = pecaDao.consultaPeca(codBarConsulta);
//				@SuppressWarnings("rawtypes")
//				List<Comparable> listaVendaPecas = new ArrayList<Comparable>();

//				System.out.printf(novaPecaVenda.getQuantidadeEstoque() + " quantidade em estoque\n");
//				System.out.println(novaVenda.getQuantidade() + " quantidade pedido");
				if(novaVenda.getQuantidade() <= novaPecaVenda.getQuantidadeEstoque()) {
					totalPecas = quantidade * (float) novaPecaVenda.getPrecoVenda();
					novaVenda.setTotalVenda(novaVenda.getTotalVenda() + totalPecas);
					System.out.printf("Total venda R$%.2f \n",novaVenda.getTotalVenda());
//					listaVendaPecas.add(novaPecaVenda.getCodigoDeBarras());
//					listaVendaPecas.add(novaPecaVenda.getNome());
//					listaVendaPecas.add(novaPecaVenda.getPrecoVenda());
//					listaVendaPecas.add(quantidade);
//					listaVendaPecas.add(totalPeca);	
					
					try {
						FileWriter fw = new FileWriter(file,true);//append true pra adicionar ao final do arquivo
						BufferedWriter br = new BufferedWriter(fw);
						
						br.write(novaPecaVenda.getCodigoDeBarras() + " | ");
						br.write(novaPecaVenda.getNome() + " | ");
						br.write(novaPecaVenda.getPrecoVenda() + " | ");
						br.write(quantidade + " | ");
						br.write(totalPecas + " | \n");
//						br.write(novaVenda.getTotalVenda() + " | ");
						br.write("-----------------------------------------------------------------------------");
						br.newLine();
						br.flush();
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}else {
					System.out.printf("quantidade em estoque atual %d peças \n",novaPecaVenda.getQuantidadeEstoque());
				}

				break;
			case 2:
					try {
						FileWriter fw = new FileWriter(file,true);//append true pra adicionar ao final do arquivo
						BufferedWriter br = new BufferedWriter(fw);
						br.newLine();
						br.write("             |TOTAL DA COMPRA R$" + novaVenda.getTotalVenda() + " |            \n");
						br.write("-----------------------------------------------------------------------------------");
						br.newLine();
						br.flush();
						br.close();
						System.out.printf("Finalizando venda.\n");
						System.out.printf("Valor final da venda R$%.2f",novaVenda.getTotalVenda());
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
			default:
				System.out.println("OPCAO INCORRETA TENTA NOVAMENTE");
				}
		} while (opcaoVenda != 0);
		
	}
	
	
}
