package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SuspendPaymentInstrumentTokenResponse implements Serializable {
    @Nullable
    private RegisteredProductInfo registeredProductInfo;

    @Nullable
    public final RegisteredProductInfo getRegisteredProductInfo() {
        return this.registeredProductInfo;
    }

    public final void setRegisteredProductInfo(@Nullable RegisteredProductInfo registeredProductInfo) {
        this.registeredProductInfo = registeredProductInfo;
    }
}
