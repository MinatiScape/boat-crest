package com.coveiot.coveaccess.runsession.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Item {
    @SerializedName("avgPace")
    @Expose
    public Integer avgPace;
    @SerializedName("clientRefId")
    @Expose
    public String clientRefId;
    @SerializedName("fitnessSessionId")
    @Expose
    public String fitnessSessionId;
    @SerializedName("sessionEndDate")
    @Expose
    public String sessionEndDate;
    @SerializedName("sessionStartDate")
    @Expose
    public String sessionStartDate;
    @SerializedName("sessionType")
    @Expose
    public String sessionType;
    @SerializedName("totalDistance")
    @Expose
    public Integer totalDistance;
    @SerializedName("totalDuration")
    @Expose
    public Integer totalDuration;
}
