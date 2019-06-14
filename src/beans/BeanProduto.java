package beans;

public class BeanProduto {
	
	private Long id;
	private String nome;
	private int quantidade;
	private double valor;
	private String valorEmTexto;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getValorEmTexto() {
		return Double.toString(valor).replace(".", ",");
	}
}