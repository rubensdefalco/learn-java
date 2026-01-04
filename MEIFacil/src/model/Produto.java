package model;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int estoque;

	// Construtor vazio (obrigatório para uso com DAO)
	public Produto() {
	}

	// Construtor com parâmetros (opcional)
	public Produto(int id, String nome, double preco, int estoque) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Produto getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + " - R$ =" + preco + "]";
	}

}
