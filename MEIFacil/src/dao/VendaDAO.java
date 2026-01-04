package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Venda;
import util.ConexaoDB;

public class VendaDAO {

	public void salvar(Venda v) {
		String sql = "INSERT INTO Venda (cliente_id, produto_id, quantidade, data_venda, total) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConexaoDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, v.getCliente().getId());
			ps.setInt(2, v.getProduto().getId());
			ps.setInt(3, v.getQuantidade());
			ps.setDate(4, v.getData());
			ps.setDouble(5, v.getTotal());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Venda> listarTodos() {
		List<Venda> vendas = new ArrayList<>();

		String sql = """
				    SELECT v.id, c.nome AS cliente, p.nome AS produto,
				           v.quantidade, v.total
				    FROM venda v
				    JOIN cliente c ON v.cliente_id = c.id
				    JOIN produto p ON v.produto_id = p.id
				""";

		try (Connection conn = ConexaoDB.conectar();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Venda v = new Venda();
				v.setId(rs.getInt("id"));
				v.getCliente().setNome(rs.getString("cliente"));
				v.getProduto().setNome(rs.getString("produto"));
				v.setQuantidade(rs.getInt("quantidade"));
				//v.setData(rs.getDate("Data da venda"));
				v.setTotal(rs.getDouble("total"));

				vendas.add(v);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vendas; // 🔥 NUNCA NULL
	}
}
