package com.coveiot.android.customreminders.listeners;

import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.model.CustomReminder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface PostEditReminderListListener {
    void onEditReminder(int i, @NotNull CustomReminder customReminder, @NotNull ReminderType reminderType);
}
