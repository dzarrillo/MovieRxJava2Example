package com.dzartek.movierxjava2example.movie.service;

import com.dzartek.movierxjava2example.movie.Movie;
import com.dzartek.movierxjava2example.movie.pojomodel.Result;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dzarrillo on 1/23/2018.
 */

public class MovieClient {
    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/";
    private static MovieClient instance;
    private MovieService movieService;

    private MovieClient(){
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    public static MovieClient getInstance() {
        if(instance == null){
            instance = new MovieClient();
        }
        return instance;
    }

    public io.reactivex.Observable<List<Movie>> getAllMovies(@NonNull String sort_by,
                                                                       @NonNull String api_key){
        return movieService.getMovies(sort_by, api_key);
    }

}
