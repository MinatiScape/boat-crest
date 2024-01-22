package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.TGDial;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchDIYWatchFaceReq extends TouchELXBaseReq {
    @NotNull
    public TGDial e;

    public TouchDIYWatchFaceReq(@NotNull TGDial photoDialBuilder) {
        Intrinsics.checkNotNullParameter(photoDialBuilder, "photoDialBuilder");
        this.e = photoDialBuilder;
    }

    @NotNull
    public final TGDial getPhotoDialBuilder() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setPhotoDialBuilder(@NotNull TGDial tGDial) {
        Intrinsics.checkNotNullParameter(tGDial, "<set-?>");
        this.e = tGDial;
    }
}
