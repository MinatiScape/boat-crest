package com.mappls.sdk.services.api.costestimation;

import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
interface b {
    @GET("apis/O2O/action/route/costEstimation")
    Call<CostEstimationResponse> getCall(@QueryMap Map<String, Object> map);
}
