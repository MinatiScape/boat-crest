package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PermissionsRequiredData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4792a;
    @NotNull
    public final String b;

    public PermissionsRequiredData(@NotNull String title, @NotNull String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        this.f4792a = title;
        this.b = description;
    }

    public static /* synthetic */ PermissionsRequiredData copy$default(PermissionsRequiredData permissionsRequiredData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = permissionsRequiredData.f4792a;
        }
        if ((i & 2) != 0) {
            str2 = permissionsRequiredData.b;
        }
        return permissionsRequiredData.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4792a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final PermissionsRequiredData copy(@NotNull String title, @NotNull String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        return new PermissionsRequiredData(title, description);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PermissionsRequiredData) {
            PermissionsRequiredData permissionsRequiredData = (PermissionsRequiredData) obj;
            return Intrinsics.areEqual(this.f4792a, permissionsRequiredData.f4792a) && Intrinsics.areEqual(this.b, permissionsRequiredData.b);
        }
        return false;
    }

    @NotNull
    public final String getDescription() {
        return this.b;
    }

    @NotNull
    public final String getTitle() {
        return this.f4792a;
    }

    public int hashCode() {
        return (this.f4792a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "PermissionsRequiredData(title=" + this.f4792a + ", description=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
