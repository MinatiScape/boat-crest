package com.bestmafen.baseble.messenger;

import com.bestmafen.baseble.data.ByteArrayExtKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class WriteMessage extends BleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f2222a;
    @NotNull
    public final String b;
    @NotNull
    public final byte[] c;

    public WriteMessage(@NotNull String mService, @NotNull String mCharacteristic, @NotNull byte[] mData) {
        Intrinsics.checkNotNullParameter(mService, "mService");
        Intrinsics.checkNotNullParameter(mCharacteristic, "mCharacteristic");
        Intrinsics.checkNotNullParameter(mData, "mData");
        this.f2222a = mService;
        this.b = mCharacteristic;
        this.c = mData;
    }

    @NotNull
    public final String getMCharacteristic() {
        return this.b;
    }

    @NotNull
    public final byte[] getMData() {
        return this.c;
    }

    @NotNull
    public final String getMService() {
        return this.f2222a;
    }

    @NotNull
    public String toString() {
        return "WriteMessage(service='" + this.f2222a + "', characteristic='" + this.b + "', data=" + ByteArrayExtKt.getMHexString(this.c);
    }
}
