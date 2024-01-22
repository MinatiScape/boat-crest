package com.coveiot.android.customreminders.listeners;

import com.coveiot.android.customreminders.ReminderType;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface EditReminderListListener {
    void onDeleteReminder(int i, @NotNull ReminderType reminderType);

    void onEditReminder(int i, @NotNull ReminderType reminderType);

    void onReminderStatusChange(int i, boolean z);
}
