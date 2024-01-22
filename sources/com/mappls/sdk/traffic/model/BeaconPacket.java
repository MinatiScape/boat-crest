package com.mappls.sdk.traffic.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class BeaconPacket {
    @SerializedName("probes")
    @Expose
    private List<ProbeWrapper> probes = null;

    public List<ProbeWrapper> getProbes() {
        return this.probes;
    }

    public void setProbes(List<ProbeWrapper> list) {
        this.probes = list;
    }
}
