package com.coveiot.android.smasdk.api;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaUploadContactReq extends SmaBaseReq {
    @Nullable
    public byte[] d;

    @Nullable
    public final byte[] getContactBytes() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setContactBytes(@Nullable byte[] bArr) {
        this.d = bArr;
    }
}
