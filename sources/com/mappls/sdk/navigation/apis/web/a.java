package com.mappls.sdk.navigation.apis.web;

import com.mappls.sdk.navigation.model.JunctionApiResponse;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
/* loaded from: classes11.dex */
public interface a {
    @Streaming
    @GET
    Call<ResponseBody> a(@Url String str);

    @GET("apis/O2O/action/route/junction")
    Call<JunctionApiResponse> a(@Query("routeId") String str, @Query("mode") String str2, @Query("routeIdx") Integer num, @Query("size") String str3);

    @GET("apis/O2O/action/assets/encodedURL")
    Call<Map<String, String>> a(@Query("imageName") List<String> list, @Query("mode") String str, @Query("size") String str2);
}
