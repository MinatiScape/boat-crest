package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class HRLastMeasuredData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f4853a;
    @Nullable
    public final Long b;

    public HRLastMeasuredData(@Nullable Integer num, @Nullable Long l) {
        this.f4853a = num;
        this.b = l;
    }

    public static /* synthetic */ HRLastMeasuredData copy$default(HRLastMeasuredData hRLastMeasuredData, Integer num, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            num = hRLastMeasuredData.f4853a;
        }
        if ((i & 2) != 0) {
            l = hRLastMeasuredData.b;
        }
        return hRLastMeasuredData.copy(num, l);
    }

    @Nullable
    public final Integer component1() {
        return this.f4853a;
    }

    @Nullable
    public final Long component2() {
        return this.b;
    }

    @NotNull
    public final HRLastMeasuredData copy(@Nullable Integer num, @Nullable Long l) {
        return new HRLastMeasuredData(num, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HRLastMeasuredData) {
            HRLastMeasuredData hRLastMeasuredData = (HRLastMeasuredData) obj;
            return Intrinsics.areEqual(this.f4853a, hRLastMeasuredData.f4853a) && Intrinsics.areEqual(this.b, hRLastMeasuredData.b);
        }
        return false;
    }

    @Nullable
    public final Integer getHr() {
        return this.f4853a;
    }

    @Nullable
    public final Long getTimeStamp() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f4853a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Long l = this.b;
        return hashCode + (l != null ? l.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "HRLastMeasuredData(hr=" + this.f4853a + ", timeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
