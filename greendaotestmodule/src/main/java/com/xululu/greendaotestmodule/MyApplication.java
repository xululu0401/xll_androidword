package com.xululu.greendaotestmodule;

import android.app.Application;

import com.xululu.greendaotestmodule.dao.DaoMaster;
import com.xululu.greendaotestmodule.dao.DaoSession;
import com.xululu.greendaotestmodule.dao.StudentDao;

/**
 * greenDao的使用
 * Author: pipilu
 * Time: 2019/6/18 09:22
 */
public class MyApplication extends Application {

    private StudentDao mStuDao;

    private static MyApplication mApp;

    public MyApplication getInstance(){
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        createDatebase();
    }

    private void createDatebase(){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "Student.db");
        DaoMaster master = new DaoMaster(openHelper.getWritableDb());
        DaoSession session = master.newSession();
        mStuDao = session.getStudentDao();
    }

    public StudentDao getStudentDao(){
        return mStuDao;
    }
}
