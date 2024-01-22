package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ExtraInfo implements Serializable {
    @Nullable
    private String extra;

    @Nullable
    public final String getExtra() {
        return this.extra;
    }

    public final void setExtra(@Nullable String str) {
        this.extra = str;
    }
}
