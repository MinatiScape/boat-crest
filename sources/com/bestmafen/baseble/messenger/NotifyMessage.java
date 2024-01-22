package com.bestmafen.baseble.messenger;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class NotifyMessage extends BleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f2218a;
    @NotNull
    public final String b;
    public final boolean c;

    public /* synthetic */ NotifyMessage(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? true : z);
    }

    public static /* synthetic */ NotifyMessage copy$default(NotifyMessage notifyMessage, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = notifyMessage.f2218a;
        }
        if ((i & 2) != 0) {
            str2 = notifyMessage.b;
        }
        if ((i & 4) != 0) {
            z = notifyMessage.c;
        }
        return notifyMessage.copy(str, str2, z);
    }

    @NotNull
    public final String component1() {
        return this.f2218a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final boolean component3() {
        return this.c;
    }

    @NotNull
    public final NotifyMessage copy(@NotNull String mService, @NotNull String mCharacteristic, boolean z) {
        Intrinsics.checkNotNullParameter(mService, "mService");
        Intrinsics.checkNotNullParameter(mCharacteristic, "mCharacteristic");
        return new NotifyMessage(mService, mCharacteristic, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotifyMessage) {
            NotifyMessage notifyMessage = (NotifyMessage) obj;
            return Intrinsics.areEqual(this.f2218a, notifyMessage.f2218a) && Intrinsics.areEqual(this.b, notifyMessage.b) && this.c == notifyMessage.c;
        }
        return false;
    }

    @NotNull
    public final String getMCharacteristic() {
        return this.b;
    }

    public final boolean getMEnabled() {
        return this.c;
    }

    @NotNull
    public final String getMService() {
        return this.f2218a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.f2218a.hashCode() * 31) + this.b.hashCode()) * 31;
        boolean z = this.c;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "NotifyMessage(mService=" + this.f2218a + ", mCharacteristic=" + this.b + ", mEnabled=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public NotifyMessage(@NotNull String mService, @NotNull String mCharacteristic, boolean z) {
        Intrinsics.checkNotNullParameter(mService, "mService");
        Intrinsics.checkNotNullParameter(mCharacteristic, "mCharacteristic");
        this.f2218a = mService;
        this.b = mCharacteristic;
        this.c = z;
    }
}
