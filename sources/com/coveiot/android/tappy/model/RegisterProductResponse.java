package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class RegisterProductResponse implements Serializable {
    @Nullable
    private String errorMessage;
    @Nullable
    private RegisteredProductInfo registeredProductInfo;

    @Nullable
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    @Nullable
    public final RegisteredProductInfo getRegisteredProductInfo() {
        return this.registeredProductInfo;
    }

    public final void setErrorMessage(@Nullable String str) {
        this.errorMessage = str;
    }

    public final void setRegisteredProductInfo(@Nullable RegisteredProductInfo registeredProductInfo) {
        this.registeredProductInfo = registeredProductInfo;
    }
}
