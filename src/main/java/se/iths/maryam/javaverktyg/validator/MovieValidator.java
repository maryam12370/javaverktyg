package se.iths.maryam.javaverktyg.validator;

import org.springframework.stereotype.Component;
import se.iths.maryam.javaverktyg.model.Movie;

@Component
public class MovieValidator {

    public void validate(Movie movie) {
        validateTitle(movie.getTitle());
        validateGenre(movie.getGenre());
        validateReleaseYear(movie.getReleaseYear());
        validateRating(movie.getRating());
    }

    public void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    public void validateGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be empty");
        }
    }

    public void validateReleaseYear(int releaseYear) {
        if (releaseYear < 1888) {
            throw new IllegalArgumentException("Release year is not valid");
        }
    }

    public void validateRating(double rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10");
        }
    }
}