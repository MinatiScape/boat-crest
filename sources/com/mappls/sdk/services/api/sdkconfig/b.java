package com.mappls.sdk.services.api.sdkconfig;

import com.mappls.sdk.services.api.sdkconfig.model.SDKConfigResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
/* loaded from: classes7.dex */
interface b {
    @GET
    Call<SDKConfigResponse> getCall(@Url String str);
}
