package com.xululu.aidlmodule.parcelpackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: pipilu
 * Time: 2020-02-23 16:13
 */
public class Student implements Parcelable {

    public String mName;
    public int mAge;
    public boolean mIsBoy;

    public Book mBook;

    public Student(String name, int age, boolean isBoy, Book book) {
        mName = name;
        mAge = age;
        mIsBoy = isBoy;
        mBook = book;
    }

    /**
     * 描述内容
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列化
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
        dest.writeInt(mIsBoy ? 1 : 0);
        dest.writeParcelable(mBook, 0);
    }

    /**
     * 反序列化
     */
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    /**
     * 从序列化的数据中获取对象
     */
    private Student(Parcel in) {
        mName = in.readString();
        mAge = in.readInt();
        mIsBoy = in.readInt() == 1;

        //Book是一个可序列化的对象，因此他的序列化需要传递当前线程的上下文类加载器
        mBook = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }


}
