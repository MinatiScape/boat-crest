package com.coveiot.android.respiratoryrate.model;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateListBean {
    @Nullable
    private String date;
    @Nullable
    private Integer max;
    @Nullable
    private Integer min;

    @Nullable
    public final String getDate() {
        return this.date;
    }

    @Nullable
    public final Integer getMax() {
        return this.max;
    }

    @Nullable
    public final Integer getMin() {
        return this.min;
    }

    public final void setDate(@Nullable String str) {
        this.date = str;
    }

    public final void setMax(@Nullable Integer num) {
        this.max = num;
    }

    public final void setMin(@Nullable Integer num) {
        this.min = num;
    }
}
