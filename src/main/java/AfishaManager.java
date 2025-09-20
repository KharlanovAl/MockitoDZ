public class AfishaManager {

    private Movie[] movies;
    private int limit;

    public AfishaManager() {
        this(5);
    }

    public AfishaManager(int limit) {
        this.limit = limit;
        this.movies = new Movie[0];
    }

    public void addMovie(Movie movie) {
        Movie[] tmp = new Movie[movies.length + 1];
        for (int i = 0; i < movies.length; i++) {
            tmp[i] = movies[i];
        }
        tmp[tmp.length - 1] = movie;
        movies = tmp;
    }

    public Movie[] findAll() {
        return movies.clone(); // возвращаем копию для безопасности
    }

    public Movie[] findLast() {
        int resultLength;
        if (movies.length < limit) {
            resultLength = movies.length;
        } else {
            resultLength = limit;
        }

        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[movies.length - 1 - i];
        }
        return result;
    }

    // Метод для вывода информации о фильмах Title и Genres
    public String[] getMovieTitles() {
        String[] titles = new String[movies.length];
        for (int i = 0; i < movies.length; i++) {
            titles[i] = movies[i].getTitle();
        }
        return titles;
    }

    public String[] getMovieGenres() {
        String[] genres = new String[movies.length];
        for (int i = 0; i < movies.length; i++) {
            genres[i] = movies[i].getGenre();
        }
        return genres;
    }

    public String[] getLastMovieTitles() {
        Movie[] lastMovies = findLast();
        String[] titles = new String[lastMovies.length];
        for (int i = 0; i < lastMovies.length; i++) {
            titles[i] = lastMovies[i].getTitle();
        }
        return titles;
    }
}
