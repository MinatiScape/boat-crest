package com.coveiot.coveaccess.activitysession;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class PostActivitySessionHeaderResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public PostActivitySessionDataRequest requestData;

    public PostActivitySessionHeaderResponse(int i, PostActivitySessionDataRequest postActivitySessionDataRequest) {
        super(i);
        this.requestData = postActivitySessionDataRequest;
    }
}
