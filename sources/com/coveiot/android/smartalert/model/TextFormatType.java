package com.coveiot.android.smartalert.model;

import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum TextFormatType {
    RESERVED("RESERVED"),
    CENTER("CENTER"),
    CENTER_VERTICAL("CENTER_VERTICAL"),
    CENTER_HORIZONTAL("CENTER_HORIZONTAL"),
    LEFT(FitnessChallengeConstants.LEFT),
    RIGHT("RIGHT"),
    TOP("TOP"),
    BOTTOM("BOTTOM");
    
    @NotNull
    private String type;

    TextFormatType(String str) {
        this.type = str;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public final void setType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }
}
