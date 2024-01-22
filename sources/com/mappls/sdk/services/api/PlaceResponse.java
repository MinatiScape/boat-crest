package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class PlaceResponse {
    @SerializedName("results")
    @Expose
    private List<Place> places = null;
    @SerializedName("responseCode")
    @Expose
    private long responseCode;
    @SerializedName("version")
    @Expose
    private String version;

    public List<Place> getPlaces() {
        return this.places;
    }

    public long getResponseCode() {
        return this.responseCode;
    }

    public String getVersion() {
        return this.version;
    }

    public void setPlaces(List<Place> list) {
        this.places = list;
    }

    public void setResponseCode(long j) {
        this.responseCode = j;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
