package com.mappls.sdk.services.api.hateaosnearby;

import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
/* loaded from: classes6.dex */
public interface NearbyHateosService {
    @GET
    Call<NearbyAtlasResponse> getCall(@Url String str);
}
