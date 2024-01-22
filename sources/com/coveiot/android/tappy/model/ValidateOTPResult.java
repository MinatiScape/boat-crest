package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ValidateOTPResult implements Serializable {
    @Nullable
    private String result;

    @Nullable
    public final String getResult() {
        return this.result;
    }

    public final void setResult(@Nullable String str) {
        this.result = str;
    }
}
