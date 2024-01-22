package com.bestmafen.baseble.messenger;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RequestConnectionPriorityMessage extends BleMessage {

    /* renamed from: a  reason: collision with root package name */
    public final int f2220a;

    public RequestConnectionPriorityMessage(int i) {
        this.f2220a = i;
    }

    public static /* synthetic */ RequestConnectionPriorityMessage copy$default(RequestConnectionPriorityMessage requestConnectionPriorityMessage, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = requestConnectionPriorityMessage.f2220a;
        }
        return requestConnectionPriorityMessage.copy(i);
    }

    public final int component1() {
        return this.f2220a;
    }

    @NotNull
    public final RequestConnectionPriorityMessage copy(int i) {
        return new RequestConnectionPriorityMessage(i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RequestConnectionPriorityMessage) && this.f2220a == ((RequestConnectionPriorityMessage) obj).f2220a;
    }

    public final int getMPriority() {
        return this.f2220a;
    }

    public int hashCode() {
        return Integer.hashCode(this.f2220a);
    }

    @NotNull
    public String toString() {
        return "RequestConnectionPriorityMessage(mPriority=" + this.f2220a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
