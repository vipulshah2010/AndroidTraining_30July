package com.microsoft.rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;

public class RxJavaBasicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<String> observable = Observable.create
                (new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter)
                            throws Exception {
                        emitter.onNext("Akshay");
                        emitter.onNext("Vishal");
                        emitter.onError(new NullPointerException
                                ("Some Error Occurred!"));
                        emitter.onNext("Vinay");
                        emitter.onNext("Vipul");
                        emitter.onComplete();
                    }
                });

        Observer<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i("vipul", s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.i("vipul", "All Completed!");
            }
        };

        observable.subscribe(observer);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);
        numbers.add(12);

        Observable<String> stringObservable = Observable
                .fromIterable(numbers)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .distinct()
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                        return currencyFormat.format(integer);
                    }
                });

        stringObservable.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(String currency) {
                Toast.makeText(RxJavaBasicsActivity.this, currency,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(RxJavaBasicsActivity.this, "Done!",
                        Toast.LENGTH_LONG).show();
            }
        });

        Observable<String> countries = Observable
                .fromArray("India", "China", "Japan", "Australia");
        Observable<Integer> population = Observable
                .fromArray(1000, 2000, 500, 300);
        Observable<String> food = Observable
                .fromArray("Khichdi", "Noodles", "Sushi", "Kangaroo");

        Observable.zip(countries, population, food, new Function3<String, Integer, String, String>() {
            @Override
            public String apply(String name, Integer population, String dish) throws Exception {
                return name + " has population of " + population + " and famous dish is " + dish;
            }
        }).subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(RxJavaBasicsActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(RxJavaBasicsActivity.this, "Done!", Toast.LENGTH_LONG).show();
            }
        });

    }


}
