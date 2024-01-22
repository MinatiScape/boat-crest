package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGRemindDrinking;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchDrinkReminderReq extends TouchELXBaseReq {
    @NotNull
    public final TGRemindDrinking e;

    public TouchDrinkReminderReq(@NotNull TGRemindDrinking drinkReminderConfig) {
        Intrinsics.checkNotNullParameter(drinkReminderConfig, "drinkReminderConfig");
        this.e = drinkReminderConfig;
    }

    @NotNull
    public final TGRemindDrinking getDrinkReminderConfig() {
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
