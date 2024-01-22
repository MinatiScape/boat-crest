package com.bestmafen.baseble.messenger;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ReadMessage extends BleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f2219a;
    @NotNull
    public final String b;

    public ReadMessage(@NotNull String mService, @NotNull String mCharacteristic) {
        Intrinsics.checkNotNullParameter(mService, "mService");
        Intrinsics.checkNotNullParameter(mCharacteristic, "mCharacteristic");
        this.f2219a = mService;
        this.b = mCharacteristic;
    }

    public static /* synthetic */ ReadMessage copy$default(ReadMessage readMessage, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = readMessage.f2219a;
        }
        if ((i & 2) != 0) {
            str2 = readMessage.b;
        }
        return readMessage.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f2219a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final ReadMessage copy(@NotNull String mService, @NotNull String mCharacteristic) {
        Intrinsics.checkNotNullParameter(mService, "mService");
        Intrinsics.checkNotNullParameter(mCharacteristic, "mCharacteristic");
        return new ReadMessage(mService, mCharacteristic);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReadMessage) {
            ReadMessage readMessage = (ReadMessage) obj;
            return Intrinsics.areEqual(this.f2219a, readMessage.f2219a) && Intrinsics.areEqual(this.b, readMessage.b);
        }
        return false;
    }

    @NotNull
    public final String getMCharacteristic() {
        return this.b;
    }

    @NotNull
    public final String getMService() {
        return this.f2219a;
    }

    public int hashCode() {
        return (this.f2219a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "ReadMessage(mService=" + this.f2219a + ", mCharacteristic=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
