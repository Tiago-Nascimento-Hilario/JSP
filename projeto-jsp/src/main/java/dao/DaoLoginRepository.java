package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConection;
import model.ModelLogin;

public class DaoLoginRepository {
	
	private Connection connection;
	
	public DaoLoginRepository() {
		connection = SingleConection.getConnection();
	}
	
	public Boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return true;/*Autenticado*/
		}
		
		return false;/*Não autenticado*/
	}
	
}
