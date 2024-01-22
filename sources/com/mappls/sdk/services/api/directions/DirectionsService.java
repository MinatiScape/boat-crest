package com.mappls.sdk.services.api.directions;

import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes11.dex */
public interface DirectionsService {
    @GET("advancedmaps/v1/{rest_api_ley}/{resource}/{profile}/{coordinates}")
    Call<DirectionsResponse> getCall(@Path("profile") String str, @Path("resource") String str2, @Path("coordinates") String str3, @Path("rest_api_ley") String str4, @Query("alternatives") Boolean bool, @Query("geometries") String str5, @Query("overview") String str6, @Query("radiuses") String str7, @Query("steps") Boolean bool2, @Query("bearings") String str8, @Query("lessverbose") Boolean bool3, @Query("annotations") String str9, @Query("language") String str10, @Query("roundabout_exits") Boolean bool4, @Query("continue_straight") Boolean bool5, @Query("banner_instructions") Boolean bool6, @Query("exclude") String str11, @Query("routeRefresh") Boolean bool7, @Query("deviceId") String str12, @Query("sessionId") String str13, @Query("isSort") Boolean bool8, @Query("skip_waypoints") Boolean bool9, @Query("instructions") Boolean bool10, @Query("rtype") Integer num);

    @FormUrlEncoded
    @POST("advancedmaps/v1/{rest_api_ley}/{resource}/{profile}")
    Call<DirectionsResponse> postCall(@Path("profile") String str, @Path("resource") String str2, @Field("coordinates") String str3, @Query("rest_api_ley") String str4, @Query("alternatives") Boolean bool, @Query("geometries") String str5, @Query("overview") String str6, @Query("radiuses") String str7, @Query("steps") Boolean bool2, @Query("bearings") String str8, @Query("lessverbose") Boolean bool3, @Query("annotations") String str9, @Query("language") String str10, @Query("roundabout_exits") Boolean bool4, @Query("continue_straight") Boolean bool5, @Query("banner_instructions") Boolean bool6, @Query("exclude") String str11, @Query("routeRefresh") Boolean bool7, @Query("deviceId") String str12, @Query("sessionId") String str13, @Query("isSort") Boolean bool8, @Query("skip_waypoints") Boolean bool9, @Query("instructions") Boolean bool10, @Query("rtype") Integer num);
}
