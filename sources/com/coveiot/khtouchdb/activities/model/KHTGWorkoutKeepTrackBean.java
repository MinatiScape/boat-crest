package com.coveiot.khtouchdb.activities.model;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHTGWorkoutKeepTrackBean {

    /* renamed from: a  reason: collision with root package name */
    public int f7176a;
    public boolean b;
    @Nullable
    public String c;
    @Nullable
    public List<KHTGWorkoutKeepTrackItemBean> d;

    @Nullable
    public final String getDate() {
        return this.c;
    }

    public final boolean getHaveMoreData() {
        return this.b;
    }

    @Nullable
    public final List<KHTGWorkoutKeepTrackItemBean> getItems() {
        return this.d;
    }

    public final int getOffset() {
        return this.f7176a;
    }

    public final void setDate(@Nullable String str) {
        this.c = str;
    }

    public final void setHaveMoreData(boolean z) {
        this.b = z;
    }

    public final void setItems(@Nullable List<KHTGWorkoutKeepTrackItemBean> list) {
        this.d = list;
    }

    public final void setOffset(int i) {
        this.f7176a = i;
    }
}
