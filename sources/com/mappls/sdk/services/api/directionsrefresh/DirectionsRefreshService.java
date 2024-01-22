package com.mappls.sdk.services.api.directionsrefresh;

import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes11.dex */
public interface DirectionsRefreshService {
    @GET("advancedmaps/v1/{rest_api_ley}/route_eta_refresh/{profile}")
    Call<DirectionsRoute> getCall(@Path("rest_api_ley") String str, @Path("profile") String str2, @Query("routeId") String str3, @Query("routeIndex") Integer num, @Query("isRefresh") Boolean bool, @Query("isNotify") Boolean bool2, @Query("nodeIndex") Long l, @Query("categories") String str4, @Query("tripType") Integer num2, @Query("sessionId") String str5, @Query("isSort") Boolean bool3, @Query("source") String str6);
}
