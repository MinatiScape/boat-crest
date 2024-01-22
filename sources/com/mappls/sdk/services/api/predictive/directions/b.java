package com.mappls.sdk.services.api.predictive.directions;

import com.mappls.sdk.services.api.predictive.directions.model.PredictiveDirectionsResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes7.dex */
interface b {
    @GET("advancedmaps/v2/route")
    Call<PredictiveDirectionsResponse> getCall(@QueryMap Map<String, Object> map);
}
