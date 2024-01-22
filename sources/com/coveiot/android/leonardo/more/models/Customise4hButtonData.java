package com.coveiot.android.leonardo.more.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class Customise4hButtonData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f5134a;
    @NotNull
    public final String b;
    public final int c;
    public boolean d;
    public boolean e;

    public Customise4hButtonData(@NotNull String actionId, @NotNull String name, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(name, "name");
        this.f5134a = actionId;
        this.b = name;
        this.c = i;
        this.d = z;
        this.e = z2;
    }

    public static /* synthetic */ Customise4hButtonData copy$default(Customise4hButtonData customise4hButtonData, String str, String str2, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customise4hButtonData.f5134a;
        }
        if ((i2 & 2) != 0) {
            str2 = customise4hButtonData.b;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            i = customise4hButtonData.c;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = customise4hButtonData.d;
        }
        boolean z3 = z;
        if ((i2 & 16) != 0) {
            z2 = customise4hButtonData.e;
        }
        return customise4hButtonData.copy(str, str3, i3, z3, z2);
    }

    @NotNull
    public final String component1() {
        return this.f5134a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    public final boolean component5() {
        return this.e;
    }

    @NotNull
    public final Customise4hButtonData copy(@NotNull String actionId, @NotNull String name, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(actionId, "actionId");
        Intrinsics.checkNotNullParameter(name, "name");
        return new Customise4hButtonData(actionId, name, i, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Customise4hButtonData) {
            Customise4hButtonData customise4hButtonData = (Customise4hButtonData) obj;
            return Intrinsics.areEqual(this.f5134a, customise4hButtonData.f5134a) && Intrinsics.areEqual(this.b, customise4hButtonData.b) && this.c == customise4hButtonData.c && this.d == customise4hButtonData.d && this.e == customise4hButtonData.e;
        }
        return false;
    }

    @NotNull
    public final String getActionId() {
        return this.f5134a;
    }

    public final int getFwCode() {
        return this.c;
    }

    @NotNull
    public final String getName() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.f5134a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31;
        boolean z = this.d;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.e;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public final boolean isChecked() {
        return this.e;
    }

    public final boolean isEnabled() {
        return this.d;
    }

    public final void setChecked(boolean z) {
        this.e = z;
    }

    public final void setEnabled(boolean z) {
        this.d = z;
    }

    @NotNull
    public String toString() {
        return "Customise4hButtonData(actionId=" + this.f5134a + ", name=" + this.b + ", fwCode=" + this.c + ", isEnabled=" + this.d + ", isChecked=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
