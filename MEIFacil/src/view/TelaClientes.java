package view;

import javax.swing.*;
import dao.ClienteDAO;
import model.Cliente;
import java.awt.event.*;
import java.util.List;

public class TelaClientes extends JFrame{
	
	private JTextField txtNome, txtCpf, txtTelefone;
    private JList<Cliente> listaClientes;
    private DefaultListModel<Cliente> modeloLista;
    private JButton btnSalvar, btnExcluir, btnAtualizar;

    public TelaClientes() {
        setTitle("Gerenciar Clientes");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Componentes
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblTelefone = new JLabel("Telefone:");

        txtNome = new JTextField(20);
        txtCpf = new JTextField(14);
        txtTelefone = new JTextField(15);

        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");

        // Lista de clientes
        modeloLista = new DefaultListModel<>();
        listaClientes = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaClientes);
        scroll.setPreferredSize(new java.awt.Dimension(450,150));
        carregarClientes();

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());

        listaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getClickCount() == 2) { // duplo clique
                    Cliente c = listaClientes.getSelectedValue();
                    if(c != null) {
                        txtNome.setText(c.getNome());
                        txtCpf.setText(c.getCpf());
                        txtTelefone.setText(c.getTelefone());
                    }
                }
            }
        });

        // Layout GroupLayout
        JPanel painel = new JPanel();
        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scroll)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNome)
                        .addComponent(lblCpf)
                        .addComponent(lblTelefone))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtNome)
                        .addComponent(txtCpf)
                        .addComponent(txtTelefone)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnSalvar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnExcluir))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCpf)
                    .addComponent(txtCpf))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(txtTelefone))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnExcluir))
        );

        add(painel);
    }

    // Métodos CRUD
    private void carregarClientes() {
        modeloLista.clear();
        List<Cliente> clientes = new ClienteDAO().listarTodos();
        for(Cliente c : clientes) {
            modeloLista.addElement(c);
        }
    }

    private void salvarCliente() {
        try {
            Cliente c = new Cliente();
            c.setNome(txtNome.getText());
            c.setCpf(txtCpf.getText());
            c.setTelefone(txtTelefone.getText());

            new ClienteDAO().salvar(c);
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
            limparCampos();
            carregarClientes();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + e.getMessage());
        }
    }

    private void atualizarCliente() {
        Cliente c = listaClientes.getSelectedValue();
        if(c != null) {
            c.setNome(txtNome.getText());
            c.setCpf(txtCpf.getText());
            c.setTelefone(txtTelefone.getText());
            new ClienteDAO().atualizar(c);
            JOptionPane.showMessageDialog(this, "Cliente atualizado!");
            limparCampos();
            carregarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar!");
        }
    }

    private void excluirCliente() {
        Cliente c = listaClientes.getSelectedValue();
        if(c != null) {
            new ClienteDAO().excluir(c.getId());
            JOptionPane.showMessageDialog(this, "Cliente excluído!");
            limparCampos();
            carregarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir!");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
    }

}
