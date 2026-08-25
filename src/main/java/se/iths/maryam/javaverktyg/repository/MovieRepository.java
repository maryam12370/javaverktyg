package se.iths.maryam.javaverktyg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.maryam.javaverktyg.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
