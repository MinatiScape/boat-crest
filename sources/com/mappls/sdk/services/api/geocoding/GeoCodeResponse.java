package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.Keep;
import java.util.List;
@Keep
/* loaded from: classes6.dex */
public class GeoCodeResponse {
    private List<GeoCode> results = null;

    public List<GeoCode> getResults() {
        return this.results;
    }

    public void setResults(List<GeoCode> list) {
        this.results = list;
    }
}
