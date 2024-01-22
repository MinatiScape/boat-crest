package com.coveiot.coveaccess.runsession;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.coveiot.coveaccess.runsession.common.GetSessionData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetSessionDataResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public GetSessionData data;

    public GetSessionDataResponse(int i, GetSessionData getSessionData) {
        super(i);
        this.data = getSessionData;
    }
}
