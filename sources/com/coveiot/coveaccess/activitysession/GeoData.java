package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GeoData {
    @SerializedName("bbox")

    /* renamed from: a  reason: collision with root package name */
    private List<Double> f6388a;

    public List<Double> getBbox() {
        return this.f6388a;
    }

    public void setBbox(List<Double> list) {
        this.f6388a = list;
    }
}
