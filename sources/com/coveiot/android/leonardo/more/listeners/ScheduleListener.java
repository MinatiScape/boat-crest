package com.coveiot.android.leonardo.more.listeners;

import com.coveiot.sdk.ble.api.model.ScheduleInfo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface ScheduleListener {
    @NotNull
    List<ScheduleInfo> getDeletedScheduleList();

    @NotNull
    List<ScheduleInfo> getLatestScheduleList();

    void onDeleteSchedule(@NotNull ScheduleInfo scheduleInfo);

    void onScheduleAdded(@NotNull ScheduleInfo scheduleInfo);

    void onScheduleUpdated(@NotNull ScheduleInfo scheduleInfo);

    void visibleTextview();
}
