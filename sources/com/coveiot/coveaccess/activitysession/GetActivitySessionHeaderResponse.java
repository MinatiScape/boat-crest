package com.coveiot.coveaccess.activitysession;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetActivitySessionHeaderResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public fitnessActivitySessions requestData;

    public GetActivitySessionHeaderResponse(int i, fitnessActivitySessions fitnessactivitysessions) {
        super(i);
        this.requestData = fitnessactivitysessions;
    }
}
