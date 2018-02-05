package com.dzartek.movierxjava2example.movierxsingle;

import com.dzartek.movierxjava2example.movie.pojomodel.Movies;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dzarrillo on 1/28/2018.
 */

public interface MovieRxSingleService {

    @GET("discover/movie")
    Single<Movies> getMovies(@Query("sort_by") String sort_by,
                            @Query("api_key") String api_key);
}
