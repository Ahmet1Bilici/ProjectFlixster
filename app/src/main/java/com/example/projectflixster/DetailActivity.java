package com.example.projectflixster;

import models.Movie;
import okhttp3.Headers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

public class DetailActivity extends YouTubeBaseActivity {
    public static final String YOUTUBE_API_KEY = "AIzaSyAl6yvz0VP94ooEsSxEFQACnauG4VPIMU0";
    public static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvMovieTitle;
    TextView tvOverview;
    TextView tvOriginalLanguage;
    TextView tvReleaseDate;
    RatingBar averageScore;
    YouTubePlayerView youTubePlayerView;


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Objects.requireNonNull(getSupportActionBar()).hide();

        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvOverview = findViewById(R.id.tvOverview);
        averageScore = findViewById(R.id.ratingBar);
        tvOriginalLanguage = findViewById(R.id.originalLanguageTextView);
        tvReleaseDate = findViewById(R.id.releaseDateTextView);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);


        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        tvMovieTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvOriginalLanguage.setText("Original Language: " + movie.getOriginalLanguage());
        tvReleaseDate.setText("Release Date : " + movie.getReleaseDate());
        averageScore.setRating((float)movie.getAverageScore());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieID()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray jsonArray = json.jsonObject.getJSONArray("results");
                    if(jsonArray.length() == 0){
                        return;
                    }
                    //finds the first trail published
                    String youtubeKey = jsonArray.getJSONObject(jsonArray.length()-1).getString("key");
                    Log.d("DetailActivity", youtubeKey);
                    initializeYoutube(youtubeKey);
                }catch (JSONException e){
                    Log.d("DetailActivity", "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });


    }

    private void initializeYoutube(String youtubeKey) {

        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");
            }
        });
    }
}