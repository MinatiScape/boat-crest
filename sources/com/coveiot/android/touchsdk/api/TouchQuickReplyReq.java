package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGQuickReply;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchQuickReplyReq extends TouchELXBaseReq {
    @NotNull
    public final List<TGQuickReply> e;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchQuickReplyReq(@NotNull List<? extends TGQuickReply> quickReplyList) {
        Intrinsics.checkNotNullParameter(quickReplyList, "quickReplyList");
        this.e = quickReplyList;
    }

    @NotNull
    public final List<TGQuickReply> getQuickReplyList() {
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
