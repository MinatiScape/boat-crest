package com.mappls.sdk.traffic.api;

import com.mappls.sdk.traffic.model.BeaconPacket;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
/* loaded from: classes8.dex */
public interface b {
    @POST("api/probes")
    Call<ResponseBody> a(@Query("key") String str, @Body BeaconPacket beaconPacket);
}
