package view;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.LocalDate;

import dao.ClienteDAO;
import model.Cliente;
import service.RelatorioVendas;
import util.NotaVenda;

public class TelaPrincipal extends JFrame {

	 private RelatorioVendas relatorioVendas;

	 public TelaPrincipal() {
	        setTitle("MEI Fácil - Sistema de Gestão");
	        setSize(900, 500);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        // Título
	        JLabel lblTitulo = new JLabel("MEI Fácil - Sistema de Gestão");
	        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

	        // Botões principais
	        JButton btnClientes = new JButton("Gerenciar Clientes");
	        JButton btnProdutos = new JButton("Gerenciar Produtos");
	        JButton btnVendas = new JButton("Registrar Vendas");
	        JButton btnRelVendas = new JButton("Gerar Relatório de Vendas");

	        // Ação dos botões
	        btnClientes.addActionListener((ActionEvent e) -> {
	            new TelaClientes().setVisible(true);
	        });

	        btnProdutos.addActionListener((ActionEvent e) -> {
	            new TelaProdutos().setVisible(true);
	        });

	        btnVendas.addActionListener((ActionEvent e) -> {
	            new TelaVendas().setVisible(true);
	        });
	        
	        btnRelVendas.setBackground(new Color(40, 167, 69));
	        btnRelVendas.setForeground(Color.WHITE);
	        btnRelVendas.setFocusPainted(false);
	        btnRelVendas.addActionListener(e -> gerarRelatorio());
	       

	        // Painel principal com GroupLayout
	        JPanel painel = new JPanel();
	        GroupLayout layout = new GroupLayout(painel);
	        painel.setLayout(layout);

	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);

	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	                .addComponent(lblTitulo)
	                .addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnRelVendas, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	        );

	        layout.setVerticalGroup(
	            layout.createSequentialGroup()
	                .addComponent(lblTitulo)
	                .addGap(20)
	                .addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnRelVendas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	        );

	        add(painel);
	        
	    }

	   	private void gerarRelatorio() {
	   	 try {
	         relatorioVendas = new RelatorioVendas();

	         // 1️⃣ Define o arquivo (exemplo simples)
	         File pdf = new File("C:/MEIFacil/relatoriosVenda.pdf");
	         

	         // 2️⃣ Gera o relatório
	         RelatorioVendas.gerar(pdf);

	         // 3️⃣ Abre o PDF
	         if (pdf.exists()) {
	             Desktop.getDesktop().open(pdf);
	             JOptionPane.showMessageDialog(this,
	                     "Relatório gerado com sucesso!",
	                     "Sucesso",
	                     JOptionPane.INFORMATION_MESSAGE);
	         } else {
	             JOptionPane.showMessageDialog(this,
	                     "Erro ao gerar o relatório.",
	                     "Erro",
	                     JOptionPane.ERROR_MESSAGE);
	         }

	     } catch (Exception ex) {
	         JOptionPane.showMessageDialog(this,
	                 "Falha ao abrir o PDF.",
	                 "Erro",
	                 JOptionPane.ERROR_MESSAGE);
	         ex.printStackTrace();
	        }
	}

		// Método principal para teste
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new TelaPrincipal().setVisible(true);
	        });
	    }
    }

