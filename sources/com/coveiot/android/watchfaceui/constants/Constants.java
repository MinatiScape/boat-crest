package com.coveiot.android.watchfaceui.constants;

import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public enum Constants {
    INTENT_EXTRA_URL(WorkoutConstants.INTENT_EXTRA_URL),
    INTENT_EXTRA_TITLE(WorkoutConstants.INTENT_EXTRA_TITLE),
    INTENT_EXTRA_FROM_DASHBOARD("FROM_DASHBOARD"),
    SCREEN_TYPE("screen_type");
    
    @NotNull
    private String value;

    Constants(String str) {
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
