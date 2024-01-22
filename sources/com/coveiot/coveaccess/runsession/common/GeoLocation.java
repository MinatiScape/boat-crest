package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GeoLocation implements Serializable {
    @SerializedName("logs")
    @Expose
    public List<LocationLog> logs = null;
}
