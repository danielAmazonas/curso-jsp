package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanUsuario usuario) {
		String sql = "insert into usuario(login, senha, nome, cep, rua, bairro, cidade, uf, ibge, fotobase64, contenttypefoto, curriculobase64, contenttypecurriculo, fotobase64miniatura, ativo, sexo, perfil) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getCep());
			statement.setString(5, usuario.getRua());
			statement.setString(6, usuario.getBairro());
			statement.setString(7, usuario.getCidade());
			statement.setString(8, usuario.getUf());
			statement.setString(9, usuario.getIbge());
			statement.setString(10, usuario.getFotoBase64());
			statement.setString(11, usuario.getContentTypeFoto());
			statement.setString(12, usuario.getCurriculoBase64());
			statement.setString(13, usuario.getContentTypeCurriculo());
			statement.setString(14, usuario.getFotoBase64Miniatura());
			statement.setBoolean(15, usuario.isAtivo());
			statement.setString(16, usuario.getSexo());
			statement.setString(17, usuario.getPerfil());
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
	
	public List<BeanUsuario> listar(String descricao) {
		String sql = "select * from usuario where login <> 'admin' and nome like '%" + descricao + "%' order by id";
		return consultarUsuarios(sql);
	}
	
	public List<BeanUsuario> listar() {
		String sql = "select * from usuario where login <> 'admin' order by id";
		return consultarUsuarios(sql);
	}

private List<BeanUsuario> consultarUsuarios(String sql) {
	List<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
			//usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setContentTypeFoto(resultSet.getString("contenttypefoto"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			usuario.setAtivo(resultSet.getBoolean("ativo"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setPerfil(resultSet.getString("perfil"));
			usuarios.add(usuario);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return usuarios;
}

	public void delete(String id) {
		String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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

	public BeanUsuario consultar(String id) {
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				BeanUsuario usuario = new BeanUsuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setRua(resultSet.getString("rua"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setUf(resultSet.getString("uf"));
				usuario.setIbge(resultSet.getString("ibge"));
				usuario.setFotoBase64(resultSet.getString("fotobase64"));
				usuario.setContentTypeFoto(resultSet.getString("contenttypefoto"));
				usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
				usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
				usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
				usuario.setAtivo(resultSet.getBoolean("ativo"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setPerfil(resultSet.getString("perfil"));
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validarLogin(String login) {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
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

	public boolean validarLoginUpdate(String login, String id) {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
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

	public boolean validarSenha(String senha) {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
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

	public boolean validarSenhaUpdate(String senha, String id) {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <> " + id;
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

	public void atualizar(BeanUsuario usuario) {
		StringBuilder sql = new StringBuilder();
		sql.append("update usuario set login = ?, senha = ? ");
		sql.append(", nome = ?, cep = ?, rua = ?, bairro = ?, cidade = ? ");
		sql.append(", uf = ?, ibge = ?, ativo = ?, sexo = ?, perfil = ? ");
		if (usuario.isAtualizarImagem()) {
			sql.append(", fotobase64 = ?, contenttypefoto = ? ");
		}
		if (usuario.isAtualizarCurriculo()) {
			sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
		}
		if (usuario.isAtualizarImagem()) {
			sql.append(", fotobase64miniatura = ? ");
		}
		sql.append(" where id = " + usuario.getId());
		try {
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getCep());
			statement.setString(5, usuario.getRua());
			statement.setString(6, usuario.getBairro());
			statement.setString(7, usuario.getCidade());
			statement.setString(8, usuario.getUf());
			statement.setString(9, usuario.getIbge());
			statement.setBoolean(10, usuario.isAtivo());
			statement.setString(11, usuario.getSexo());
			statement.setString(12, usuario.getPerfil());
			if (usuario.isAtualizarImagem()) {
				statement.setString(13, usuario.getFotoBase64());
				statement.setString(14, usuario.getContentTypeFoto());
			}
			if (usuario.isAtualizarCurriculo()) {
				if (usuario.isAtualizarCurriculo() && !usuario.isAtualizarImagem()) {
					statement.setString(13, usuario.getCurriculoBase64());
					statement.setString(14, usuario.getContentTypeCurriculo());
				} else {
					statement.setString(15, usuario.getCurriculoBase64());
					statement.setString(16, usuario.getContentTypeCurriculo());
				}
			} else {
				if (usuario.isAtualizarImagem()) {
					statement.setString(15, usuario.getFotoBase64Miniatura());
				}
			}
			if (usuario.isAtualizarImagem() && usuario.isAtualizarCurriculo()) {
				statement.setString(17, usuario.getFotoBase64Miniatura());
			}
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