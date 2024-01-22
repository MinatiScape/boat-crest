package com.mappls.sdk.services.api.publickey;

import com.mappls.sdk.services.api.publickey.model.PublicKeyResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes7.dex */
interface b {
    @POST("api/security/sdk/public")
    Call<PublicKeyResponse> a(@Body PublicKeyRequest publicKeyRequest);
}
