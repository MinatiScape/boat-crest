package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class APDULine implements Serializable {
    @Nullable
    private String aPDU;
    @Nullable
    private String aPDUID;

    @Nullable
    public final String getAPDU() {
        return this.aPDU;
    }

    @Nullable
    public final String getAPDUID() {
        return this.aPDUID;
    }

    public final void setAPDU(@Nullable String str) {
        this.aPDU = str;
    }

    public final void setAPDUID(@Nullable String str) {
        this.aPDUID = str;
    }
}
