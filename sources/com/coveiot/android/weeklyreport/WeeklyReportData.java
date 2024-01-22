package com.coveiot.android.weeklyreport;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeeklyReportData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f6239a;
    @NotNull
    public final String b;

    public WeeklyReportData(@NotNull String screenType, @NotNull String emailId) {
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        this.f6239a = screenType;
        this.b = emailId;
    }

    public static /* synthetic */ WeeklyReportData copy$default(WeeklyReportData weeklyReportData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = weeklyReportData.f6239a;
        }
        if ((i & 2) != 0) {
            str2 = weeklyReportData.b;
        }
        return weeklyReportData.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f6239a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final WeeklyReportData copy(@NotNull String screenType, @NotNull String emailId) {
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        return new WeeklyReportData(screenType, emailId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeeklyReportData) {
            WeeklyReportData weeklyReportData = (WeeklyReportData) obj;
            return Intrinsics.areEqual(this.f6239a, weeklyReportData.f6239a) && Intrinsics.areEqual(this.b, weeklyReportData.b);
        }
        return false;
    }

    @NotNull
    public final String getEmailId() {
        return this.b;
    }

    @NotNull
    public final String getScreenType() {
        return this.f6239a;
    }

    public int hashCode() {
        return (this.f6239a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "WeeklyReportData(screenType=" + this.f6239a + ", emailId=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
