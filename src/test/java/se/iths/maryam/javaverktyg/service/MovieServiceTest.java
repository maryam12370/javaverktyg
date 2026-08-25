package se.iths.maryam.javaverktyg.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.iths.maryam.javaverktyg.exception.MovieNotFoundException;
import se.iths.maryam.javaverktyg.model.Movie;
import se.iths.maryam.javaverktyg.repository.MovieRepository;
import se.iths.maryam.javaverktyg.validator.MovieValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieValidator movieValidator;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieService = new MovieService(movieRepository, movieValidator);
    }

    @Test
    void getAllMoviesShouldReturnAllMovies() {

        Movie movie1 = new Movie();
        movie1.setTitle("Inception");

        Movie movie2 = new Movie();
        movie2.setTitle("Titanic");

        when(movieRepository.findAll())
                .thenReturn(List.of(movie1, movie2));

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
        assertEquals("Inception", result.get(0).getTitle());
        assertEquals("Titanic", result.get(1).getTitle());

        verify(movieRepository).findAll();
    }

    @Test
    void getMovieByIdShouldReturnMovie() {

        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieById(1L);

        assertEquals("Inception", result.getTitle());

        verify(movieRepository).findById(1L);
    }

    @Test
    void getMovieByIdShouldThrowExceptionWhenMovieDoesNotExist() {

        when(movieRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.getMovieById(999L)
        );

        verify(movieRepository).findById(999L);
    }

    @Test
    void createMovieShouldValidateAndSaveMovie() {

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setReleaseYear(2010);
        movie.setRating(8.8);

        when(movieRepository.save(movie))
                .thenReturn(movie);

        Movie result = movieService.createMovie(movie);

        verify(movieValidator).validate(movie);
        verify(movieRepository).save(movie);

        assertEquals("Inception", result.getTitle());
    }

    @Test
    void updateMovieShouldUpdateAndSaveMovie() {

        Movie existingMovie = new Movie();
        existingMovie.setTitle("Old Title");

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Inception");
        updatedMovie.setGenre("Sci-Fi");
        updatedMovie.setReleaseYear(2010);
        updatedMovie.setRating(8.8);

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(existingMovie));

        when(movieRepository.save(existingMovie))
                .thenReturn(existingMovie);

        Movie result = movieService.updateMovie(1L, updatedMovie);

        verify(movieRepository).findById(1L);
        verify(movieValidator).validate(updatedMovie);
        verify(movieRepository).save(existingMovie);

        assertEquals("Inception", result.getTitle());
        assertEquals("Sci-Fi", result.getGenre());
        assertEquals(2010, result.getReleaseYear());
        assertEquals(8.8, result.getRating());
    }

    @Test
    void updateMovieShouldThrowExceptionWhenMovieDoesNotExist() {

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Inception");

        when(movieRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.updateMovie(999L, updatedMovie)
        );

        verify(movieRepository).findById(999L);
        verify(movieValidator, never()).validate(any());
        verify(movieRepository, never()).save(any());
    }

    @Test
    void deleteMovieShouldDeleteMovie() {

        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(movie));

        movieService.deleteMovie(1L);

        verify(movieRepository).findById(1L);
        verify(movieRepository).delete(movie);
    }

    @Test
    void deleteMovieShouldThrowExceptionWhenMovieDoesNotExist() {

        when(movieRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.deleteMovie(999L)
        );

        verify(movieRepository).findById(999L);
        verify(movieRepository, never()).delete(any());
    }
}
