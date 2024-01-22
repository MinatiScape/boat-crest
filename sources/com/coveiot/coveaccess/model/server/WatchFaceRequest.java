package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WatchFaceRequest {
    @SerializedName("uids")
    @Expose
    private List<String> uids;

    public List<String> getUids() {
        return this.uids;
    }

    public void setUids(List<String> list) {
        this.uids = list;
    }
}
