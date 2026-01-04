package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.ConexaoDB;

public class ClienteDAO {
	public void salvar(Cliente cliente) {// Salvar clientes
		String sql = "INSERT INTO Cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getTelefone());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> listarTodos() {// Listar todos os clientes
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM Cliente ORDER BY nome";
		try (Connection conn = ConexaoDB.conectar();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setTelefone(rs.getString("telefone"));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void atualizar(Cliente c) {// Atualizar clientes
		// TODO Auto-generated method stub
		String sql = "UPDATE Cliente SET nome=?, cpf=?, telefone=? WHERE id=?";
		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, c.getNome());
			ps.setString(2, c.getCpf());
			ps.setString(3, c.getTelefone());
			ps.setInt(4, c.getId());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {// Excluir clientes
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Cliente WHERE id=?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
