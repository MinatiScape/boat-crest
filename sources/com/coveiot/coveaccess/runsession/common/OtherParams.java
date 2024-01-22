package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class OtherParams implements Serializable {
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose
    public Float height;
    @SerializedName("runningStrideLength")
    @Expose
    public Float runningStrideLength;
    @SerializedName("walkingStrideLength")
    @Expose
    public Float walkingStrideLength;
    @SerializedName("weight")
    @Expose
    public Float weight;
}
