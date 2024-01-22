package com.coveiot.android.sos.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum SOSConstants {
    ABORTED("ABORTED"),
    CONNECTED("CONNECTED"),
    FAILED("FAILED"),
    BLE_CONNECTION_ERROR("BLE_CONNECTION_ERROR"),
    STARTED("STARTED");
    
    @NotNull
    private String value;

    SOSConstants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
