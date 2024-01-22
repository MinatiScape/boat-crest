package com.coveiot.coveaccess.contacttracing.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class NearByDeviceReq {
    @SerializedName("fromTime")

    /* renamed from: a  reason: collision with root package name */
    private String f6446a;
    @SerializedName("toTime")
    private String b;
    @SerializedName("scanFrequency")
    private int c;
    @SerializedName("source")
    private SourceBean d;
    @SerializedName("nearbyThings")
    private List<NearbyThingsBean> e;
    @SerializedName("hasNearByBeacons")
    private Boolean f;

    public String getFromTime() {
        return this.f6446a;
    }

    public Boolean getHasNearByBeacons() {
        return this.f;
    }

    public List<NearbyThingsBean> getNearbyThings() {
        return this.e;
    }

    public int getScanFrequency() {
        return this.c;
    }

    public SourceBean getSource() {
        return this.d;
    }

    public String getToTime() {
        return this.b;
    }

    public void setFromTime(String str) {
        this.f6446a = str;
    }

    public void setHasNearByBeacons(Boolean bool) {
        this.f = bool;
    }

    public void setNearbyThings(List<NearbyThingsBean> list) {
        this.e = list;
    }

    public void setScanFrequency(int i) {
        this.c = i;
    }

    public void setSource(SourceBean sourceBean) {
        this.d = sourceBean;
    }

    public void setToTime(String str) {
        this.b = str;
    }
}
