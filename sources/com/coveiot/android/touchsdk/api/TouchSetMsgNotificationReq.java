package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGMessage;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetMsgNotificationReq extends TouchELXBaseReq {
    @NotNull
    public TGMessage e;

    public TouchSetMsgNotificationReq(@NotNull TGMessage tgMessage) {
        Intrinsics.checkNotNullParameter(tgMessage, "tgMessage");
        this.e = tgMessage;
    }

    @NotNull
    public final TGMessage getTgMessage() {
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

    public final void setTgMessage(@NotNull TGMessage tGMessage) {
        Intrinsics.checkNotNullParameter(tGMessage, "<set-?>");
        this.e = tGMessage;
    }
}
