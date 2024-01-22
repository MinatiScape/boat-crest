package com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class Response {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)

    /* renamed from: a  reason: collision with root package name */
    private List<Items> f5992a;
    @SerializedName("total_items")
    private Integer b;
    @SerializedName("total_pages")
    private Integer c;

    public List<Items> getItems() {
        return this.f5992a;
    }

    public Integer getTotalItems() {
        return this.b;
    }

    public Integer getTotalPages() {
        return this.c;
    }

    public void setItems(List<Items> list) {
        this.f5992a = list;
    }

    public void setTotalItems(Integer num) {
        this.b = num;
    }

    public void setTotalPages(Integer num) {
        this.c = num;
    }
}
