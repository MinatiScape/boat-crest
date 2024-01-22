package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGAlarm;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchAlarmReq extends TouchELXBaseReq {
    @NotNull
    public final List<TGAlarm> e;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchAlarmReq(@NotNull List<? extends TGAlarm> alarmList) {
        Intrinsics.checkNotNullParameter(alarmList, "alarmList");
        this.e = alarmList;
    }

    @NotNull
    public final List<TGAlarm> getAlarmList() {
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
