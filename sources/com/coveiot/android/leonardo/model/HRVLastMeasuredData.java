package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class HRVLastMeasuredData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Double f4854a;
    @Nullable
    public final Long b;

    public HRVLastMeasuredData(@Nullable Double d, @Nullable Long l) {
        this.f4854a = d;
        this.b = l;
    }

    public static /* synthetic */ HRVLastMeasuredData copy$default(HRVLastMeasuredData hRVLastMeasuredData, Double d, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            d = hRVLastMeasuredData.f4854a;
        }
        if ((i & 2) != 0) {
            l = hRVLastMeasuredData.b;
        }
        return hRVLastMeasuredData.copy(d, l);
    }

    @Nullable
    public final Double component1() {
        return this.f4854a;
    }

    @Nullable
    public final Long component2() {
        return this.b;
    }

    @NotNull
    public final HRVLastMeasuredData copy(@Nullable Double d, @Nullable Long l) {
        return new HRVLastMeasuredData(d, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HRVLastMeasuredData) {
            HRVLastMeasuredData hRVLastMeasuredData = (HRVLastMeasuredData) obj;
            return Intrinsics.areEqual((Object) this.f4854a, (Object) hRVLastMeasuredData.f4854a) && Intrinsics.areEqual(this.b, hRVLastMeasuredData.b);
        }
        return false;
    }

    @Nullable
    public final Double getHrv() {
        return this.f4854a;
    }

    @Nullable
    public final Long getTimeStamp() {
        return this.b;
    }

    public int hashCode() {
        Double d = this.f4854a;
        int hashCode = (d == null ? 0 : d.hashCode()) * 31;
        Long l = this.b;
        return hashCode + (l != null ? l.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "HRVLastMeasuredData(hrv=" + this.f4854a + ", timeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
