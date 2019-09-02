package com.skilldistillery.film.entities;

import java.util.ArrayList;
import java.util.List;


public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private String language;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost; 
	private String rating; //finished
	private String specialFeatures; //finished
	private String category; //don't worry about
	private int categoryId; //finished
	private int languageId; //finished
	private List<Actor> actors;

	public Film() {
	}

	public Film(int id, String title, String description, int releaseYear, String category, String language,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.category = category;
	}

	public Film(int id, String title, String description, int releaseYear, int categoryId, int languageId,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.categoryId = categoryId;
		//could not get comparing ints to work on JSP page, so this is how we had to do it to get it to auto populate language on CRUD.jsp.
		if(languageId == 1) {
			language = "English";
		}
		if(languageId == 2) {
			language = "Italian";
		}
		if(languageId == 3) {
			language = "Japanese";
		}
		if(languageId == 4) {
			language = "Mandarin";
		}
		if(languageId == 5) {
			language = "French";
		}
		if(languageId == 6) {
			language = "German";
		}
		//could not get comparing ints to work on JSP page, so this is how we had to do it to get it to auto populate category on CRUD.jsp
		if(categoryId == 1) {
			category = "Action";
		}
		if(categoryId == 2) {
			category = "Animation";
		}
		if(categoryId == 3) {
			category = "Children";
		}
		if(categoryId == 4) {
			category = "Classics";
		}
		if(categoryId == 5) {
			category = "Comedy";
		}
		if(categoryId == 6) {
			category = "Documentary";
		}
		if(categoryId == 7) {
			category = "Drama";
		}
		if(categoryId == 8) {
			category = "Family";
		}
		if(categoryId == 9) {
			category = "Foreign";
		}
		if(categoryId == 10) {
			category = "Games";
		}
		if(categoryId == 11) {
			category = "Horror";
		}
		if(categoryId == 12) {
			category = "Music";
		}
		if(categoryId == 13) {
			category = "New";
		}
		if(categoryId == 14) {
			category = "Sci-Fi";
		}
		if(categoryId == 15) {
			category = "Sports";
		}
		if(categoryId == 16) {
			category = "Travel";
		}
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public List<Actor> getActors() {
		List<Actor> newList = null;
		if (actors != null && actors.size() > 0){
			newList = new ArrayList<>(actors);
		}
		return newList;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		String upperTS = "FILM ID: " + id + ", Title: " + title + ", Description: " + description + ", Release Year: "
				+ releaseYear + ", Category: " + category + ", Language: " + language + ", Rental Duration: "
				+ rentalDuration + ", Rental Rate: " + rentalRate + ", Length: " + length + ", Replacement Cost: "
				+ replacementCost + ", Rating: " + rating + ", Special Features: " + specialFeatures;
		upperTS = upperTS.toUpperCase();
		return upperTS;
	}

	public String userFriendlyToString() {
		String upperUFTS = "Title: " + title + ", Release Year: " + releaseYear + ", Rating: " + rating + ", Language: "
				+ language + ", Description: " + description;
		upperUFTS = upperUFTS.toUpperCase();
		return upperUFTS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
