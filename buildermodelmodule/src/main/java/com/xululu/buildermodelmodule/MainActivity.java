package com.xululu.buildermodelmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xululu.buildermodelmodule.data.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Student student = new Student.Builder()
                .setAge(2)
                .setName("zhangsan")
                .setStuId("0000")
                .build();
        Log.e("llxu4", student.getName()+"   ***   "+ student.getStuId()+"   ***   "+
                student.getAge());
    }
}
