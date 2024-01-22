package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleUserProfile;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetUserProfileReq extends SmaBaseReq {
    @Nullable
    public BleUserProfile d;

    @Nullable
    public final BleUserProfile getUserProfile() {
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

    public final void setUserProfile(@Nullable BleUserProfile bleUserProfile) {
        this.d = bleUserProfile;
    }
}
