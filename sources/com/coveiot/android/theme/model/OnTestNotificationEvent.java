package com.coveiot.android.theme.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class OnTestNotificationEvent {
    private final boolean isReceived;

    public OnTestNotificationEvent(boolean z) {
        this.isReceived = z;
    }

    public static /* synthetic */ OnTestNotificationEvent copy$default(OnTestNotificationEvent onTestNotificationEvent, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = onTestNotificationEvent.isReceived;
        }
        return onTestNotificationEvent.copy(z);
    }

    public final boolean component1() {
        return this.isReceived;
    }

    @NotNull
    public final OnTestNotificationEvent copy(boolean z) {
        return new OnTestNotificationEvent(z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OnTestNotificationEvent) && this.isReceived == ((OnTestNotificationEvent) obj).isReceived;
    }

    public int hashCode() {
        boolean z = this.isReceived;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final boolean isReceived() {
        return this.isReceived;
    }

    @NotNull
    public String toString() {
        return "OnTestNotificationEvent(isReceived=" + this.isReceived + HexStringBuilder.COMMENT_END_CHAR;
    }
}
