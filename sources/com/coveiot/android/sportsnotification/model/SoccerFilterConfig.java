package com.coveiot.android.sportsnotification.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class SoccerFilterConfig {
    @SerializedName("filters")

    /* renamed from: a  reason: collision with root package name */
    private List<Filters> f5876a;

    public List<Filters> getFilters() {
        return this.f5876a;
    }

    public void setFilters(List<Filters> list) {
        this.f5876a = list;
    }
}
