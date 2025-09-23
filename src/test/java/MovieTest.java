import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    public void shouldCreateMovieWithTitleAndGenre() {
        Movie movie = new Movie("Джентльмены", "боевик");

        assertEquals("Джентльмены", movie.getTitle());
        assertEquals("боевик", movie.getGenre());
    }

    @Test
    public void shouldReturnCorrectToString() {
        Movie movie = new Movie("Отель «Белград»", "комедия");

        String result = movie.toString();
        assertEquals("Отель «Белград» (комедия)", result);
    }

    @Test
    public void shouldHandleEmptyStrings() {
        Movie movie = new Movie("", "");

        assertEquals("", movie.getTitle());
        assertEquals("", movie.getGenre());
        assertEquals(" ()", movie.toString());
    }

    @Test
    public void shouldHandleNullStrings() {
        Movie movie = new Movie(null, null);

        assertNull(movie.getTitle());
        assertNull(movie.getGenre());
        assertEquals("null (null)", movie.toString());
    }
}