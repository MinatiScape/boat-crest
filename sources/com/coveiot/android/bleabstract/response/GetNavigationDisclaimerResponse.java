package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetNavigationDisclaimerResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3615a = "";
    public boolean b;
    @NotNull
    public Calendar c;

    public GetNavigationDisclaimerResponse() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.c = calendar;
    }

    @NotNull
    public final Calendar getCalendar() {
        return this.c;
    }

    @NotNull
    public final String getVersionText() {
        return this.f3615a;
    }

    public final boolean isAccepted() {
        return this.b;
    }

    public final void setAccepted(boolean z) {
        this.b = z;
    }

    public final void setCalendar(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<set-?>");
        this.c = calendar;
    }

    public final void setVersionText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f3615a = str;
    }

    @NotNull
    public String toString() {
        return "(versionText=" + this.f3615a + ", isAccepted=" + this.b + ", calendar=" + this.c.getTime() + HexStringBuilder.COMMENT_END_CHAR;
    }
}
