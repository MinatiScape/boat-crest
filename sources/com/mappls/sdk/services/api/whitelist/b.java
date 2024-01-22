package com.mappls.sdk.services.api.whitelist;

import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/* loaded from: classes8.dex */
interface b {
    @FormUrlEncoded
    @POST("api/sso/login/viaOtp")
    Call<AtlasAuthToken> getCall(@FieldMap Map<String, String> map);
}
