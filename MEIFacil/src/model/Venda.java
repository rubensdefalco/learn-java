package model;

import java.sql.Date;

import javax.swing.JOptionPane;

public class Venda {
	
	private int id;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private Date data;
    private double total;

    public Venda() {
    	
    	
    	cliente = new Cliente();
    	produto = new Produto();
    	
    	//Produto produtoSelecionado = (Produto) produto.getSelectedItem();
    	/*if (produtoSelecionado == null || produtoSelecionado.getId() == 0) {
    	    JOptionPane.showMessageDialog(null, this, "Selecione um produto válido!", id);
    	    return;
    	}*/
     }
/*
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
