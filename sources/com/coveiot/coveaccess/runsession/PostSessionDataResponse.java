package com.coveiot.coveaccess.runsession;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.coveiot.coveaccess.runsession.common.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class PostSessionDataResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public Data data;

    public PostSessionDataResponse(int i, Data data) {
        super(i);
        this.data = data;
    }
}
