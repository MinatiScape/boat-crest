package com.coveiot.khsmadb.temperature;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.khsmadb.Utils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes8.dex */
public final class KhBleTemperature {

    /* renamed from: a  reason: collision with root package name */
    public int f7161a;
    public int b;
    @NotNull
    public String c;
    @Nullable
    public String d;
    @ColumnInfo(name = "isProcessed")
    public boolean e;
    @ColumnInfo(name = "Timestamp")
    public String timeStamp;

    public KhBleTemperature(int i, int i2, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f7161a = i;
        this.b = i2;
        this.c = mMacAddress;
        this.d = str;
        Utils utils = Utils.INSTANCE;
        this.d = utils.convertSDKTimeToDate(i);
        String convertSDKTime = utils.convertSDKTime(this.f7161a);
        Intrinsics.checkNotNull(convertSDKTime);
        setTimeStamp(convertSDKTime);
    }

    public static /* synthetic */ KhBleTemperature copy$default(KhBleTemperature khBleTemperature, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = khBleTemperature.f7161a;
        }
        if ((i3 & 2) != 0) {
            i2 = khBleTemperature.b;
        }
        if ((i3 & 4) != 0) {
            str = khBleTemperature.c;
        }
        if ((i3 & 8) != 0) {
            str2 = khBleTemperature.d;
        }
        return khBleTemperature.copy(i, i2, str, str2);
    }

    public final int component1() {
        return this.f7161a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final KhBleTemperature copy(int i, int i2, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhBleTemperature(i, i2, mMacAddress, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhBleTemperature) {
            KhBleTemperature khBleTemperature = (KhBleTemperature) obj;
            return this.f7161a == khBleTemperature.f7161a && this.b == khBleTemperature.b && Intrinsics.areEqual(this.c, khBleTemperature.c) && Intrinsics.areEqual(this.d, khBleTemperature.d);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.d;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.c;
    }

    public final int getMTemperature() {
        return this.b;
    }

    public final int getMTime() {
        return this.f7161a;
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
        int hashCode = ((((Integer.hashCode(this.f7161a) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode()) * 31;
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
        this.c = str;
    }

    public final void setMTemperature(int i) {
        this.b = i;
    }

    public final void setMTime(int i) {
        this.f7161a = i;
    }

    public final void setProcessed(boolean z) {
        this.e = z;
    }

    public final void setTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timeStamp = str;
    }

    @NotNull
    public String toString() {
        return "KhBleTemperature(mTime=" + this.f7161a + ", mTemperature=" + this.b + ", mMacAddress='" + this.c + "', convertedDate=" + this.d + ", isProcessed=" + this.e + ", timeStamp='" + getTimeStamp() + "')";
    }

    public /* synthetic */ KhBleTemperature(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, str, (i3 & 8) != 0 ? null : str2);
    }
}
