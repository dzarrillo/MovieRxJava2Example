package com.dzartek.movierxjava2example.movierxsingle;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dzarrillo on 1/29/2018.
 */

public class MovieRetroFit {
    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/";

    public MovieRxSingleService getMovieRxSingleService(){
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(MovieRxSingleService.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(createOkHttpClient())
                .build();
    }
}
