package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
	
	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanProduto produto) {
		String sql = "insert into produto(nome, quantidade, valor) values (?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> listar() {
		List<BeanProduto> produtos = new ArrayList<BeanProduto>();
		String sql = "select * from produto order by id";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BeanProduto produto = new BeanProduto();
				produto.setId(resultSet.getLong("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public void delete(String id) {
		String sql = "delete from produto where id = '" + id + "'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanProduto consultar(String id) {
		String sql = "select * from produto where id = '" + id + "'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				BeanProduto produto = new BeanProduto();
				produto.setId(resultSet.getLong("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));
				return produto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validarNome(String nome) {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("qtd") <= 0; /* Return true */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validarNomeUpdate(String nome, String id) {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "' and id <> " + id;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("qtd") <= 0; /* Return true */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void atualizar(BeanProduto produto) {
		String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
