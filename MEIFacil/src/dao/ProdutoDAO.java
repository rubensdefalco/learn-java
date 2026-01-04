package dao;

import java.sql.Connection;
import java.sql.*;

import java.util.*;
import model.Produto;
import util.ConexaoDB;

public class ProdutoDAO {

	public void salvar(Produto p) {
		String sql = "INSERT INTO Produto (nome, preco, estoque) VALUES (?, ?, ?)";
		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setInt(3, p.getEstoque());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Produto p) {
		String sql = "UPDATE Produto SET nome=?, preco=?, estoque=? WHERE id=?";
		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setInt(3, p.getEstoque());
			ps.setInt(4, p.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM Produto";
		try (Connection conn = ConexaoDB.conectar();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setEstoque(rs.getInt("estoque"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void reduzirEstoque(int produtoId, int quantidade) {
		String sql = "UPDATE Produto SET estoque = estoque - ? WHERE id = ?";
		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, quantidade);
			ps.setInt(2, produtoId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
