package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGMusicInfo;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchMusicMetaDataReq extends TouchELXBaseReq {
    @NotNull
    public TGMusicInfo e;

    public TouchMusicMetaDataReq(@NotNull TGMusicInfo musicInfo) {
        Intrinsics.checkNotNullParameter(musicInfo, "musicInfo");
        this.e = musicInfo;
    }

    @NotNull
    public final TGMusicInfo getMusicInfo() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setMusicInfo(@NotNull TGMusicInfo tGMusicInfo) {
        Intrinsics.checkNotNullParameter(tGMusicInfo, "<set-?>");
        this.e = tGMusicInfo;
    }
}
