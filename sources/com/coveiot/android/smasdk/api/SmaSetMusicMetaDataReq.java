package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleMusicControl;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetMusicMetaDataReq extends SmaBaseReq {
    @Nullable
    public List<BleMusicControl> d;

    @Nullable
    public final List<BleMusicControl> getMusicControls() {
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

    public final void setMusicControls(@Nullable List<BleMusicControl> list) {
        this.d = list;
    }
}
