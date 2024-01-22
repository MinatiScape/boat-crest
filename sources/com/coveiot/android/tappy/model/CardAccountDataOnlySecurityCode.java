package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CardAccountDataOnlySecurityCode implements Serializable {
    @Nullable
    private String securityCode;

    @Nullable
    public final String getSecurityCode() {
        return this.securityCode;
    }

    public final void setSecurityCode(@Nullable String str) {
        this.securityCode = str;
    }
}
