package com.olah.cinema.persistence.dao;

import com.olah.cinema.persistence.entity.Cinema;
import com.olah.cinema.persistence.entity.Film;
import com.olah.cinema.persistence.entity.Ticket;
import com.olah.cinema.persistence.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDao implements Dao<Integer, Film>{
	public static final String createSql = """
            INSERT INTO FILM(NAME, RELEASEYEAR, GENRE, DESCRIPTION, RATING, CINEMA_ID) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;
	public static final String getAll = """
            SELECT * FROM FILM
            """;
	private static final String getAllById = getAll + """
            WHERE FILM_ID = ?
            """;
	private static final String updateSql = """
            UPDATE FILM
            SET 
                NAME = ?,
                RELEASEYEAR = ?,
                GENRE = ?,
                DESCRIPTION = ?,
                RATING = ?,
                CINEMA_ID = ?
            WHERE FILM_ID = ?
            """;
	private static final String deleteSql = """
            DELETE FROM FILM
            WHERE FILM_ID = ?
            """;
	@Override
	public boolean create(Film film) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(createSql)) {
			preparedStatement.setString(1, film.getName());
			preparedStatement.setInt(2, film.getReleaseYear());
			preparedStatement.setString(3, film.getGenre());
			preparedStatement.setString(4, film.getDescription());
			preparedStatement.setInt(5, film.getRating());
			preparedStatement.setInt(6, film.getCinema().getCinemaId());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Film> getAll() {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAll)) {
			var resultSet = preparedStatement.executeQuery();
			List<Film> filmList = new ArrayList<>();

			while (resultSet.next()){
				filmList.add(buildFilm(resultSet));
			}
			return filmList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Film getById(Integer id) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAllById)) {
			preparedStatement.setInt(1, id);

			var resultSet = preparedStatement.executeQuery();

			Film film = null;
			while (resultSet.next()){
				film = buildFilm(resultSet);
			}
			return film;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(Film film) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(updateSql)) {
			preparedStatement.setString(1, film.getName());
			preparedStatement.setInt(2, film.getReleaseYear());
			preparedStatement.setString(3, film.getGenre());
			preparedStatement.setString(4, film.getDescription());
			preparedStatement.setInt(5, film.getRating());
			preparedStatement.setInt(6, film.getCinema().getCinemaId());
			preparedStatement.setInt(7, film.getId());

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

	private Film buildFilm(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("FILM_ID");
		String name = resultSet.getString("NAME");
		int releaseYear = resultSet.getInt("RELEASEYEAR");
		String genre = resultSet.getString("GENRE");
		String description = resultSet.getString("DESCRIPTION");
		int rating = resultSet.getInt("RATING");
		int cinemaId = resultSet.getInt("CINEMA_ID");

		CinemaDao cinemaDao = new CinemaDao();
		Cinema cinema = cinemaDao.getById(cinemaId);

		TicketDao ticketDao = new TicketDao();

		List<Ticket> ticketList = ticketDao.getTicketsForFilm(id);

		return new Film(id, name, releaseYear, genre, description, rating, ticketList, cinema);
	}
}
