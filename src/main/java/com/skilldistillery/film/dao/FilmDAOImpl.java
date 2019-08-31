package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryFilm;

@Component
public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String USER = "student";
	private final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement createPrepareStatementId(String sql, Connection conn, int filmId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		return stmt;
	}

	private PreparedStatement createPrepareStatementKeyword(String sql, Connection conn, String keyword)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		keyword = "%" + keyword + "%";
		stmt.setString(1, keyword);
		stmt.setString(2, keyword);
		return stmt;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT film.id, film.title, film.description, film.release_year,"
				+ " category.name, language.name, film.rental_duration, film.rental_rate, film.length,"
				+ " film.replacement_cost, film.rating, film.special_features FROM film"
				+ " JOIN language ON film.language_id = language.id JOIN film_category ON film_category.film_id = film.id"
				+ " JOIN category ON category.id = film_category.category_id WHERE film.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatementId(sql, conn, filmId);
				ResultSet filmRes = stmt.executeQuery();) {
			if (filmRes.next()) {
				film = new Film(filmRes.getInt("film.id"), filmRes.getString("film.title"),
						filmRes.getString("film.description"), filmRes.getInt("film.release_year"),
						filmRes.getString("category.name"), filmRes.getString("language.name"),
						filmRes.getInt("film.rental_duration"), filmRes.getDouble("film.rental_rate"),
						filmRes.getInt("film.length"), filmRes.getDouble("film.replacement_cost"),
						filmRes.getString("film.rating"), filmRes.getString("film.special_features"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor WHERE actor.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatementId(sql, conn, actorId);
				ResultSet filmRes = stmt.executeQuery();) {
			if (filmRes.next()) {
				actor = new Actor(filmRes.getInt("id"), filmRes.getString("first_name"),
						filmRes.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, CONCAT(actor.first_name, \" \", actor.last_name) AS \"Full Name\""
				+ " FROM actor JOIN film_actor ON film_actor.actor_id = actor.id WHERE film_actor.film_id = ?"
				+ " ORDER BY actor.last_name";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatementId(sql, conn, filmId);
				ResultSet filmRes = stmt.executeQuery();) {
			while (filmRes.next()) {
				actors.add(new Actor(filmRes.getInt("id"), filmRes.getString("Full Name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT film.id, film.title, film.description, film.release_year, category.name, language.name,"
				+ " film.rental_duration, film.rental_rate, film.length, film.replacement_cost,"
				+ " film.rating, film.special_features FROM film JOIN language"
				+ " ON film.language_id = language.id JOIN film_category ON film_category.film_id = film.id"
				+ " JOIN category ON category.id = film_category.category_id"
				+ " WHERE film.title LIKE ? OR film.description LIKE ? ORDER BY film.title";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatementKeyword(sql, conn, keyword);
				ResultSet filmRes = stmt.executeQuery();) {
			while (filmRes.next()) {
				films.add(new Film(filmRes.getInt("film.id"), filmRes.getString("film.title"),
						filmRes.getString("film.description"), filmRes.getInt("film.release_year"),
						filmRes.getString("category.name"), filmRes.getString("language.name"),
						filmRes.getInt("film.rental_duration"), filmRes.getDouble("film.rental_rate"),
						filmRes.getInt("film.length"), filmRes.getDouble("film.replacement_cost"),
						filmRes.getString("film.rating"), filmRes.getString("film.special_features")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<InventoryFilm> findAllCopiesAndConditionOfFilm(int filmId) {
		List<InventoryFilm> inventoryFilms = new ArrayList<>();
		String sql = "SELECT film.id, film.title, film.description, film.release_year, category.name, language.name,"
				+ " film.rental_duration, film.rental_rate, film.length, film.replacement_cost,"
				+ " film.rating, film.special_features, inventory_item.media_condition, inventory_item.id"
				+ " FROM film JOIN language ON film.language_id = language.id"
				+ " JOIN film_category ON film_category.film_id = film.id"
				+ " JOIN category ON category.id = film_category.category_id"
				+ " JOIN inventory_item ON film.id = inventory_item.film_id WHERE film.id = ?"
				+ " ORDER BY inventory_item.media_condition";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatementId(sql, conn, filmId);
				ResultSet filmRes = stmt.executeQuery();) {
			while (filmRes.next()) {
				inventoryFilms.add(new InventoryFilm(filmRes.getInt("film.id"), filmRes.getString("film.title"),
						filmRes.getString("film.description"), filmRes.getInt("film.release_year"),
						filmRes.getString("category.name"), filmRes.getString("language.name"),
						filmRes.getInt("film.rental_duration"), filmRes.getDouble("film.rental_rate"),
						filmRes.getInt("film.length"), filmRes.getDouble("film.replacement_cost"),
						filmRes.getString("film.rating"), filmRes.getString("film.special_features"),
						filmRes.getString("inventory_item.media_condition"), filmRes.getInt("inventory_item.id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryFilms;
	}

	@Override
	public Film createFilm(Film film) {
		FilmDAOImpl filmDao = new FilmDAOImpl();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			System.out.println(stmt);
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					List<Actor> actors = filmDao.findActorsByFilmId(newFilmId);
					if (actors != null && actors.size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Actor actor : actors) {
							stmt.setInt(1, newFilmId);
							stmt.setInt(2, actor.getId());
							updateCount = stmt.executeUpdate();
						}
					}
					if(film.getCategoryId() != 0) {
						sql = "INSERT INTO film_category (film_id, category_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, newFilmId);
						stmt.setInt(2, film.getCategoryId());
					}
				}
			} else {
				film = null;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("*******************");
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(film);
		return film;
	}
}
