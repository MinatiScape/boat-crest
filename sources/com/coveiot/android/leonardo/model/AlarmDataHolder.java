package com.coveiot.android.leonardo.model;

import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AlarmDataHolder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public AlarmInfo f4850a;
    public boolean b;

    public AlarmDataHolder(@NotNull AlarmInfo alarmInfo, boolean z) {
        Intrinsics.checkNotNullParameter(alarmInfo, "alarmInfo");
        this.f4850a = alarmInfo;
        this.b = z;
    }

    public static /* synthetic */ AlarmDataHolder copy$default(AlarmDataHolder alarmDataHolder, AlarmInfo alarmInfo, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alarmInfo = alarmDataHolder.f4850a;
        }
        if ((i & 2) != 0) {
            z = alarmDataHolder.b;
        }
        return alarmDataHolder.copy(alarmInfo, z);
    }

    @NotNull
    public final AlarmInfo component1() {
        return this.f4850a;
    }

    public final boolean component2() {
        return this.b;
    }

    @NotNull
    public final AlarmDataHolder copy(@NotNull AlarmInfo alarmInfo, boolean z) {
        Intrinsics.checkNotNullParameter(alarmInfo, "alarmInfo");
        return new AlarmDataHolder(alarmInfo, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlarmDataHolder) {
            AlarmDataHolder alarmDataHolder = (AlarmDataHolder) obj;
            return Intrinsics.areEqual(this.f4850a, alarmDataHolder.f4850a) && this.b == alarmDataHolder.b;
        }
        return false;
    }

    @NotNull
    public final AlarmInfo getAlarmInfo() {
        return this.f4850a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.f4850a.hashCode() * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final boolean isAlreadySaved() {
        return this.b;
    }

    public final void setAlarmInfo(@NotNull AlarmInfo alarmInfo) {
        Intrinsics.checkNotNullParameter(alarmInfo, "<set-?>");
        this.f4850a = alarmInfo;
    }

    public final void setAlreadySaved(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "AlarmDataHolder(alarmInfo=" + this.f4850a + ", isAlreadySaved=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
