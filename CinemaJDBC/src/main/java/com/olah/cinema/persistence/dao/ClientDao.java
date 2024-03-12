package com.olah.cinema.persistence.dao;

import com.olah.cinema.persistence.entity.Client;
import com.olah.cinema.persistence.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements Dao<Integer, Client> {
	public static final String createSql = """
      			INSERT INTO CLIENT(fullname, password, phonenumber) 
      			VALUES (?,?,?)
		    """;
	public static final String getAll = """
      		SELECT * FROM CLIENT
		    """;
	private static final String getAllById = getAll + """
     			WHERE CLIENT_ID = ?
	    """;
	private static final String updateSql = """
      			UPDATE CLIENT
      			SET 
      				FULLNAME = ?,
      				PASSWORD = ?,
      				PHONENUMBER = ?
      			WHERE CLIENT_ID = ?
		    """;
	private static final String deleteSql = """
      			DELETE FROM CLIENT
      			WHERE CLIENT_ID = ?
		    """;

	@Override
	public boolean create(Client client) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(createSql)) {
			preparedStatement.setString(1, client.getFullname());
			preparedStatement.setString(2, client.getPassword());
			preparedStatement.setString(3, client.getPhoneNumber());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Client> getAll() {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAll)) {
			var resultSet = preparedStatement.executeQuery();

			List<Client> clientList = new ArrayList<>();

			while (resultSet.next()){
				clientList.add(buildClient(resultSet));
			}
			return clientList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Client getById(Integer id) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAllById)) {
			preparedStatement.setInt(1, id);


			var resultSet = preparedStatement.executeQuery();

			if (resultSet.next()){
				return buildClient(resultSet);
			}else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(Client client) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(updateSql)) {
			preparedStatement.setString(1, client.getFullname());
			preparedStatement.setString(2, client.getPassword());
			preparedStatement.setString(3, client.getPhoneNumber());
			preparedStatement.setInt(4, client.getId());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delete(Integer id) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(deleteSql)) {
			preparedStatement.setInt(1, id);

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private Client buildClient(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("CLIENT_ID");
		String fullname = resultSet.getString("FULLNAME");
		String password = resultSet.getString("PASSWORD");
		String phoneNumber = resultSet.getString("PHONENUMBER");
		return new Client(id, fullname, password, phoneNumber);
	}
}
