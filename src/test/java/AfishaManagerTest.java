import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AfishaManagerTest {

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");

        manager.addMovie(movie1);

        Movie[] all = manager.findAll();
        assertArrayEquals(new Movie[]{movie1}, all);

        String[] titles = manager.getMovieTitles();
        assertArrayEquals(new String[]{"Джентльмены"}, titles);
    }

    @Test
    public void shouldFindAllMoviesInOrder() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");
        Movie movie2 = new Movie("Отель «Белград»", "комедия");
        Movie movie3 = new Movie("Человек-невидимка", "ужасы");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] all = manager.findAll();
        assertArrayEquals(new Movie[]{movie1, movie2, movie3}, all);

        String[] titles = manager.getMovieTitles();
        assertArrayEquals(new String[]{"Джентльмены", "Отель «Белград»", "Человек-невидимка"}, titles);
    }

    @Test
    public void shouldFindLastMoviesDefaultLimit() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");
        Movie movie2 = new Movie("Отель «Белград»", "комедия");
        Movie movie3 = new Movie("Человек-невидимка", "ужасы");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] last = manager.findLast();
        assertArrayEquals(new Movie[]{movie3, movie2, movie1}, last);

        String[] lastTitles = manager.getLastMovieTitles();
        assertArrayEquals(new String[]{"Человек-невидимка", "Отель «Белград»", "Джентльмены"}, lastTitles);
    }

    @Test
    public void shouldFindLastMoviesCustomLimit() {
        AfishaManager manager = new AfishaManager(2);
        Movie movie1 = new Movie("Джентльмены", "боевик");
        Movie movie2 = new Movie("Отель «Белград»", "комедия");
        Movie movie3 = new Movie("Человек-невидимка", "ужасы");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        Movie[] last = manager.findLast();
        assertArrayEquals(new Movie[]{movie3, movie2}, last);

        String[] lastTitles = manager.getLastMovieTitles();
        assertArrayEquals(new String[]{"Человек-невидимка", "Отель «Белград»"}, lastTitles);
    }

    @Test
    public void shouldHandleEmptyManager() {
        AfishaManager manager = new AfishaManager();

        Movie[] all = manager.findAll();
        Movie[] last = manager.findLast();
        String[] titles = manager.getMovieTitles();
        String[] lastTitles = manager.getLastMovieTitles();

        assertArrayEquals(new Movie[]{}, all);
        assertArrayEquals(new Movie[]{}, last);
        assertArrayEquals(new String[]{}, titles);
        assertArrayEquals(new String[]{}, lastTitles);
    }

    @Test
    public void shouldWorkWithSingleMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");

        manager.addMovie(movie1);

        Movie[] all = manager.findAll();
        Movie[] last = manager.findLast();

        assertArrayEquals(new Movie[]{movie1}, all);
        assertArrayEquals(new Movie[]{movie1}, last);

        String[] titles = manager.getMovieTitles();
        String[] lastTitles = manager.getLastMovieTitles();

        assertArrayEquals(new String[]{"Джентльмены"}, titles);
        assertArrayEquals(new String[]{"Джентльмены"}, lastTitles);
    }

    @Test
    public void shouldGetMovieGenres() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");
        Movie movie2 = new Movie("Отель «Белград»", "комедия");
        Movie movie3 = new Movie("Человек-невидимка", "ужасы");

        manager.addMovie(movie1);
        manager.addMovie(movie2);
        manager.addMovie(movie3);

        String[] genres = manager.getMovieGenres();
        assertArrayEquals(new String[]{"боевик", "комедия", "ужасы"}, genres);
    }
}