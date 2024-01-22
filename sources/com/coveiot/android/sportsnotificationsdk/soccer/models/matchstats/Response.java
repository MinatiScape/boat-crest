package com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Response {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)

    /* renamed from: a  reason: collision with root package name */
    private Items f5995a;
    @SerializedName("total_items")
    private Integer b;
    @SerializedName("total_pages")
    private Integer c;

    public Items getItems() {
        return this.f5995a;
    }

    public Integer getTotalItems() {
        return this.b;
    }

    public Integer getTotalPages() {
        return this.c;
    }

    public void setItems(Items items) {
        this.f5995a = items;
    }

    public void setTotalItems(Integer num) {
        this.b = num;
    }

    public void setTotalPages(Integer num) {
        this.c = num;
    }
}
