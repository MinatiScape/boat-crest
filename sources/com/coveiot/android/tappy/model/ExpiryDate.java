package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ExpiryDate implements Serializable {
    @Nullable
    private String month;
    @Nullable
    private String year;

    @Nullable
    public final String getMonth() {
        return this.month;
    }

    @Nullable
    public final String getYear() {
        return this.year;
    }

    public final void setMonth(@Nullable String str) {
        this.month = str;
    }

    public final void setYear(@Nullable String str) {
        this.year = str;
    }
}
