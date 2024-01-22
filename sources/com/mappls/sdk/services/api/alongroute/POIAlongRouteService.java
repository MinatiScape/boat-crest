package com.mappls.sdk.services.api.alongroute;

import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/* loaded from: classes11.dex */
public interface POIAlongRouteService {
    @FormUrlEncoded
    @POST("api/places/along_route")
    Call<POIAlongRouteResponse> getCall(@FieldMap HashMap<String, Object> hashMap);
}
