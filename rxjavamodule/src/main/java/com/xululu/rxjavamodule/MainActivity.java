package com.xululu.rxjavamodule;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xululu.rxjavamodule.bean.Course;
import com.xululu.rxjavamodule.bean.Student;
import com.xululu.rxjavamodule.request.inter.YDPostInterface;
import com.xululu.utilmodule.LogUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "llxu MainActivity";

    private TextView mTestTv;

    private Observable mObservable2 = Observable.just("test1", "test2", "test3", "test4","hehe");

    private Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String o) {
            LogUtil.d(TAG, "action action1"+ o);
        }
    };

    private Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            LogUtil.d(TAG, "action throwable");
        }
    };

    private Action0 onComplete = new Action0() {
        @Override
        public void call() {
            LogUtil.d(TAG, "action complete");
        }
    };


    private Button mActionTestbtn;
    private Button mTestRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestTv = findViewById(R.id.test_tv);
        mTestTv.setOnClickListener(this);
        findViewById(R.id.just_test).setOnClickListener(this);
        mActionTestbtn = findViewById(R.id.action_test);
        mActionTestbtn.setOnClickListener(this);
        mTestRetrofit = findViewById(R.id.test_retrofit);
        mTestRetrofit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mTestTv) {
            testObserve();
        } else if (v == findViewById(R.id.just_test)) {
            mObservable2.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    LogUtil.d(TAG, "just oncomplete");

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.d(TAG, "just onerror");

                }

                @Override
                public void onNext(String o) {
//                    LogUtil.d(TAG, "just onnext");
                    Log.e(TAG, "just onnext");

                }
            });
        } else if (v == mActionTestbtn) {
            mObservable2.subscribe(onNextAction, onErrorAction,onComplete);
        } else if (v == mTestRetrofit) {
            Intent i = new Intent(MainActivity.this, RetrofitTestActivity.class);
            startActivity(i);
        }
    }

    private void testObserve() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> o) {

            }
        });

        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    /**
     * this method can be used to test Thread Changing
     */
    private void testThread(){
        Observable.create(new Observable.OnSubscribe<Drawable>(){
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable o) {

            }
        });
    }

    private void testMap(){
        Student[] students = {
                new Student( "zhangsan", 10),
                new Student("lisi",18),
        };

        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {

                        return student.getName();
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    private void testFlatMap(){
        Student[] students = {
                new Student( "zhangsan", 10),
                new Student("lisi",18),
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {

            }
        });
    }
}
