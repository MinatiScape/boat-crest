package com.coveiot.coveaccess.onekactivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class PhysicalActivityData {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<PhysicalActivityItem> f6673a = null;

    public List<PhysicalActivityItem> getItems() {
        return this.f6673a;
    }

    public void setItems(List<PhysicalActivityItem> list) {
        this.f6673a = list;
    }
}
