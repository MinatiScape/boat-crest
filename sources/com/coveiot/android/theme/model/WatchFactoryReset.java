package com.coveiot.android.theme.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class WatchFactoryReset {
    private boolean isUnbindRequired;

    public WatchFactoryReset() {
        this(false, 1, null);
    }

    public WatchFactoryReset(boolean z) {
        this.isUnbindRequired = z;
    }

    public static /* synthetic */ WatchFactoryReset copy$default(WatchFactoryReset watchFactoryReset, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = watchFactoryReset.isUnbindRequired;
        }
        return watchFactoryReset.copy(z);
    }

    public final boolean component1() {
        return this.isUnbindRequired;
    }

    @NotNull
    public final WatchFactoryReset copy(boolean z) {
        return new WatchFactoryReset(z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WatchFactoryReset) && this.isUnbindRequired == ((WatchFactoryReset) obj).isUnbindRequired;
    }

    public int hashCode() {
        boolean z = this.isUnbindRequired;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final boolean isUnbindRequired() {
        return this.isUnbindRequired;
    }

    public final void setUnbindRequired(boolean z) {
        this.isUnbindRequired = z;
    }

    @NotNull
    public String toString() {
        return "WatchFactoryReset(isUnbindRequired=" + this.isUnbindRequired + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchFactoryReset(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }
}
