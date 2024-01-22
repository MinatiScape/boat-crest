package com.coveiot.khtouchdb.activities.model;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHTGWorkoutRealTimeDataBean {

    /* renamed from: a  reason: collision with root package name */
    public int f7179a;
    public boolean b;
    @Nullable
    public String c;
    @Nullable
    public List<KHTGWorkoutRealTimeDataItemBean> d;

    @Nullable
    public final String getDate() {
        return this.c;
    }

    public final boolean getHaveMoreData() {
        return this.b;
    }

    @Nullable
    public final List<KHTGWorkoutRealTimeDataItemBean> getItems() {
        return this.d;
    }

    public final int getOffset() {
        return this.f7179a;
    }

    public final void setDate(@Nullable String str) {
        this.c = str;
    }

    public final void setHaveMoreData(boolean z) {
        this.b = z;
    }

    public final void setItems(@Nullable List<KHTGWorkoutRealTimeDataItemBean> list) {
        this.d = list;
    }

    public final void setOffset(int i) {
        this.f7179a = i;
    }
}
