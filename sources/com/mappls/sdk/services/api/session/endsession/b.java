package com.mappls.sdk.services.api.session.endsession;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes8.dex */
interface b {
    @DELETE("api/security/sessions/{sessionType}")
    Call<Void> a(@Path("sessionType") String str, @Query("sessionDevice") String str2);
}
