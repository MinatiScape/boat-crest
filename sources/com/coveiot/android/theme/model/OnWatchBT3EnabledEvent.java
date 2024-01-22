package com.coveiot.android.theme.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class OnWatchBT3EnabledEvent {
    private final boolean isEnabled;

    public OnWatchBT3EnabledEvent(boolean z) {
        this.isEnabled = z;
    }

    public static /* synthetic */ OnWatchBT3EnabledEvent copy$default(OnWatchBT3EnabledEvent onWatchBT3EnabledEvent, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = onWatchBT3EnabledEvent.isEnabled;
        }
        return onWatchBT3EnabledEvent.copy(z);
    }

    public final boolean component1() {
        return this.isEnabled;
    }

    @NotNull
    public final OnWatchBT3EnabledEvent copy(boolean z) {
        return new OnWatchBT3EnabledEvent(z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OnWatchBT3EnabledEvent) && this.isEnabled == ((OnWatchBT3EnabledEvent) obj).isEnabled;
    }

    public int hashCode() {
        boolean z = this.isEnabled;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    @NotNull
    public String toString() {
        return "OnWatchBT3EnabledEvent(isEnabled=" + this.isEnabled + HexStringBuilder.COMMENT_END_CHAR;
    }
}
