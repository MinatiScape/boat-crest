package com.coveiot.android.femalewellness.wellnesscalendar.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class PhaseAndDateModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4410a;
    @NotNull
    public String b;

    public PhaseAndDateModel(@NotNull String date, @NotNull String phase) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(phase, "phase");
        this.f4410a = date;
        this.b = phase;
    }

    public static /* synthetic */ PhaseAndDateModel copy$default(PhaseAndDateModel phaseAndDateModel, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = phaseAndDateModel.f4410a;
        }
        if ((i & 2) != 0) {
            str2 = phaseAndDateModel.b;
        }
        return phaseAndDateModel.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4410a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final PhaseAndDateModel copy(@NotNull String date, @NotNull String phase) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(phase, "phase");
        return new PhaseAndDateModel(date, phase);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PhaseAndDateModel) {
            PhaseAndDateModel phaseAndDateModel = (PhaseAndDateModel) obj;
            return Intrinsics.areEqual(this.f4410a, phaseAndDateModel.f4410a) && Intrinsics.areEqual(this.b, phaseAndDateModel.b);
        }
        return false;
    }

    @NotNull
    public final String getDate() {
        return this.f4410a;
    }

    @NotNull
    public final String getPhase() {
        return this.b;
    }

    public int hashCode() {
        return (this.f4410a.hashCode() * 31) + this.b.hashCode();
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4410a = str;
    }

    public final void setPhase(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "PhaseAndDateModel(date=" + this.f4410a + ", phase=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
