package com.mappls.sdk.services.api.session.delete;

import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Url;
/* loaded from: classes8.dex */
interface b {
    @DELETE
    Call<SessionResponse> getCall(@Url String str);
}
