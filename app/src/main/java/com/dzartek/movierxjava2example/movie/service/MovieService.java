package com.dzartek.movierxjava2example.movie.service;

import com.dzartek.movierxjava2example.movie.pojomodel.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dzarrillo on 1/23/2018.
 */

public interface MovieService {
    @GET("discover/movie")
    Observable<Movies> getMovies(@Query("sort_by") String sort_by,
                                 @Query("api_key") String api_key);
}
