package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGEventReminder;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchEventReminderReq extends TouchELXBaseReq {
    @NotNull
    public final List<TGEventReminder> e;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchEventReminderReq(@NotNull List<? extends TGEventReminder> reminderList) {
        Intrinsics.checkNotNullParameter(reminderList, "reminderList");
        this.e = reminderList;
    }

    @NotNull
    public final List<TGEventReminder> getReminderList() {
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
