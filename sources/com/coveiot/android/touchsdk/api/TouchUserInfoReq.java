package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGProfile;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchUserInfoReq extends TouchELXBaseReq {
    @NotNull
    public final TGProfile e;

    public TouchUserInfoReq(@NotNull TGProfile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        this.e = profile;
    }

    @NotNull
    public final TGProfile getProfile() {
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
}
