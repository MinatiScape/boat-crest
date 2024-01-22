package com.mappls.sdk.services.api.event.route;

import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/* loaded from: classes4.dex */
public interface RouteSummaryService {
    @GET("apis/O2O/action/report/route")
    Call<RouteReportSummaryResponse> getCall(@Query("routeId") String str, @Query("routeIdx") Integer num, @Query("nodeId") String str2, @Query("isGroup") Integer num2, @Query("categories") String str3, @Query("scrName") String str4);
}
