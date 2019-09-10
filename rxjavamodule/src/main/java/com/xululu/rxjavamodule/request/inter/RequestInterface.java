package com.xululu.rxjavamodule.request.inter;

import com.xululu.rxjavamodule.bean.Translation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 该接口用于描述网络请求
 * Author: pipilu
 * Time: 2019-08-04 13:16
 */
public interface RequestInterface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();

}
