package se.iths.maryam.javaverktyg.service;

import org.springframework.stereotype.Service;
import se.iths.maryam.javaverktyg.exception.MovieNotFoundException;
import se.iths.maryam.javaverktyg.model.Movie;
import se.iths.maryam.javaverktyg.repository.MovieRepository;
import se.iths.maryam.javaverktyg.validator.MovieValidator;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieValidator movieValidator;

    public MovieService(MovieRepository movieRepository,
                        MovieValidator movieValidator) {
        this.movieRepository = movieRepository;
        this.movieValidator = movieValidator;
    }

    // GET all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // GET one movie
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    // CREATE movie
    public Movie createMovie(Movie movie) {
        movieValidator.validate(movie);
        return movieRepository.save(movie);
    }

    // UPDATE movie
    public Movie updateMovie(Long id, Movie movie) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id " + id + " not found"));

        movieValidator.validate(movie);

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setRating(movie.getRating());

        return movieRepository.save(existingMovie);
    }

    // DELETE movie
    public void deleteMovie(Long id) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie with id " + id + " not found"));

        movieRepository.delete(existingMovie);
    }
}