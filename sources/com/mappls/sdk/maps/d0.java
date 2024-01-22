package com.mappls.sdk.maps;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
/* loaded from: classes11.dex */
interface d0 {
    @POST("getStyles/json")
    Call<GetStylesResponse> a(@Query("logo_resolution") String str);
}
