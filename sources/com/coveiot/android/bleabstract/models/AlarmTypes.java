package com.coveiot.android.bleabstract.models;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public enum AlarmTypes {
    ALARM("Alarm"),
    MEDICINE("Medicine"),
    DRINK("Drink Water"),
    FOOD("Food");
    
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3397a;

    AlarmTypes(String str) {
        this.f3397a = str;
    }

    @NotNull
    public final String getValue() {
        return this.f3397a;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f3397a = str;
    }
}
