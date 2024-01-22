package com.coveiot.android.theme.utils;

import com.google.android.gms.common.Scopes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum ReviewAndRateUsConstants {
    CREATE_WATCHFACE("create_watchface"),
    WATCHFACE("watchface"),
    CREATE_CUSTOM_WATCHFACE("create_custom_watchface"),
    PROFILE(Scopes.PROFILE);
    
    @NotNull
    private String value;

    ReviewAndRateUsConstants(String str) {
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
