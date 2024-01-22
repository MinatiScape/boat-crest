package com.coveiot.khsmadb.sleep;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.khsmadb.Utils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes8.dex */
public final class KhBleSleep {

    /* renamed from: a  reason: collision with root package name */
    public int f7151a;
    public int b;
    public int c;
    public int d;
    @NotNull
    public String e;
    @Nullable
    public String f;
    @ColumnInfo(name = "isProcessed")
    public boolean g;
    @ColumnInfo(name = "Timestamp")
    public String timeStamp;

    public KhBleSleep(int i, int i2, int i3, int i4, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f7151a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = mMacAddress;
        this.f = str;
        Utils utils = Utils.INSTANCE;
        this.f = utils.convertSDKTimeToDate(i);
        String convertSDKTime = utils.convertSDKTime(this.f7151a);
        Intrinsics.checkNotNull(convertSDKTime);
        setTimeStamp(convertSDKTime);
    }

    public static /* synthetic */ KhBleSleep copy$default(KhBleSleep khBleSleep, int i, int i2, int i3, int i4, String str, String str2, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = khBleSleep.f7151a;
        }
        if ((i5 & 2) != 0) {
            i2 = khBleSleep.b;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            i3 = khBleSleep.c;
        }
        int i7 = i3;
        if ((i5 & 8) != 0) {
            i4 = khBleSleep.d;
        }
        int i8 = i4;
        if ((i5 & 16) != 0) {
            str = khBleSleep.e;
        }
        String str3 = str;
        if ((i5 & 32) != 0) {
            str2 = khBleSleep.f;
        }
        return khBleSleep.copy(i, i6, i7, i8, str3, str2);
    }

    public final int component1() {
        return this.f7151a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final KhBleSleep copy(int i, int i2, int i3, int i4, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhBleSleep(i, i2, i3, i4, mMacAddress, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhBleSleep) {
            KhBleSleep khBleSleep = (KhBleSleep) obj;
            return this.f7151a == khBleSleep.f7151a && this.b == khBleSleep.b && this.c == khBleSleep.c && this.d == khBleSleep.d && Intrinsics.areEqual(this.e, khBleSleep.e) && Intrinsics.areEqual(this.f, khBleSleep.f);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.f;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.e;
    }

    public final int getMMode() {
        return this.b;
    }

    public final int getMSoft() {
        return this.c;
    }

    public final int getMStrong() {
        return this.d;
    }

    public final int getMTime() {
        return this.f7151a;
    }

    @NotNull
    public final String getTimeStamp() {
        String str = this.timeStamp;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("timeStamp");
        return null;
    }

    public int hashCode() {
        int hashCode = ((((((((Integer.hashCode(this.f7151a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + this.e.hashCode()) * 31;
        String str = this.f;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.g;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.f = str;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setMMode(int i) {
        this.b = i;
    }

    public final void setMSoft(int i) {
        this.c = i;
    }

    public final void setMStrong(int i) {
        this.d = i;
    }

    public final void setMTime(int i) {
        this.f7151a = i;
    }

    public final void setProcessed(boolean z) {
        this.g = z;
    }

    public final void setTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timeStamp = str;
    }

    @NotNull
    public String toString() {
        return "KhBleSleep(mTime=" + this.f7151a + ", mMode=" + this.b + ", mSoft=" + this.c + ", mStrong=" + this.d + ", mMacAddress='" + this.e + "', convertedDate=" + this.f + ", isProcessed=" + this.g + ", timeStamp='" + getTimeStamp() + "')";
    }

    public /* synthetic */ KhBleSleep(int i, int i2, int i3, int i4, String str, String str2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4, str, (i5 & 32) != 0 ? null : str2);
    }
}
