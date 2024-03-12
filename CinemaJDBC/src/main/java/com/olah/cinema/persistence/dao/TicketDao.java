package com.olah.cinema.persistence.dao;

import com.olah.cinema.persistence.entity.Client;
import com.olah.cinema.persistence.entity.Film;
import com.olah.cinema.persistence.entity.Ticket;
import com.olah.cinema.persistence.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.engine.User;

public class TicketDao implements Dao<Integer, Ticket>{
	public static final String createSql = """
            INSERT INTO TICKET(ROWNUMBER, SEATNUMBER, USER_ID, FILM_ID) 
            VALUES (?, ?, ?, ?)
            """;
	public static final String getAll = """
            SELECT * FROM TICKET
            """;
	private static final String getAllById = getAll + """
            WHERE TICKET_ID = ?
            """;
	private static final String updateSql = """
            UPDATE TICKET
            SET 
                ROWNUMBER = ?,
                SEATNUMBER = ?,
                USER_ID = ?,
                FILM_ID = ?
            WHERE TICKET_ID = ?
            """;
	private static final String deleteSql = """
            DELETE FROM TICKET
            WHERE TICKET_ID = ?
            """;

	@Override
	public boolean create(Ticket ticket) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(createSql)) {
			preparedStatement.setInt(1, ticket.getRowNumber());
			preparedStatement.setInt(2, ticket.getSeatNumber());
			preparedStatement.setInt(3, ticket.getClient().getId());
			preparedStatement.setInt(4, ticket.getFilm().getId());

			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Ticket> getAll() {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAll)) {
			var resultSet = preparedStatement.executeQuery();
			List<Ticket> ticketList = new ArrayList<>();

			while (resultSet.next()) {
				ticketList.add(buildTicket(resultSet));
			}
			return ticketList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Ticket getById(Integer id) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(getAllById)) {
			preparedStatement.setInt(1, id);

			var resultSet = preparedStatement.executeQuery();

			Ticket ticket = null;
			while (resultSet.next()) {
				ticket = buildTicket(resultSet);
			}
			return ticket;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(Ticket ticket) {
		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement(updateSql)) {
			preparedStatement.setInt(1, ticket.getRowNumber());
			preparedStatement.setInt(2, ticket.getSeatNumber());
			preparedStatement.setInt(3, ticket.getClient().getId());
			preparedStatement.setInt(4, ticket.getFilm().getId());
			preparedStatement.setInt(5, ticket.getId());

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
	public List<Ticket> getTicketsForFilm(int filmId) {
		List<Ticket> ticketList = new ArrayList<>();

		try (var connection = ConnectionManager.getConnection();
		    var preparedStatement = connection.prepareStatement("SELECT * FROM TICKET WHERE FILM_ID = ?")) {
			preparedStatement.setInt(1, filmId);

			var resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int ticketId = resultSet.getInt("TICKET_ID");
				int rowNumber = resultSet.getInt("ROWNUMBER");
				int seatNumber = resultSet.getInt("SEATNUMBER");
				int clientId = resultSet.getInt("USER_ID");

				ClientDao clientDao = new ClientDao();
				Client client = clientDao.getById(clientId);

				ticketList.add(new Ticket(ticketId, rowNumber, seatNumber, client, null));
			}
			return ticketList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Ticket buildTicket(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("TICKET_ID");
		int rowNumber = resultSet.getInt("ROWNUMBER");
		int seatNumber = resultSet.getInt("SEATNUMBER");
		int clientId = resultSet.getInt("USER_ID");
		int filmId = resultSet.getInt("FILM_ID");

		ClientDao clientDao = new ClientDao();
		Client client = clientDao.getById(clientId);

		FilmDao filmDao = new FilmDao();
		Film film = filmDao.getById(filmId);

		return new Ticket(id, rowNumber, seatNumber, client, film);
	}
}
