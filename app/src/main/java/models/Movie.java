package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Parcel
public class Movie {

    String backdropPath;
    String releaseDate;
    String originalLanguage;
    String posterPath;
    String title;
    String overview;
    int movieID;
    double averageScore;


    //Empty constructor for Parcel library
    public Movie(){}

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        averageScore = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
        releaseDate = jsonObject.getString("release_date");
        originalLanguage = jsonObject.getString("original_language").toUpperCase(Locale.ROOT);

    }

    public static List<Movie> fromJsonArr(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }


    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview + "\nIMDb Score:(" + averageScore + ")";
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }
}
