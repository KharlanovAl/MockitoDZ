import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AfishaManagerTest {

    @Mock
    private Movie mockMovie1;

    @Mock
    private Movie mockMovie2;

    @Mock
    private Movie mockMovie3;

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager();

        when(mockMovie1.getTitle()).thenReturn("Джентльмены");

        manager.addMovie(mockMovie1);

        Movie[] all = manager.findAll();
        assertEquals(1, all.length);
        assertEquals(mockMovie1, all[0]);

        // Проверяем только реальные вызовы
        String[] titles = manager.getMovieTitles();
        assertEquals("Джентльмены", titles[0]);

        verify(mockMovie1, times(1)).getTitle();
        // Убираем проверку getGenre(), так как он не вызывается
    }

    @Test
    public void shouldFindAllMoviesInOrder() {
        AfishaManager manager = new AfishaManager();

        when(mockMovie1.getTitle()).thenReturn("Джентльмены");
        when(mockMovie2.getTitle()).thenReturn("Отель «Белград»");
        when(mockMovie3.getTitle()).thenReturn("Человек-невидимка");

        manager.addMovie(mockMovie1);
        manager.addMovie(mockMovie2);
        manager.addMovie(mockMovie3);

        Movie[] all = manager.findAll();
        assertEquals(3, all.length);
        assertEquals(mockMovie1, all[0]);
        assertEquals(mockMovie2, all[1]);
        assertEquals(mockMovie3, all[2]);

        // Проверяем вызовы методов через вспомогательные методы
        String[] titles = manager.getMovieTitles();
        assertEquals("Джентльмены", titles[0]);
        assertEquals("Отель «Белград»", titles[1]);
        assertEquals("Человек-невидимка", titles[2]);

        verify(mockMovie1, times(1)).getTitle();
        verify(mockMovie2, times(1)).getTitle();
        verify(mockMovie3, times(1)).getTitle();
    }

    @Test
    public void shouldFindLastMoviesDefaultLimit() {
        AfishaManager manager = new AfishaManager();

        when(mockMovie1.getTitle()).thenReturn("Джентльмены");
        when(mockMovie2.getTitle()).thenReturn("Отель «Белград»");
        when(mockMovie3.getTitle()).thenReturn("Человек-невидимка");

        manager.addMovie(mockMovie1);
        manager.addMovie(mockMovie2);
        manager.addMovie(mockMovie3);

        Movie[] last = manager.findLast();
        assertEquals(3, last.length);
        assertEquals(mockMovie3, last[0]);
        assertEquals(mockMovie2, last[1]);
        assertEquals(mockMovie1, last[2]);

        // Проверяем вызовы через вспомогательный метод
        String[] lastTitles = manager.getLastMovieTitles();
        assertEquals("Человек-невидимка", lastTitles[0]);
        assertEquals("Отель «Белград»", lastTitles[1]);
        assertEquals("Джентльмены", lastTitles[2]);

        verify(mockMovie1, times(1)).getTitle();
        verify(mockMovie2, times(1)).getTitle();
        verify(mockMovie3, times(1)).getTitle();
    }

    @Test
    public void shouldFindLastMoviesCustomLimit() {
        AfishaManager manager = new AfishaManager(2);

        // Создаем заглушки только для тех фильмов, которые будут использоваться
        when(mockMovie2.getTitle()).thenReturn("Отель «Белград»");
        when(mockMovie3.getTitle()).thenReturn("Человек-невидимка");

        // Добавляем все фильмы, но заглушки создаем только для нужных
        Movie unusedMovie = new Movie("Джентльмены", "боевик"); // Реальный объект вместо мока
        manager.addMovie(unusedMovie);
        manager.addMovie(mockMovie2);
        manager.addMovie(mockMovie3);

        Movie[] last = manager.findLast();
        assertEquals(2, last.length);
        assertEquals(mockMovie3, last[0]);
        assertEquals(mockMovie2, last[1]);

        String[] lastTitles = manager.getLastMovieTitles();
        assertEquals("Человек-невидимка", lastTitles[0]);
        assertEquals("Отель «Белград»", lastTitles[1]);

        verify(mockMovie2, times(1)).getTitle();
        verify(mockMovie3, times(1)).getTitle();
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

        when(mockMovie1.getTitle()).thenReturn("Джентльмены");

        manager.addMovie(mockMovie1);

        Movie[] all = manager.findAll();
        Movie[] last = manager.findLast();

        assertEquals(1, all.length);
        assertEquals(1, last.length);
        assertEquals(mockMovie1, all[0]);
        assertEquals(mockMovie1, last[0]);

        String[] titles = manager.getMovieTitles();
        String[] lastTitles = manager.getLastMovieTitles();

        assertEquals("Джентльмены", titles[0]);
        assertEquals("Джентльмены", lastTitles[0]);

        verify(mockMovie1, times(2)).getTitle(); // 2 раза: для titles и lastTitles
        // Убираем проверку getGenre(), так как он не вызывается
    }
    //Полное покрытие
    @Test
    public void shouldGetMovieGenres() {
        AfishaManager manager = new AfishaManager();

        when(mockMovie1.getGenre()).thenReturn("боевик");
        when(mockMovie2.getGenre()).thenReturn("комедия");
        when(mockMovie3.getGenre()).thenReturn("ужасы");

        manager.addMovie(mockMovie1);
        manager.addMovie(mockMovie2);
        manager.addMovie(mockMovie3);

        String[] genres = manager.getMovieGenres();

        assertEquals(3, genres.length);
        assertEquals("боевик", genres[0]);
        assertEquals("комедия", genres[1]);
        assertEquals("ужасы", genres[2]);

        verify(mockMovie1, times(1)).getGenre();
        verify(mockMovie2, times(1)).getGenre();
        verify(mockMovie3, times(1)).getGenre();
    }


}

