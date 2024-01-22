package com.coveiot.android.leonardo.more.listeners;

import com.coveiot.android.bleabstract.models.EventReminder;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface EventReminderListener {
    @NotNull
    List<EventReminder> getLatestEventReminderList();

    void onDeleteEventReminder(@NotNull EventReminder eventReminder);

    void onEventReminderAdded(@NotNull EventReminder eventReminder);

    void onEventReminderUpdated(@NotNull EventReminder eventReminder);

    void visibleTextview();
}
