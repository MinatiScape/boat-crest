package com.coveiot.android.respiratoryrate.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateShareData implements Serializable {
    @Nullable
    private Integer max;
    @Nullable
    private Integer min;
    @Nullable
    private String title;

    @Nullable
    public final Integer getMax() {
        return this.max;
    }

    @Nullable
    public final Integer getMin() {
        return this.min;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setMax(@Nullable Integer num) {
        this.max = num;
    }

    public final void setMin(@Nullable Integer num) {
        this.min = num;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }
}
