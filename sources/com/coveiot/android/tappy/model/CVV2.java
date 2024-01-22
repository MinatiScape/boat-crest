package com.coveiot.android.tappy.model;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class CVV2 implements Serializable {
    @NotNull
    private String cvv2;

    public CVV2(@NotNull String cvv2) {
        Intrinsics.checkNotNullParameter(cvv2, "cvv2");
        this.cvv2 = cvv2;
    }

    @NotNull
    public final String getCvv2() {
        return this.cvv2;
    }

    public final void setCvv2(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cvv2 = str;
    }
}
