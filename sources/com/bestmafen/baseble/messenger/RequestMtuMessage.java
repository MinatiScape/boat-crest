package com.bestmafen.baseble.messenger;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RequestMtuMessage extends BleMessage {

    /* renamed from: a  reason: collision with root package name */
    public final int f2221a;

    public RequestMtuMessage() {
        this(0, 1, null);
    }

    public /* synthetic */ RequestMtuMessage(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 512 : i);
    }

    public static /* synthetic */ RequestMtuMessage copy$default(RequestMtuMessage requestMtuMessage, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = requestMtuMessage.f2221a;
        }
        return requestMtuMessage.copy(i);
    }

    public final int component1() {
        return this.f2221a;
    }

    @NotNull
    public final RequestMtuMessage copy(int i) {
        return new RequestMtuMessage(i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RequestMtuMessage) && this.f2221a == ((RequestMtuMessage) obj).f2221a;
    }

    public final int getMMtu() {
        return this.f2221a;
    }

    public int hashCode() {
        return Integer.hashCode(this.f2221a);
    }

    @NotNull
    public String toString() {
        return "RequestMtuMessage(mMtu=" + this.f2221a + HexStringBuilder.COMMENT_END_CHAR;
    }

    public RequestMtuMessage(int i) {
        this.f2221a = i;
    }
}
