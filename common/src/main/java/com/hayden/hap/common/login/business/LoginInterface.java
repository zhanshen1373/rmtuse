package com.hayden.hap.common.login.business;

import com.hayden.hap.common.common.bussiess.ResultData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 登录接口
 * Created by liuyang on 2017/2/27.
 */

public interface LoginInterface {
    @GET("m/login/sy/getPubKey.json?act=getPubKey")
    Call<ResultData<LoginKey>> getStokenkey();

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("m/tenant/validate/login.json")
    Call<ResultData<LoginUser>> login(@Body LoginRequestBody body);

    public class LoginRequestBody {
        public String username;
        public String password;
        public String stokenKey;
        public String appVersion;
        public String equipmentCode;
    }
}
