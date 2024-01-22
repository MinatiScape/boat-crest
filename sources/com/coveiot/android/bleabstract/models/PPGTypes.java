package com.coveiot.android.bleabstract.models;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public enum PPGTypes {
    PPG("PPG"),
    BLOOD_OXYGEN_PPG("BLOOD_OXYGEN_PPG");
    
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3439a;

    PPGTypes(String str) {
        this.f3439a = str;
    }

    @NotNull
    public final String getValue() {
        return this.f3439a;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f3439a = str;
    }
}
