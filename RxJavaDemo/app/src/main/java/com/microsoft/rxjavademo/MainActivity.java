package com.microsoft.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple Introduction
        Observable<String> observable =
                Observable.fromArray("Vipul", "Vinay", "Vishal");

        observable.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(String name) {
                Log.i("vipul", "Received Element " + name);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("vipul", "Received Error");
            }

            @Override
            public void onComplete() {
                Log.i("vipul", "All Names Done!");
            }
        });

        // Filter Operator
        Observable<Integer> numbersObservable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbersObservable.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer % 2 == 0;
            }
        }).subscribe(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Log.i("vipul", "Number is " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("vipul", "Received Error");
            }

            @Override
            public void onComplete() {
                Log.i("vipul", "Complete!");
            }
        });

        // Distinct

        Observable<Integer> values = Observable.fromArray(1, 2, 1, 3, 7, 9, 2);
        values
                .distinct()
                .skip(2)
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.i("vipul", "Value is " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("vipul", "Received Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("vipul", "Complete!");
                    }
                });

        Observable.interval(30, TimeUnit.DAYS).subscribe(new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                Log.i("Vipul", "Calling " + new Random().nextInt());

                // Check for software updates
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
