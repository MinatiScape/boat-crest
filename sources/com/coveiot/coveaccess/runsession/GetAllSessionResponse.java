package com.coveiot.coveaccess.runsession;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.coveiot.coveaccess.runsession.common.GetAllSessionData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetAllSessionResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public GetAllSessionData data;

    public GetAllSessionResponse(int i, GetAllSessionData getAllSessionData) {
        super(i);
        this.data = getAllSessionData;
    }
}
