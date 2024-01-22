package com.mappls.sdk.maps.auth;

import com.mappls.sdk.maps.auth.model.PublicKeyToken;
import retrofit2.Call;
import retrofit2.http.GET;
/* loaded from: classes11.dex */
interface b {
    @GET("api/advance/vectorMaps/public")
    Call<PublicKeyToken> getCall();
}
