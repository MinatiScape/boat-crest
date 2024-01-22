package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitLegGeometry {
    @SerializedName("length")
    private Integer length;
    @SerializedName("points")
    private String points;

    public Integer getLength() {
        return this.length;
    }

    public String getPoints() {
        return this.points;
    }

    public void setLength(Integer num) {
        this.length = num;
    }

    public void setPoints(String str) {
        this.points = str;
    }
}
