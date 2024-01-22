package com.coveiot.android.leonardo.more.listeners;

import com.coveiot.sdk.ble.api.model.AlarmInfo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface AlarmListener {
    @NotNull
    List<AlarmInfo> getLatestAlarmList();

    void onAlarmAdded(@NotNull AlarmInfo alarmInfo);

    void onAlarmUpdated(@NotNull AlarmInfo alarmInfo);

    void visibleTextview();
}
