package view;

import javax.swing.*;

import dao.ProdutoDAO;
import model.Produto;

public class TelaProdutos extends JFrame{
	
	 public TelaProdutos() {
	        setTitle("Cadastro de Produtos");
	        setSize(350,300);
	        setLocationRelativeTo(null);

	        JTextField txtNome = new JTextField(20);
	        JTextField txtPreco = new JTextField(10);
	        JTextField txtEstoque = new JTextField(5);

	        JButton btnSalvar = new JButton("Salvar");

	        btnSalvar.addActionListener(e -> {
	            Produto p = new Produto();
	            p.setNome(txtNome.getText());
	            p.setPreco(Double.parseDouble(txtPreco.getText()));
	            p.setEstoque(Integer.parseInt(txtEstoque.getText()));

	            new ProdutoDAO().salvar(p);
	            JOptionPane.showMessageDialog(null, "Produto salvo!");
	        });

	        JPanel painel = new JPanel();
	        painel.add(new JLabel("Nome:")); painel.add(txtNome);
	        painel.add(new JLabel("Preço:")); painel.add(txtPreco);
	        painel.add(new JLabel("Estoque:")); painel.add(txtEstoque);
	        painel.add(btnSalvar);

	        add(painel);
	 }
}
