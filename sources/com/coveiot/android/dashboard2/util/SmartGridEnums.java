package com.coveiot.android.dashboard2.util;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public enum SmartGridEnums {
    WATCH_FACE_STUDIO("WF_STUDIO"),
    BOAT_COINS("GMFCN_CNS"),
    FITNESS_CHALLENGE("FIT_CHALNG"),
    QR_TRAY("QR_TRAY"),
    NAVIGATION("TBT_NAV"),
    SMART_ALERTS("SMRT_ALRTS"),
    SOS("SOS"),
    ACTIV_HLTH("ACTIV_HLTH"),
    FITNESS_BUDDIES("FIT_BUDDIES"),
    FITNESS_REPORT("FIT_REPORT");
    
    @NotNull
    private String value;

    SmartGridEnums(String str) {
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
