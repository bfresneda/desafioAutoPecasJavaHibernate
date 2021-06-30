package autopecas.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Peca {
	
	@Id
	private int codigoDeBarras;
	private String nome;
	private String modeloCarro;
	private String fabricante;
	private double precoCusto;
	private double precoVenda;
	private int quantidadeEstoque;
	private String categoria;
	
	public Peca(){
	}
	
	public Peca(int codigoDeBarras, String nome, String modeloCarro, String fabricante, double precoCusto,
			double precoVenda, int quantidadeEstoque, String categoria) {
		super();
		this.codigoDeBarras = codigoDeBarras;
		this.nome = nome;
		this.modeloCarro = modeloCarro;
		this.fabricante = fabricante;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}


	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}


	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getModeloCarro() {
		return modeloCarro;
	}


	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}


	public String getFabricante() {
		return fabricante;
	}


	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}


	public double getPrecoCusto() {
		return precoCusto;
	}


	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}


	public double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}


	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}


	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Peca [codigoDeBarras=" + codigoDeBarras + ", nome=" + nome + ", modeloCarro=" + modeloCarro
				+ ", fabricante=" + fabricante + ", precoCusto=" + precoCusto + ", precoVenda=" + precoVenda
				+ ", quantidadeEstoque=" + quantidadeEstoque + ", categoria=" + categoria + "]";
	}

	
}
