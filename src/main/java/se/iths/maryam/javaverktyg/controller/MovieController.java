package se.iths.maryam.javaverktyg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.maryam.javaverktyg.model.Movie;
import se.iths.maryam.javaverktyg.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // GET /movies
    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies";
    }

    // GET /movies/{id}
    @GetMapping("/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "movie-details";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "edit-movie";
    }

    // POST /movies
    @PostMapping
    public String createMovie(@ModelAttribute Movie movie) {
        movieService.createMovie(movie);
        return "redirect:/movies";
    }

    // PUT /movies/{id}
    @PutMapping("/{id}")
    public String updateMovie(@PathVariable Long id,
                              @ModelAttribute Movie movie) {
        movieService.updateMovie(id, movie);
        return "redirect:/movies";
    }

    // DELETE /movies/{id}
    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}