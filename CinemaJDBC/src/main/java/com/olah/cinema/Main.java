package com.olah.cinema;

import com.olah.cinema.persistence.dao.CinemaDao;
import com.olah.cinema.persistence.dao.ClientDao;
import com.olah.cinema.persistence.dao.FilmDao;
import com.olah.cinema.persistence.dao.TicketDao;
import com.olah.cinema.persistence.entity.Cinema;
import com.olah.cinema.persistence.entity.Client;
import com.olah.cinema.persistence.entity.Film;
import com.olah.cinema.persistence.entity.Ticket;
import java.util.List;

public class Main {
	private static final ClientDao clientDao = new ClientDao();
	private static final CinemaDao cinemaDao = new CinemaDao();
	private static final FilmDao filmDao = new FilmDao();
	private static final TicketDao tickedDao = new TicketDao();

	public static void main(String[] args) {
		//createClient();
//
		//allClients();
//
		//System.out.println(getClientById(8));
//
		//updateClient(getClientById(8));
//
		//deleteClient(8);

		/////////////////////////////////////////
		//createCinema();
//
		//allCinemas();
//
		//System.out.println(getCinemaById(3));
//
		//updateCinema(getCinemaById(3));
//
		//deleteCinema(3);
		/////////////////////////////////////////

		//createFilm();
		//allFilms();
		//System.out.println(getFilmById(1));
//		updateFilm(getFilmById(1));
		//deleteFilm(3);

		//////////////////////////////////////////

		//createTicket();
		//allTickets();
		//System.out.println(getTicketById(1));
		//updateTicket(getTicketById(1));
		//deleteTicket(3);

	}

	private static void createTicket() {
		Ticket ticket = new Ticket(1, 2, getClientById(1), getFilmById(1));
		System.out.println(tickedDao.create(ticket));
	}

	private static void allTickets() {
		List<Ticket> allTickets = tickedDao.getAll();
		for (Ticket ticket : allTickets) {
			System.out.println(ticket);
		}
	}

	private static Ticket getTicketById(Integer id) {
		return tickedDao.getById(id);
	}

	private static void updateTicket(Ticket ticket) {
		ticket.setRowNumber(3);
		ticket.setSeatNumber(4);
		ticket.setClient(getClientById(2));
		ticket.setFilm(getFilmById(2));
		System.out.println(tickedDao.update(ticket));
	}

	private static void deleteTicket(Integer id) {
		System.out.println(tickedDao.delete(id));
	}
	private static void createFilm() {
		Film film = new Film("Назва фільму", 2022, "Жанр", "Опис", 5, null, getCinemaById(1));
		System.out.println(filmDao.create(film));
	}

	private static void allFilms() {
		List<Film> allFilms = filmDao.getAll();
		for (Film film : allFilms) {
			System.out.println(film);
		}
	}

	private static Film getFilmById(Integer id) {
		return filmDao.getById(id);
	}

	private static void updateFilm(Film film) {
		film.setName("Нова назва фільму");
		System.out.println(filmDao.update(film));
	}

	private static void deleteFilm(Integer id) {
		System.out.println(filmDao.delete(id));
	}
	private static void deleteCinema(Integer deleteCinemaId) {
		System.out.println(cinemaDao.delete(deleteCinemaId));
	}

	private static void updateCinema(Cinema cinema) {
		cinema.setName("Ужгород");
		System.out.println(cinemaDao.update(cinema));
	}

	private static Cinema getCinemaById(Integer id) {
		return cinemaDao.getById(id);
	}

	private static void allCinemas() {
		List<Cinema> allCinemas = cinemaDao.getAll();
		for (Cinema cinema : allCinemas) {
			System.out.println(cinema);
		}
	}

	private static void createCinema() {
		Cinema cinema = new Cinema("Новий кінотеатр", "Адреса", "Місто");
		System.out.println(cinemaDao.create(cinema));
	}
	private static void deleteClient(Integer deleteClientId) {
		System.out.println(clientDao.delete(deleteClientId));
	}

	private static void updateClient(Client client) {
		client.setFullname("Test");
		System.out.println(clientDao.update(client));
	}

	private static Client getClientById(Integer id){
		return clientDao.getById(id);
	}
	private static void allClients() {
		List<Client> allClients = clientDao.getAll();
		for (Client client: allClients){
			System.out.println(client);
		}
	}

	private static void createClient() {
		Client client
		    = new Client("Новий2024666test", "1234", "+3098655456");
		System.out.println(clientDao.create(client));
	}
}
