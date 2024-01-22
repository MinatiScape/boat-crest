package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class LocationCodedValue implements Serializable {
    @SerializedName("elevation")
    @Expose
    public Double elevation;
    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
}
