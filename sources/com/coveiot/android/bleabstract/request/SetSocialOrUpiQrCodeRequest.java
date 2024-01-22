package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetSocialOrUpiQrCodeRequest extends BleBaseRequest {
    public final int f;
    @NotNull
    public final String g;

    public SetSocialOrUpiQrCodeRequest(int i, @NotNull String qrCodeData) {
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        this.f = i;
        this.g = qrCodeData;
    }

    public final int getAppType() {
        return this.f;
    }

    @NotNull
    public final String getQrCodeData() {
        return this.g;
    }
}
