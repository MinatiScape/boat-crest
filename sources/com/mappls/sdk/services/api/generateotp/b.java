package com.mappls.sdk.services.api.generateotp;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
/* loaded from: classes6.dex */
interface b {
    @POST("api/users/authenticate")
    Call<Void> getCall(@Query("handle") String str);
}
