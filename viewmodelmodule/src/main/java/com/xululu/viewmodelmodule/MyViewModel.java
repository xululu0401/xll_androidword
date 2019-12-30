package com.xululu.viewmodelmodule;


import androidx.lifecycle.ViewModel;

import com.xululu.viewmodelmodule.data.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: pipilu
 * Time: 2019-08-09 22:47
 */
public class MyViewModel extends ViewModel {

    private List<Student> mStudents = new ArrayList<>();
    private int countFlag;

    public List<Student> getData() {
        return mStudents;
    }

    public void addData(){
        Random random = new Random(countFlag);
        String name = random.nextInt()+"*****test******";
        String stuId = "studentid   " + random.nextInt(100);
        int age = random.nextInt();
        mStudents.add(new Student(stuId, name, age));
        countFlag++;
    }

    public void removeDatas(){
        Random random = new Random(countFlag);
        int rmIndex = random.nextInt(mStudents.size());
        mStudents.remove(rmIndex);
    }


}
