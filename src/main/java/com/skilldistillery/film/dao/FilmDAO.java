package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryFilm;

public interface FilmDAO {

	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> findFilmsByKeyword(String keyword);

	public List<InventoryFilm> findAllCopiesAndConditionOfFilm(int filmId);

	public Film createFilm(Film film);

	public boolean saveFilm(Film film);

	public boolean deleteFilm(Film film);
}
