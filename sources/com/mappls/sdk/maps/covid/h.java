package com.mappls.sdk.maps.covid;

import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
/* loaded from: classes11.dex */
interface h {
    @GET("api/covid/wms")
    Observable<ResponseBody> getCall(@QueryMap HashMap<String, String> hashMap);
}
