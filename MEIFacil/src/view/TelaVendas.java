package view;

import java.awt.*;

import javax.swing.*;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import model.Cliente;
import model.Produto;
import model.Venda;
import service.RelatorioVendas;
import util.NotaVenda;

import java.awt.*;
import java.util.List;
import java.util.Date;

public class TelaVendas extends JFrame {

	private JComboBox<Cliente> cbClientes;
	private JComboBox<Produto> cbProdutos;
	private JTextField txtQuantidade;
	// private ProdutoDAO produtoDAO = new ProdutoDAO();

	public TelaVendas() {
		setTitle("Registro de Vendas");
		setSize(450, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Layout Grid
		setLayout(new GridLayout(5, 2, 5, 5));

		// Componentes
		JLabel lblCliente = new JLabel("Cliente:");
		cbClientes = new JComboBox<>();
		carregarClientes();

		JLabel lblProduto = new JLabel("Produto:");
		cbProdutos = new JComboBox<>();
		carregarProdutos();

		JLabel lblQtd = new JLabel("Quantidade:");
		txtQuantidade = new JTextField();

		JButton btnRegistrar = new JButton("Registrar Venda");
		btnRegistrar.addActionListener(e -> registrarVenda());

		// Adicionando componentes
		add(lblCliente);
		add(cbClientes);
		add(lblProduto);
		add(cbProdutos);
		add(lblQtd);
		add(txtQuantidade);
		add(new JLabel());
		add(btnRegistrar);
	}

	private void carregarClientes() {
		List<Cliente> clientes = new ClienteDAO().listarTodos();
		for (Cliente c : clientes) {
			cbClientes.addItem(c);
		}
	}

	private void carregarProdutos() {
		List<Produto> produtos = new ProdutoDAO().listarTodos();
		if (produtos != null && !produtos.isEmpty()) {
			cbProdutos.setModel(new DefaultComboBoxModel<>(produtos.toArray(new Produto[0])));
		} else {
			cbProdutos.setModel(new DefaultComboBoxModel<>(new Produto[0])); // lista vazia
		}
	}

	private void registrarVenda() {
		Cliente c = (Cliente) cbClientes.getSelectedItem();
		Produto p = (Produto) cbProdutos.getSelectedItem();
		int qtd;

		try {
			qtd = Integer.parseInt(txtQuantidade.getText());
			if (qtd <= 0) {
				JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero!");
				return;
			}
			if (qtd > p.getEstoque()) {
				JOptionPane.showMessageDialog(this, "Estoque insuficiente!");
				return;
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Informe uma quantidade válida!");
			return;
		}

		double total = p.getPreco() * qtd;

		// Registrar no banco
		Venda v = new Venda();
		v.setCliente(c);
		v.setProduto(p);
		v.setQuantidade(qtd);
		v.setData(new java.sql.Date(new Date().getTime()));
		v.setTotal(total);

		new VendaDAO().salvar(v);

		// Atualizar estoque
		new ProdutoDAO().reduzirEstoque(p.getId(), qtd);

		NotaVenda.gerar(v, "C:/MEIFacil/nota.pdf");
		
		JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");
		txtQuantidade.setText("");
		cbProdutos.removeAllItems();
		carregarProdutos();
	}

}
