package com.mappls.sdk.services.api.session.update;

import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;
/* loaded from: classes8.dex */
interface b {
    @PUT
    Call<SessionResponse> a(@Url String str, @Query("sessionDevice") String str2, @Body SessionRequestModel sessionRequestModel);
}
