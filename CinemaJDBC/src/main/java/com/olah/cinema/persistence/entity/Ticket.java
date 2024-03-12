package com.olah.cinema.persistence.entity;

public class Ticket extends Entity{
	private int id;
	private int rowNumber;
	private int seatNumber;
	private Client client;
	private Film film;

	public Ticket(int id, int rowNumber, int seatNumber, Client client, Film film) {
		this.id = id;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
		this.client = client;
		this.film = film;
	}

	public Ticket(int rowNumber, int seatNumber, Client client, Film film) {
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
		this.client = client;
		this.film = film;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Ticket{" +
			 "id=" + id +
			 ", rowNumber=" + rowNumber +
			 ", seatNumber=" + seatNumber +
			 ", client=" + client +
			 ", film=" + film +
			 '}';
	}
}
