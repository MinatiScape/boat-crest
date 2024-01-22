package com.mappls.sdk.services.api.reversegeocode;

import com.mappls.sdk.services.api.PlaceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/* loaded from: classes7.dex */
public interface ReverseGeoCodeService {
    @GET("advancedmaps/v1/rev_geocode")
    Call<PlaceResponse> getCall(@Query("lat") double d, @Query("lng") double d2, @Query("lang") String str);
}
