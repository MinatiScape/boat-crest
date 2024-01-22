package com.mappls.sdk.services.api.auth;

import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/* loaded from: classes11.dex */
interface a {
    @FormUrlEncoded
    @POST("api/security/oauth/token")
    Call<AtlasAuthToken> a(@Field("client_id") String str, @Field("client_secret") String str2, @Field("refresh_token") String str3, @Field("grant_type") String str4);
}
