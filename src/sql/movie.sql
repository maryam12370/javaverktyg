CREATE TABLE movie (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       genre VARCHAR(100) NOT NULL,
                       release_year INTEGER NOT NULL,
                       rating DOUBLE PRECISION NOT NULL
);