package autopecas.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import autopecas.pojo.Peca;

public class PecaDao {
	
	private EntityManager em;

	public PecaDao(EntityManager em) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.em = factory.createEntityManager();
	}
	
	public PecaDao() {
		
	}

	Scanner entrada = new Scanner(System.in);
	Peca novaPeca = new Peca();
	
	
	public boolean inserePeca(Scanner entrada) {
		System.out.println("____________________________________");
		System.out.println("|                                   |");
		System.out.println("|    ##  Auto Peças Malaquias  ##   |");
		System.out.println("|___________________________________|");
		System.out.println("|                                   |");
		System.out.println("|        INSIRA UMA NOVA PEÇA       |");
		System.out.println("|___________________________________|");
		System.out.print("Codigo de barras:");
		novaPeca.setCodigoDeBarras(entrada.nextInt());
		entrada.nextLine();
		System.out.print("Nome da peça:");
		novaPeca.setNome(entrada.nextLine());
		System.out.print("Modelo do carro:");
		novaPeca.setModeloCarro(entrada.nextLine());
		System.out.print("Fabricante:");
		novaPeca.setFabricante(entrada.nextLine());
		System.out.print("Preço de custo: R$");
		novaPeca.setPrecoCusto(entrada.nextDouble());
		System.out.print("Preço de venda: R$");
		novaPeca.setPrecoVenda(entrada.nextDouble());
		System.out.print("Quantidade em estoque: ");
		novaPeca.setQuantidadeEstoque(entrada.nextInt());
		entrada.nextLine();
		System.out.print("Categoria:");
		novaPeca.setCategoria(entrada.nextLine());
		
		try {
			em.getTransaction().begin();
			em.persist(novaPeca);
			em.getTransaction().commit();
//			// em.close();
			
		} catch (Exception e) {
			System.err.println("Erro ao criar uma nova peça");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean alteraPeca(Scanner entrada) {
		
		int opcaoAtt = 0;
		do {
			System.out.println("____________________________________");
			System.out.println("|                                   |");
			System.out.println("|    ##  Auto Peças Malaquias  ##   |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("|        ATUALIZAR INFORMACOES      |");
			System.out.println("|___________________________________|");

			System.out.print("Codigo de barras do produto:");
			novaPeca.setCodigoDeBarras(entrada.nextInt());

			System.out.println("____________________________________");
			System.out.println("|                                   |");
			System.out.println("|    ##  Auto Peças Malaquias  ##   |");
			System.out.println("|___________________________________|");
			System.out.println("|                                   |");
			System.out.println("|        ATUALIZAR INFORMACOES      |");
			System.out.println("|                                   |");			
			System.out.println("| 1 - alterar nome.                 |");
			System.out.println("| 2 - alterar modelo carro.         |");
			System.out.println("| 3 - alterar fabricante.           |");
			System.out.println("| 4 - alterar preco de custo.       |");
			System.out.println("| 5 - alterar preco de venda.       |");
			System.out.println("| 6 - alterar quantidade.           |");
			System.out.println("| 7 - alterar categoria.            |");
			System.out.println("| 0 - VOLTAR AO MENU ANTERIOR.      |");
			System.out.println("|                                   |");			
			System.out.println("|                                   |");			
			System.out.println("|                                   |");			
			System.out.println("|___________________________________|");


			System.out.print("Opção:");
			opcaoAtt = entrada.nextInt();
			entrada.nextLine();
			switch(opcaoAtt){
			case 1:
				System.out.print("Novo nome:");
				novaPeca.setNome(entrada.nextLine());
				break;
			case 2:
				System.out.print("Novo modelo carro:");
				novaPeca.setModeloCarro(entrada.nextLine());
				break;
			case 3:
				System.out.print("Novo fabricante:");
				novaPeca.setFabricante(entrada.nextLine());
				break;
			case 4:
				System.out.print("Novo preco de custo:R$");
				novaPeca.setPrecoCusto(entrada.nextDouble());
				break;
			case 5:
				System.out.print("Novo preco de venda:R$");
				novaPeca.setPrecoVenda(entrada.nextDouble());
				break;
			case 6:
				System.out.print("Nova quantidade:");
				novaPeca.setQuantidadeEstoque(entrada.nextInt());
				break;
			case 7:
				System.out.print("Nova categoria:");
				novaPeca.setCategoria(entrada.nextLine());
				break;
			default:
				System.out.print("OPCAO INCORRETA DIGITE NOVAMENTE:");
				break;
			}
			
			try {
				Peca atualizaPeca = em.find(Peca.class, novaPeca.getCodigoDeBarras());
				atualizaPeca.setNome(novaPeca.getNome());
				atualizaPeca.setModeloCarro(novaPeca.getModeloCarro());
				atualizaPeca.setFabricante(novaPeca.getFabricante());
				atualizaPeca.setPrecoCusto(novaPeca.getPrecoCusto());
				atualizaPeca.setPrecoVenda(novaPeca.getPrecoVenda());
				atualizaPeca.setQuantidadeEstoque(novaPeca.getQuantidadeEstoque());
				atualizaPeca.setCategoria(novaPeca.getCategoria());
				
				em.getTransaction().begin();
				em.merge(atualizaPeca);
				em.getTransaction().commit();
				// em.close();
				System.out.println("Peca atualizada com sucesso.");
				
				
			} catch (Exception e) {
				System.err.println("Erro ao atualizar peça.");
				System.err.println(e.getMessage());
				return false;		
			}
			
			
		} while (opcaoAtt != 0);
		
		return true;
	}
	
	public boolean alteraEstoque(int codigoDeBarras, int quantidade) {
		
		System.out.println("entrei no metodo alteraEstoque");
		System.out.println("valor recebido " + codigoDeBarras +" "+ quantidade);
		try {
			Peca atualizaPeca = em.find(Peca.class, codigoDeBarras);
			atualizaPeca.setQuantidadeEstoque(quantidade);
			
			em.getTransaction().begin();
			em.merge(atualizaPeca);
			em.getTransaction().commit();
			// em.close();
			System.out.printf("Produto atualizado no estoque quantidade atual %d",novaPeca.getQuantidadeEstoque());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
	}
		
	public boolean removePeca(Scanner entrada) {
		System.out.println("____________________________________");
		System.out.println("|                                   |");
		System.out.println("|    ##  Auto Peças Malaquias  ##   |");
		System.out.println("|___________________________________|");
		System.out.println("|                                   |");
		System.out.println("|            APAGAR PEÇA            |");
		System.out.println("|___________________________________|");
		
		System.out.print("Codigo de barras do produto:");
		novaPeca.setCodigoDeBarras(entrada.nextInt());

		Peca removePeca = em.find(Peca.class, novaPeca.getCodigoDeBarras());
		
		try {
			em.getTransaction().begin();
			em.remove(removePeca);
			em.getTransaction().commit();
			// em.close();
			System.out.printf("Peça $d, %s removida com sucesso", removePeca.getCodigoDeBarras(), removePeca.getNome());
			
		} catch (Exception e) {
			System.err.println("Erro ao remover peça");
			System.err.println(e.getMessage());
			return false;		
		}
		return true;
	}
	
	public Peca consultaPeca(int codigoDeBarras) {
		Peca novaPeca = em.find(Peca.class, codigoDeBarras);
		// em.close();
		return novaPeca;
	}
	
	public void fazerConsulta(Scanner entrada){
		System.out.println("____________________________________");
		System.out.println("|                                   |");
		System.out.println("|    ##  Auto Peças Malaquias  ##   |");
		System.out.println("|___________________________________|");
		System.out.println("|                                   |");
		System.out.println("|          CONSULTAR PEÇA           |");
		System.out.println("|                                   |");
		System.out.println("|                                   |");
		System.out.println("| 1 - Buscar pelo codigo de barras. |");
		System.out.println("| 2 - Buscar pelo nome.             |");
		System.out.println("| 3 - Buscar pelo modelo carro.     |");
		System.out.println("| 4 - Buscar pelo fabricante.       |");
		System.out.println("| 5 - Buscar pelo categoria.        |");		
		System.out.println("| 0 - RETORNAR AO MENU ANTERIOR     |");
		System.out.println("|___________________________________|");
		System.out.print("Opcao de busca:");
		int opcaoConsulta = 0;
		do {
		
			opcaoConsulta = entrada.nextInt();
			switch(opcaoConsulta) {
			case 1:
				try {
					System.out.print("Codigo de barras do produto:");
					novaPeca.setCodigoDeBarras(entrada.nextInt());
					Query query = em.createQuery("select p from Peca as p where p.codigoDeBarras = :retornaPeca");
					query.setParameter("retornaPeca", novaPeca.getCodigoDeBarras());
					List<Peca> pecasEncontradas = query.getResultList();
					for(Peca pecaEncontrada : pecasEncontradas) {
						System.out.println(pecaEncontrada);
					}
				} catch (Exception e) {
					System.err.println("Erro ao consultar peça.");
					System.err.println(e.getMessage());
//					return false;	
				}
				break;
			case 2:
				try {
					System.out.print("Nome da peça:");
					novaPeca.setNome(entrada.nextLine());
					Query query = em.createQuery("select p from Peca as p where p.nome like :retornaPeca");
					query.setParameter("retornaPeca", "%" + novaPeca.getNome()+"%");
					List<Peca> pecasEncontradas = query.getResultList();
					for(Peca pecaEncontrada : pecasEncontradas) {
						System.out.println(pecaEncontrada);
					}
				} catch (Exception e) {
					System.err.println("Erro ao consultar peça.");
					System.err.println(e.getMessage());
//					return false;	
				}
				break;
			case 3:
				try {
					System.out.print("Modelo do carro:");
					novaPeca.setNome(entrada.nextLine());
					Query query = em.createQuery("select p from Peca as p where p.nome like :retornaPeca");
					query.setParameter("retornaPeca", "%" + novaPeca.getModeloCarro()+"%");
					List<Peca> pecasEncontradas = query.getResultList();
					for(Peca pecaEncontrada : pecasEncontradas) {
						System.out.println(pecaEncontrada);
					}
				} catch (Exception e) {
					System.err.println("Erro ao consultar peça.");
					System.err.println(e.getMessage());
//					return false;	
				}
				break;
			case 4:
				try {
					System.out.print("Fabricante da peça:");
					novaPeca.setFabricante(entrada.nextLine());
					Query query = em.createQuery("select p from Peca as p where p.nome like :retornaPeca");
					query.setParameter("retornaPeca", "%" + novaPeca.getFabricante()+"%");
					List<Peca> pecasEncontradas = query.getResultList();
					for(Peca pecaEncontrada : pecasEncontradas) {
						System.out.println(pecaEncontrada);
					}
				} catch (Exception e) {
					System.err.println("Erro ao consultar peça.");
					System.err.println(e.getMessage());
//					return false;	
				}
				break;
			case 5:
				try {
					System.out.print("Categoria da peça:");
					novaPeca.setCategoria(entrada.nextLine());
					Query query = em.createQuery("select p from Peca as p where p.nome like :retornaPeca");
					query.setParameter("retornaPeca", "%" + novaPeca.getCategoria()+"%");
					List<Peca> pecasEncontradas = query.getResultList();
					for(Peca pecaEncontrada : pecasEncontradas) {
						System.out.println(pecaEncontrada);
					}
				} catch (Exception e) {
					System.err.println("Erro ao consultar peça.");
					System.err.println(e.getMessage());
//					return false;	
				}
				break;
			default:
				System.out.println("OPCAO INCORRETA TENTE NOVAMENTE");
				break;
			}
//			return true;
		}while(opcaoConsulta != 0);
	}	
	
	
	public void listaPeca(){
		Query query = em.createQuery("select p from Peca as p");
		
		List<Peca> pecasEncontradas = query.getResultList();
		for(Peca pecaEncontrada : pecasEncontradas) {
			System.out.println(pecaEncontrada);
			}
	}

}
