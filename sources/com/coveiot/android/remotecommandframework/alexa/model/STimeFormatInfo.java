package com.coveiot.android.remotecommandframework.alexa.model;

import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class STimeFormatInfo extends SCommandInfo {
    @SerializedName("clockFormat")
    @NotNull
    private String clockFormat = WatchfaceConstants.H12;

    @NotNull
    public final String getClockFormat() {
        return this.clockFormat;
    }

    public final void setClockFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clockFormat = str;
    }
}
