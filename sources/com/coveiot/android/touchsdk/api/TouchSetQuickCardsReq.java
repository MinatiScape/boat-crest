package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGQuickCard;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetQuickCardsReq extends TouchELXBaseReq {
    @NotNull
    public final List<TGQuickCard> e;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchSetQuickCardsReq(@NotNull List<? extends TGQuickCard> tgQuickCardList) {
        Intrinsics.checkNotNullParameter(tgQuickCardList, "tgQuickCardList");
        this.e = tgQuickCardList;
    }

    @NotNull
    public final List<TGQuickCard> getTgQuickCardList() {
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
