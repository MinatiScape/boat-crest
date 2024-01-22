package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class StepUpRequest implements Serializable {
    @Nullable
    private String id;
    @Nullable
    private String method;
    @Nullable
    private String methodDescription;
    @Nullable
    private String methodValue;

    @Nullable
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final String getMethodDescription() {
        return this.methodDescription;
    }

    @Nullable
    public final String getMethodValue() {
        return this.methodValue;
    }

    public final void setId(@Nullable String str) {
        this.id = str;
    }

    public final void setMethod(@Nullable String str) {
        this.method = str;
    }

    public final void setMethodDescription(@Nullable String str) {
        this.methodDescription = str;
    }

    public final void setMethodValue(@Nullable String str) {
        this.methodValue = str;
    }
}
