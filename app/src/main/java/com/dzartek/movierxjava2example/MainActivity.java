package com.dzartek.movierxjava2example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dzartek.movierxjava2example.movie.Movie;
import com.dzartek.movierxjava2example.movie.service.MovieClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String api_key = "put Your api key here";
        String sort_by = "popularity.desc";
        getAllMovies(sort_by, api_key);
    }

    @Override
    protected void onDestroy() {
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void getAllMovies(String sort_by, String api_key){
        disposable = MovieClient.getInstance()
                .getAllMovies(sort_by, api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                               @Override
                               public void accept(List<Movie> movies) throws Exception {
                                   Log.i(TAG, "Movies size: " + movies.size());
                                   // Populate adapter

                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i(TAG, "RxJava2, HTTP Error: " + throwable.getMessage());
                            }
                        });
    }
}
