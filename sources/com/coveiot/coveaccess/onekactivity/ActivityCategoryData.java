package com.coveiot.coveaccess.onekactivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivityCategoryData {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<CategoryItem> f6670a = null;

    public List<CategoryItem> getItems() {
        return this.f6670a;
    }

    public void setItems(List<CategoryItem> list) {
        this.f6670a = list;
    }
}
