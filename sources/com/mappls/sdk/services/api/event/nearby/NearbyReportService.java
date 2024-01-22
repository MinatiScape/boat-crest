package com.mappls.sdk.services.api.event.nearby;

import com.mappls.sdk.services.api.event.nearby.model.NearbyReportResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/* loaded from: classes3.dex */
public interface NearbyReportService {
    @GET("apis/O2O/action/report/nearby")
    Call<NearbyReportResponse> getCall(@Query("minX") double d, @Query("minY") double d2, @Query("maxX") double d3, @Query("maxY") double d4);
}
