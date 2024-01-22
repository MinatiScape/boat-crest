package com.mappls.sdk.services.api.textsearch;

import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes8.dex */
public interface TextSearchService {
    @GET("api/places/textsearch/json")
    Call<AutoSuggestAtlasResponse> getCall(@QueryMap Map<String, Object> map);
}
