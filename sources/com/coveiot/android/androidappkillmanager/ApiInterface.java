package com.coveiot.android.androidappkillmanager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
/* loaded from: classes2.dex */
public interface ApiInterface {
    @GET("data/mfr-intents-edf3d0c0923c43efa8e01d8373440eb3.json")
    Call<ResponseBody> serverRefresh();
}
