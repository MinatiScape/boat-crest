package com.coveiot.android.khmatrixdb.sleep;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.khmatrixdb.converter.TimeConverter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mStartTime", "mEndTime", "mMacAddress"})
/* loaded from: classes3.dex */
public final class KhMatrixSleepDetailData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4625a;
    public long b;
    public long c;
    public int d;
    @Nullable
    public String e;
    @Nullable
    public String f;
    @Nullable
    public String g;
    @ColumnInfo(name = "isProcessed")
    public boolean h;

    public KhMatrixSleepDetailData(@NotNull String mMacAddress, long j, long j2, int i, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4625a = mMacAddress;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.e = TimeConverter.fromDateToYYYYMMHH(new Date(this.b));
        this.f = TimeConverter.fromDate(new Date(this.b));
        this.g = TimeConverter.fromDate(new Date(this.c));
    }

    @NotNull
    public final String component1() {
        return this.f4625a;
    }

    public final long component2() {
        return this.b;
    }

    public final long component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final String component7() {
        return this.g;
    }

    @NotNull
    public final KhMatrixSleepDetailData copy(@NotNull String mMacAddress, long j, long j2, int i, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixSleepDetailData(mMacAddress, j, j2, i, str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixSleepDetailData) {
            KhMatrixSleepDetailData khMatrixSleepDetailData = (KhMatrixSleepDetailData) obj;
            return Intrinsics.areEqual(this.f4625a, khMatrixSleepDetailData.f4625a) && this.b == khMatrixSleepDetailData.b && this.c == khMatrixSleepDetailData.c && this.d == khMatrixSleepDetailData.d && Intrinsics.areEqual(this.e, khMatrixSleepDetailData.e) && Intrinsics.areEqual(this.f, khMatrixSleepDetailData.f) && Intrinsics.areEqual(this.g, khMatrixSleepDetailData.g);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.e;
    }

    @Nullable
    public final String getConvertedEndTime() {
        return this.g;
    }

    @Nullable
    public final String getConvertedStartTime() {
        return this.f;
    }

    public final long getMEndTime() {
        return this.c;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.f4625a;
    }

    public final long getMStartTime() {
        return this.b;
    }

    public final int getStatus() {
        return this.d;
    }

    public int hashCode() {
        int hashCode = ((((((this.f4625a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Long.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31;
        String str = this.e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.g;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final boolean isProcessed() {
        return this.h;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.e = str;
    }

    public final void setConvertedEndTime(@Nullable String str) {
        this.g = str;
    }

    public final void setConvertedStartTime(@Nullable String str) {
        this.f = str;
    }

    public final void setMEndTime(long j) {
        this.c = j;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4625a = str;
    }

    public final void setMStartTime(long j) {
        this.b = j;
    }

    public final void setProcessed(boolean z) {
        this.h = z;
    }

    public final void setStatus(int i) {
        this.d = i;
    }

    @NotNull
    public String toString() {
        return "KhMatrixSleepDetailData(mMacAddress=" + this.f4625a + ", mStartTime=" + this.b + ", mEndTime=" + this.c + ", status=" + this.d + ", convertedDate=" + this.e + ", convertedStartTime=" + this.f + ", convertedEndTime=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixSleepDetailData(String str, long j, long j2, int i, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) == 0 ? j2 : 0L, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? null : str3, (i2 & 64) == 0 ? str4 : null);
    }
}
