package com.mappls.sdk.services.api.session.create;

import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes8.dex */
interface b {
    @POST("api/security/sessions/{sessionType}")
    Call<SessionResponse> a(@Path("sessionType") String str, @Query("sessionDevice") String str2, @Body SessionRequestModel sessionRequestModel);
}
