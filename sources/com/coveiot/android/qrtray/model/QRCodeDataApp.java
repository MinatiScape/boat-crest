package com.coveiot.android.qrtray.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QRCodeDataApp {

    /* renamed from: a  reason: collision with root package name */
    public int f5576a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public String e;
    @Nullable
    public String f;
    public boolean g;
    @Nullable
    public String h;

    public QRCodeDataApp() {
        this(0, null, null, null, null, null, false, null, 255, null);
    }

    public QRCodeDataApp(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z, @Nullable String str6) {
        this.f5576a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = z;
        this.h = str6;
    }

    public final int component1() {
        return this.f5576a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
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

    public final boolean component7() {
        return this.g;
    }

    @Nullable
    public final String component8() {
        return this.h;
    }

    @NotNull
    public final QRCodeDataApp copy(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z, @Nullable String str6) {
        return new QRCodeDataApp(i, str, str2, str3, str4, str5, z, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QRCodeDataApp) {
            QRCodeDataApp qRCodeDataApp = (QRCodeDataApp) obj;
            return this.f5576a == qRCodeDataApp.f5576a && Intrinsics.areEqual(this.b, qRCodeDataApp.b) && Intrinsics.areEqual(this.c, qRCodeDataApp.c) && Intrinsics.areEqual(this.d, qRCodeDataApp.d) && Intrinsics.areEqual(this.e, qRCodeDataApp.e) && Intrinsics.areEqual(this.f, qRCodeDataApp.f) && this.g == qRCodeDataApp.g && Intrinsics.areEqual(this.h, qRCodeDataApp.h);
        }
        return false;
    }

    public final boolean getAppliedToWatch() {
        return this.g;
    }

    @Nullable
    public final String getId() {
        return this.d;
    }

    public final int getImageId() {
        return this.f5576a;
    }

    @Nullable
    public final String getImageTag() {
        return this.c;
    }

    @Nullable
    public final String getImageTitle() {
        return this.b;
    }

    @Nullable
    public final String getImageUrl() {
        return this.h;
    }

    @Nullable
    public final String getLastAppliedDate() {
        return this.e;
    }

    @Nullable
    public final String getServerId() {
        return this.f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = Integer.hashCode(this.f5576a) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.e;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        boolean z = this.g;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode6 + i) * 31;
        String str6 = this.h;
        return i2 + (str6 != null ? str6.hashCode() : 0);
    }

    public final void setAppliedToWatch(boolean z) {
        this.g = z;
    }

    public final void setId(@Nullable String str) {
        this.d = str;
    }

    public final void setImageId(int i) {
        this.f5576a = i;
    }

    public final void setImageTag(@Nullable String str) {
        this.c = str;
    }

    public final void setImageTitle(@Nullable String str) {
        this.b = str;
    }

    public final void setImageUrl(@Nullable String str) {
        this.h = str;
    }

    public final void setLastAppliedDate(@Nullable String str) {
        this.e = str;
    }

    public final void setServerId(@Nullable String str) {
        this.f = str;
    }

    @NotNull
    public String toString() {
        return "QRCodeDataApp(imageId=" + this.f5576a + ", imageTitle=" + this.b + ", imageTag=" + this.c + ", id=" + this.d + ", lastAppliedDate=" + this.e + ", serverId=" + this.f + ", appliedToWatch=" + this.g + ", imageUrl=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ QRCodeDataApp(int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) == 0 ? z : false, (i2 & 128) == 0 ? str6 : null);
    }
}
