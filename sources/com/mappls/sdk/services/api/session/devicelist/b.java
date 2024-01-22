package com.mappls.sdk.services.api.session.devicelist;

import com.mappls.sdk.services.api.session.devicelist.model.Device;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes8.dex */
interface b {
    @GET("api/security/sessions/{sessionType}")
    Call<List<Device>> a(@Path("sessionType") String str, @Query("sessionDevice") String str2);
}
