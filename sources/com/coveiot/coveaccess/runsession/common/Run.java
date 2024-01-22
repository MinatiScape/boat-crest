package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class Run implements Serializable {
    @SerializedName("baseUnit")
    @Expose
    public String baseUnit;
    @SerializedName("logs")
    @Expose
    public List<Log> logs = null;
}
