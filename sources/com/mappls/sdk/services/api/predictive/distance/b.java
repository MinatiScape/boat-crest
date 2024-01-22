package com.mappls.sdk.services.api.predictive.distance;

import com.mappls.sdk.services.api.predictive.distance.models.PredictiveDistanceResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes7.dex */
interface b {
    @GET("advancedmaps/v2/distance")
    Call<PredictiveDistanceResponse> getCall(@QueryMap Map<String, Object> map);
}
