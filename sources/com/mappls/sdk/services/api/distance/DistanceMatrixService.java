package com.mappls.sdk.services.api.distance;

import com.mappls.sdk.services.api.distance.models.DistanceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes11.dex */
public interface DistanceMatrixService {
    @GET("advancedmaps/v1/{rest_api_key}/{resource}/{profile}/{coordinates}")
    Call<DistanceResponse> getCall(@Path("resource") String str, @Path("profile") String str2, @Path("coordinates") String str3, @Path("rest_api_key") String str4, @Query("rtype") Integer num, @Query("sources") String str5, @Query("destinations") String str6, @Query("fallback_speed") Double d, @Query("fallback_coordinate") String str7);
}
