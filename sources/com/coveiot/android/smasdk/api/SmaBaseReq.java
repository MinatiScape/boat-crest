package com.coveiot.android.smasdk.api;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.smasdk.SmaResponseListener;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class SmaBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public SmaResponseListener f5764a;
    @Nullable
    public String b;
    @NotNull
    public BleKeyFlag c = BleKeyFlag.NONE;
    public BleKey key;

    @NotNull
    public final BleKey getKey() {
        BleKey bleKey = this.key;
        if (bleKey != null) {
            return bleKey;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Constants.KEY_KEY);
        return null;
    }

    @NotNull
    public final BleKeyFlag getKeyFlag() {
        return this.c;
    }

    @Nullable
    public final String getReqId() {
        return this.b;
    }

    @Nullable
    public final SmaResponseListener getResponseListener() {
        return this.f5764a;
    }

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public final void setKey(@NotNull BleKey bleKey) {
        Intrinsics.checkNotNullParameter(bleKey, "<set-?>");
        this.key = bleKey;
    }

    public final void setKeyFlag(@NotNull BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKeyFlag, "<set-?>");
        this.c = bleKeyFlag;
    }

    public final void setReqId(@Nullable String str) {
        this.b = str;
    }

    public final void setResponseListener(@Nullable SmaResponseListener smaResponseListener) {
        this.f5764a = smaResponseListener;
    }
}
