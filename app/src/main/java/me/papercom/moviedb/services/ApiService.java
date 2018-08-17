package me.papercom.moviedb.services;

import me.papercom.moviedb.BuildConfig;
import me.papercom.moviedb.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hasan on 7/13/18.
 */

public interface ApiService {

    @GET("search/movie?api_key="+ BuildConfig.API_KEY)
    Call<MovieResponse> getFilmSearch(@Query("query") String movie_name);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(@Query("api_key") String apiKey);

}
