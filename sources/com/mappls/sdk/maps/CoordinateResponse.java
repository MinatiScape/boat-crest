package com.mappls.sdk.maps;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
class CoordinateResponse {
    @SerializedName("results")
    @Expose
    private List<CoordinateResult> results = null;

    public List<CoordinateResult> getResults() {
        return this.results;
    }

    public void setResults(List<CoordinateResult> list) {
        this.results = list;
    }
}
