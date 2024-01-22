package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class Log implements Serializable {
    @SerializedName("codedValues")
    @Expose
    public List<Integer> codedValues = null;
    @SerializedName("endTime")
    @Expose
    public String endTime;
    @SerializedName("slotId")
    @Expose
    public String slotId;
    @SerializedName("startTime")
    @Expose
    public String startTime;
}
