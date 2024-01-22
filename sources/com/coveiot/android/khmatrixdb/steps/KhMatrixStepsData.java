package com.coveiot.android.khmatrixdb.steps;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.khmatrixdb.converter.TimeConverter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes3.dex */
public final class KhMatrixStepsData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4631a;
    public long b;
    public int c;
    public float d;
    public float e;
    @Nullable
    public String f;
    @ColumnInfo(name = "isProcessed")
    public boolean g;

    public KhMatrixStepsData(@NotNull String mMacAddress, long j, int i, float f, float f2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4631a = mMacAddress;
        this.b = j;
        this.c = i;
        this.d = f;
        this.e = f2;
        this.f = str;
        this.f = TimeConverter.fromDate(new Date(this.b));
    }

    public static /* synthetic */ KhMatrixStepsData copy$default(KhMatrixStepsData khMatrixStepsData, String str, long j, int i, float f, float f2, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = khMatrixStepsData.f4631a;
        }
        if ((i2 & 2) != 0) {
            j = khMatrixStepsData.b;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = khMatrixStepsData.c;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            f = khMatrixStepsData.d;
        }
        float f3 = f;
        if ((i2 & 16) != 0) {
            f2 = khMatrixStepsData.e;
        }
        float f4 = f2;
        if ((i2 & 32) != 0) {
            str2 = khMatrixStepsData.f;
        }
        return khMatrixStepsData.copy(str, j2, i3, f3, f4, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4631a;
    }

    public final long component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final float component4() {
        return this.d;
    }

    public final float component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final KhMatrixStepsData copy(@NotNull String mMacAddress, long j, int i, float f, float f2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixStepsData(mMacAddress, j, i, f, f2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixStepsData) {
            KhMatrixStepsData khMatrixStepsData = (KhMatrixStepsData) obj;
            return Intrinsics.areEqual(this.f4631a, khMatrixStepsData.f4631a) && this.b == khMatrixStepsData.b && this.c == khMatrixStepsData.c && Float.compare(this.d, khMatrixStepsData.d) == 0 && Float.compare(this.e, khMatrixStepsData.e) == 0 && Intrinsics.areEqual(this.f, khMatrixStepsData.f);
        }
        return false;
    }

    public final float getCalories() {
        return this.e;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.f;
    }

    public final float getDistance() {
        return this.d;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.f4631a;
    }

    public final long getMTime() {
        return this.b;
    }

    public final int getSteps() {
        return this.c;
    }

    public int hashCode() {
        int hashCode = ((((((((this.f4631a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Float.hashCode(this.d)) * 31) + Float.hashCode(this.e)) * 31;
        String str = this.f;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.g;
    }

    public final void setCalories(float f) {
        this.e = f;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.f = str;
    }

    public final void setDistance(float f) {
        this.d = f;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4631a = str;
    }

    public final void setMTime(long j) {
        this.b = j;
    }

    public final void setProcessed(boolean z) {
        this.g = z;
    }

    public final void setSteps(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "KhMatrixStepsData(mMacAddress=" + this.f4631a + ", mTime=" + this.b + ", steps=" + this.c + ", distance=" + this.d + ", calories=" + this.e + ", convertedDate=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixStepsData(String str, long j, int i, float f, float f2, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? 0.0f : f, (i2 & 16) == 0 ? f2 : 0.0f, (i2 & 32) != 0 ? null : str2);
    }
}
