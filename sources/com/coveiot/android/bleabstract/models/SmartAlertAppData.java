package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmartAlertAppData implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public final int f3451a;
    @NotNull
    public final String b;
    public final int c;
    public final int d;

    public SmartAlertAppData(int i, @NotNull String name, int i2, int i3) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.f3451a = i;
        this.b = name;
        this.c = i2;
        this.d = i3;
    }

    public static /* synthetic */ SmartAlertAppData copy$default(SmartAlertAppData smartAlertAppData, int i, String str, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = smartAlertAppData.f3451a;
        }
        if ((i4 & 2) != 0) {
            str = smartAlertAppData.b;
        }
        if ((i4 & 4) != 0) {
            i2 = smartAlertAppData.c;
        }
        if ((i4 & 8) != 0) {
            i3 = smartAlertAppData.d;
        }
        return smartAlertAppData.copy(i, str, i2, i3);
    }

    public final int component1() {
        return this.f3451a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final SmartAlertAppData copy(int i, @NotNull String name, int i2, int i3) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SmartAlertAppData(i, name, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SmartAlertAppData) {
            SmartAlertAppData smartAlertAppData = (SmartAlertAppData) obj;
            return this.f3451a == smartAlertAppData.f3451a && Intrinsics.areEqual(this.b, smartAlertAppData.b) && this.c == smartAlertAppData.c && this.d == smartAlertAppData.d;
        }
        return false;
    }

    public final int getId() {
        return this.f3451a;
    }

    public final int getLogoInfo() {
        return this.d;
    }

    public final int getLogoSize() {
        return this.c;
    }

    @NotNull
    public final String getName() {
        return this.b;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.f3451a) * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
    }

    @NotNull
    public String toString() {
        return "SmartAlertAppData(id=" + this.f3451a + ", name=" + this.b + ", logoSize=" + this.c + ", logoInfo=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
