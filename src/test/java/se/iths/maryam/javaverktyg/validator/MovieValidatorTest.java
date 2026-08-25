package se.iths.maryam.javaverktyg.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.maryam.javaverktyg.model.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private MovieValidator movieValidator;

    @BeforeEach
    void setUp() {
        movieValidator = new MovieValidator();
    }

    @Test
    void validateShouldAcceptValidMovie() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        assertDoesNotThrow(() -> movieValidator.validate(movie));
    }

    @Test
    void validateShouldRejectEmptyTitle() {

        Movie movie = new Movie();
        movie.setTitle("");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectNullTitle() {

        Movie movie = new Movie();
        movie.setTitle(null);
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectEmptyGenre() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("");
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectNullGenre() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre(null);
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectInvalidReleaseYear() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(1800);
        movie.setRating(8.8);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectRatingBelowZero() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(-1);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }

    @Test
    void validateShouldRejectRatingAboveTen() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(11);

        assertThrows(
                IllegalArgumentException.class,
                () -> movieValidator.validate(movie)
        );
    }
}
