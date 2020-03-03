package com.xululu.aidlmodule.parcelpackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: pipilu
 * Time: 2020-02-23 16:14
 */
public class Book implements Parcelable {

    public float price;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(price);
        dest.writeString(name);

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel in) {
        this.price = in.readFloat();
        this.name = in.readString();
    }


}
