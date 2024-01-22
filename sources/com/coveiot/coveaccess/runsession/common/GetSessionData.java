package com.coveiot.coveaccess.runsession.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class GetSessionData implements Serializable {
    @SerializedName("avgPace")
    @Expose
    public Integer avgPace;
    @SerializedName("clientRefId")
    @Expose
    public String clientRefId;
    @SerializedName("fitnessSessionId")
    @Expose
    public String fitnessSessionId;
    @SerializedName("geoLocation")
    @Expose
    public GeoLocation geoLocation;
    @SerializedName("hr")
    @Expose
    public Hr hr;
    @SerializedName("maxHr")
    @Expose
    public Integer maxHr;
    @SerializedName("minHr")
    @Expose
    public Integer minHr;
    @SerializedName("otherParams")
    @Expose
    public OtherParams otherParams;
    @SerializedName("pace")
    @Expose
    public Pace pace;
    @SerializedName("run")
    @Expose
    public Run run;
    @SerializedName("sessionEndDate")
    @Expose
    public String sessionEndDate;
    @SerializedName("sessionStartDate")
    @Expose
    public String sessionStartDate;
    @SerializedName("sessionType")
    @Expose
    public String sessionType;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    public Target target;
    @SerializedName("totalCalorie")
    @Expose
    public Integer totalCalorie;
    @SerializedName("totalDistance")
    @Expose
    public Integer totalDistance;
    @SerializedName("totalDuration")
    @Expose
    public Integer totalDuration;
    @SerializedName("totalSteps")
    @Expose
    public Integer totalSteps;
    @SerializedName("walk")
    @Expose
    public Walk walk;
}
