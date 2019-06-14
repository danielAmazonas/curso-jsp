package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	
	private Connection connection;

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanTelefone telefone) {
		String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
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

	public List<BeanTelefone> listar(Long usuario) {
		List<BeanTelefone> telefones = new ArrayList<BeanTelefone>();
		String sql = "select * from telefone where usuario = " + usuario + "order by id";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BeanTelefone telefone = new BeanTelefone();
				telefone.setId(resultSet.getLong("id"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setTipo(resultSet.getString("tipo"));
				telefone.setUsuario(resultSet.getLong("usuario"));
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefones;
	}

	public void delete(String id) {
		String sql = "delete from telefone where id = '" + id + "'";
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
}
