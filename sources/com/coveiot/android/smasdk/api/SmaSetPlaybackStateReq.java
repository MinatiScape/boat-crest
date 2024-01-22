package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleMusicControl;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetPlaybackStateReq extends SmaBaseReq {
    @Nullable
    public BleMusicControl d;

    @Nullable
    public final BleMusicControl getMusicControl() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setMusicControl(@Nullable BleMusicControl bleMusicControl) {
        this.d = bleMusicControl;
    }
}
