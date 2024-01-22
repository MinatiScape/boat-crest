package com.mappls.sdk.services.api.geolocation;

import com.mappls.sdk.services.api.geolocation.model.GeolocationResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes6.dex */
public interface GeolocationService {
    @POST("api/places/geo-location")
    Call<GeolocationResponse> getCall(@Body Map map);
}
