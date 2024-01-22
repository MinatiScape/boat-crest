package com.coveiot.android.khmatrixdb.spo2;

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
public final class KhMatrixSpO2 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4628a;
    public long b;
    public int c;
    @Nullable
    public String d;
    @ColumnInfo(name = "isProcessed")
    public boolean e;

    public KhMatrixSpO2(@NotNull String mMacAddress, long j, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4628a = mMacAddress;
        this.b = j;
        this.c = i;
        this.d = str;
        this.d = TimeConverter.fromDate(new Date(this.b));
    }

    public static /* synthetic */ KhMatrixSpO2 copy$default(KhMatrixSpO2 khMatrixSpO2, String str, long j, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = khMatrixSpO2.f4628a;
        }
        if ((i2 & 2) != 0) {
            j = khMatrixSpO2.b;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = khMatrixSpO2.c;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str2 = khMatrixSpO2.d;
        }
        return khMatrixSpO2.copy(str, j2, i3, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4628a;
    }

    public final long component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final KhMatrixSpO2 copy(@NotNull String mMacAddress, long j, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixSpO2(mMacAddress, j, i, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixSpO2) {
            KhMatrixSpO2 khMatrixSpO2 = (KhMatrixSpO2) obj;
            return Intrinsics.areEqual(this.f4628a, khMatrixSpO2.f4628a) && this.b == khMatrixSpO2.b && this.c == khMatrixSpO2.c && Intrinsics.areEqual(this.d, khMatrixSpO2.d);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.d;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.f4628a;
    }

    public final int getMSpO2() {
        return this.c;
    }

    public final long getMTime() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = ((((this.f4628a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31;
        String str = this.d;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.e;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.d = str;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4628a = str;
    }

    public final void setMSpO2(int i) {
        this.c = i;
    }

    public final void setMTime(long j) {
        this.b = j;
    }

    public final void setProcessed(boolean z) {
        this.e = z;
    }

    @NotNull
    public String toString() {
        return "KhMatrixSpO2(mMacAddress=" + this.f4628a + ", mTime=" + this.b + ", mSpO2=" + this.c + ", convertedDate=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixSpO2(String str, long j, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2);
    }
}
