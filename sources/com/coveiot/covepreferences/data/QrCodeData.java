package com.coveiot.covepreferences.data;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class QrCodeData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f7035a;
    @Nullable
    public final String b;
    @Nullable
    public final String c;

    public QrCodeData(@Nullable Integer num, @Nullable String str, @Nullable String str2) {
        this.f7035a = num;
        this.b = str;
        this.c = str2;
    }

    public static /* synthetic */ QrCodeData copy$default(QrCodeData qrCodeData, Integer num, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = qrCodeData.f7035a;
        }
        if ((i & 2) != 0) {
            str = qrCodeData.b;
        }
        if ((i & 4) != 0) {
            str2 = qrCodeData.c;
        }
        return qrCodeData.copy(num, str, str2);
    }

    @Nullable
    public final Integer component1() {
        return this.f7035a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final QrCodeData copy(@Nullable Integer num, @Nullable String str, @Nullable String str2) {
        return new QrCodeData(num, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QrCodeData) {
            QrCodeData qrCodeData = (QrCodeData) obj;
            return Intrinsics.areEqual(this.f7035a, qrCodeData.f7035a) && Intrinsics.areEqual(this.b, qrCodeData.b) && Intrinsics.areEqual(this.c, qrCodeData.c);
        }
        return false;
    }

    @Nullable
    public final String getAppName() {
        return this.c;
    }

    @Nullable
    public final Integer getAppType() {
        return this.f7035a;
    }

    @Nullable
    public final String getQrCodeMetaData() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f7035a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "QrCodeData(appType=" + this.f7035a + ", qrCodeMetaData=" + this.b + ", appName=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
