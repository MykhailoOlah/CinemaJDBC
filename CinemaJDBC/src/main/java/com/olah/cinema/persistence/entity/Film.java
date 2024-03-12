package com.olah.cinema.persistence.entity;

import java.util.List;

public class Film extends Entity{
	private int id;
	private String name;
	private int releaseYear;
	private String genre;
	private String description;
	private int rating;
	private List<Ticket> tickets;
	private Cinema cinema;

	public Film(int id, String name, int releaseYear, String genre, String description,
	    int rating,
	    List<Ticket> tickets, Cinema cinema) {
		this.id = id;
		this.name = name;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.description = description;
		this.rating = rating;
		this.tickets = tickets;
		this.cinema = cinema;
	}

	public Film(String name, int releaseYear, String genre, String description, int rating,
	    List<Ticket> tickets, Cinema cinema) {
		this.name = name;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.description = description;
		this.rating = rating;
		this.tickets = tickets;
		this.cinema = cinema;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public String toString() {
		return "Film{" +
		    "id=" + id +
		    ", name='" + name + '\'' +
		    ", releaseYear=" + releaseYear +
		    ", genre='" + genre + '\'' +
		    ", description='" + description + '\'' +
		    ", rating=" + rating +
		    ", tickets=" + tickets +
		    ", cinema=" + cinema +
		    '}';
	}
}
