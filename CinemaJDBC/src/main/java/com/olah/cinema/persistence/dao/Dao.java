package com.olah.cinema.persistence.dao;

import java.util.List;

public interface Dao<T, E> {
	boolean create(E e);
	List<E> getAll();
	E getById(T id);
	boolean update(E e);
	boolean delete(T id);
}
