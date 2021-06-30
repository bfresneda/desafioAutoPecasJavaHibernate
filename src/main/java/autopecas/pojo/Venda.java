package autopecas.pojo;

public class Venda {
	private int codigoVenda;
	private Peca peca; 
	private int quantidade;
	private float totalVenda;
	
	
	public Venda() {
		
	}
	
	public Venda(int codigoVenda, Peca peca, int quantidade) {
		super();
		this.codigoVenda = codigoVenda;
		this.peca = peca;
		this.quantidade = quantidade;
	}

	public int getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(int codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(float totalVenda) {
		this.totalVenda = totalVenda;
	}
	
}