package com.xululu.rxjavamodule.request.inter;

import com.xululu.rxjavamodule.bean.YDTranslation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: llxu4
 * Time: 2019-08-04 23:57
 */
public interface YDPostInterface {


    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<YDTranslation> getCall(@Field("i") String targetSentence);
}
