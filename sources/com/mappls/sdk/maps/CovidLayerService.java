package com.mappls.sdk.maps;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
/* loaded from: classes11.dex */
public interface CovidLayerService {
    @GET("getCovidDatasetList")
    Call<List<InteractiveLayer>> getCall();
}
