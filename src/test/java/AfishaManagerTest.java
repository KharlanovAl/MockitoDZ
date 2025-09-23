import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AfishaManagerTest {

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");

        manager.addMovie(movie1);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertEquals(movie1, all[0]);

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
        assertEquals(3, all.length);
        assertEquals(movie1, all[0]);
        assertEquals(movie2, all[1]);
        assertEquals(movie3, all[2]);

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
        assertEquals(3, last.length);
        assertEquals(movie3, last[0]);
        assertEquals(movie2, last[1]);
        assertEquals(movie1, last[2]);

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
        assertEquals(2, last.length);
        assertEquals(movie3, last[0]);
        assertEquals(movie2, last[1]);

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

        assertEquals(0, all.length);
        assertEquals(0, last.length);
        assertEquals(0, titles.length);
        assertEquals(0, lastTitles.length);
    }

    @Test
    public void shouldWorkWithSingleMovie() {
        AfishaManager manager = new AfishaManager();
        Movie movie1 = new Movie("Джентльмены", "боевик");

        manager.addMovie(movie1);

        Movie[] all = manager.findAll();
        Movie[] last = manager.findLast();

        assertEquals(1, all.length);
        assertEquals(1, last.length);
        assertEquals(movie1, all[0]);
        assertEquals(movie1, last[0]);

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

        assertEquals(3, genres.length);
        assertArrayEquals(new String[]{"боевик", "комедия", "ужасы"}, genres);
    }
}