package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DiagnosticTestHistoryModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4851a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;

    public DiagnosticTestHistoryModel(@NotNull String testDate, @NotNull String testTime, @NotNull String testDownloadUrl) {
        Intrinsics.checkNotNullParameter(testDate, "testDate");
        Intrinsics.checkNotNullParameter(testTime, "testTime");
        Intrinsics.checkNotNullParameter(testDownloadUrl, "testDownloadUrl");
        this.f4851a = testDate;
        this.b = testTime;
        this.c = testDownloadUrl;
    }

    public static /* synthetic */ DiagnosticTestHistoryModel copy$default(DiagnosticTestHistoryModel diagnosticTestHistoryModel, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = diagnosticTestHistoryModel.f4851a;
        }
        if ((i & 2) != 0) {
            str2 = diagnosticTestHistoryModel.b;
        }
        if ((i & 4) != 0) {
            str3 = diagnosticTestHistoryModel.c;
        }
        return diagnosticTestHistoryModel.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.f4851a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final DiagnosticTestHistoryModel copy(@NotNull String testDate, @NotNull String testTime, @NotNull String testDownloadUrl) {
        Intrinsics.checkNotNullParameter(testDate, "testDate");
        Intrinsics.checkNotNullParameter(testTime, "testTime");
        Intrinsics.checkNotNullParameter(testDownloadUrl, "testDownloadUrl");
        return new DiagnosticTestHistoryModel(testDate, testTime, testDownloadUrl);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiagnosticTestHistoryModel) {
            DiagnosticTestHistoryModel diagnosticTestHistoryModel = (DiagnosticTestHistoryModel) obj;
            return Intrinsics.areEqual(this.f4851a, diagnosticTestHistoryModel.f4851a) && Intrinsics.areEqual(this.b, diagnosticTestHistoryModel.b) && Intrinsics.areEqual(this.c, diagnosticTestHistoryModel.c);
        }
        return false;
    }

    @NotNull
    public final String getTestDate() {
        return this.f4851a;
    }

    @NotNull
    public final String getTestDownloadUrl() {
        return this.c;
    }

    @NotNull
    public final String getTestTime() {
        return this.b;
    }

    public int hashCode() {
        return (((this.f4851a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    @NotNull
    public String toString() {
        return "DiagnosticTestHistoryModel(testDate=" + this.f4851a + ", testTime=" + this.b + ", testDownloadUrl=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
