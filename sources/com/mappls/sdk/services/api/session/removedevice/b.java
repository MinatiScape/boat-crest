package com.mappls.sdk.services.api.session.removedevice;

import retrofit2.Call;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes8.dex */
interface b {
    @PATCH("api/security/sessions/{sessionType}")
    Call<Void> a(@Path("sessionType") String str, @Query("sessionDevice") String str2, @Query("deviceFingerprint") String str3);
}
