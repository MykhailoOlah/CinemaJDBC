package com.olah.cinema.persistence.dao;

import com.olah.cinema.persistence.entity.Cinema;
import com.olah.cinema.persistence.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDao implements Dao<Integer, Cinema>{
	public static final String createSql = """
      			INSERT INTO CINEMA(NAME, ADDRESS, CITY) 
      			VALUES (?,?,?)
		    """;
	public static final String getAll = """
      		SELECT * FROM CINEMA
		    """;
	private static final String getAllById = getAll + """
     			WHERE CINEMA_ID = ?
	    """;
	private static final String updateSql = """
      			UPDATE CINEMA
      			SET 
      				NAME = ?,
      				ADDRESS = ?,
      				CITY = ?
      			WHERE CINEMA_ID = ?
		    """;
	private static final String deleteSql = """
      			DELETE FROM CINEMA
      			WHERE CINEMA_ID = ?
		    """;

	@Override
	public boolean create(Cinema cinema) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(createSql)) {
			preparedStatement.setString(1, cinema.getName());
			preparedStatement.setString(2, cinema.getAddress());
			preparedStatement.setString(3, cinema.getCity());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Cinema> getAll() {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAll)) {

			List<Cinema> cinemaList = new ArrayList<>();

			var resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				cinemaList.add(buildCinema(resultSet));
			}

			return cinemaList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Cinema getById(Integer id) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAllById)) {
			preparedStatement.setInt(1, id);

			var resultSet = preparedStatement.executeQuery();

			Cinema cinema = null;
			while (resultSet.next()){
				cinema = buildCinema(resultSet);
			}
			return cinema;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(Cinema cinema) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(updateSql)) {
			preparedStatement.setString(1, cinema.getName());
			preparedStatement.setString(2, cinema.getAddress());
			preparedStatement.setString(3, cinema.getCity());
			preparedStatement.setInt(4, cinema.getCinemaId());

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


	private Cinema buildCinema(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("CINEMA_ID");
		String name = resultSet.getString("NAME");
		String address = resultSet.getString("ADDRESS");
		String city = resultSet.getString("CITY");
		return new Cinema(id, name, address, city);
	}
}
