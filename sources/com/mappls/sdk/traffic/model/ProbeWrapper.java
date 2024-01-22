package com.mappls.sdk.traffic.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import com.mappls.sdk.traffic.db.ProbeLocation;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class ProbeWrapper {
    @SerializedName(d.g)
    @Expose
    private String deviceId;
    @SerializedName("gps")
    @Expose
    private List<ProbeLocation> gps = null;

    public String getDeviceId() {
        return this.deviceId;
    }

    public List<ProbeLocation> getGps() {
        return this.gps;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setGps(List<ProbeLocation> list) {
        this.gps = list;
    }
}
