package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class Target implements Serializable {
    @SerializedName("baseUnit")
    @Expose
    public String baseUnit;
    @SerializedName("value")
    @Expose
    public Integer value;
}
