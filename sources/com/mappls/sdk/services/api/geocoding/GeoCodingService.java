package com.mappls.sdk.services.api.geocoding;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes6.dex */
public interface GeoCodingService {
    @GET("api/places/geocode")
    Call<GeoCodeResponse> getCall(@QueryMap Map<String, Object> map);
}
