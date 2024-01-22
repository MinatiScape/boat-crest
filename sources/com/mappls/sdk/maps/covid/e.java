package com.mappls.sdk.maps.covid;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
/* loaded from: classes11.dex */
interface e {
    @POST("api/places/layers/json")
    Call<ZoneInfo> a(@Header("User-Agent") String str, @Query("keywords") String str2, @Query("distance") Integer num, @Query("range") Integer num2, @Body List<ZoneInfoRequestData> list);
}
