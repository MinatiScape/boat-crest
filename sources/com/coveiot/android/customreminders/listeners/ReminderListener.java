package com.coveiot.android.customreminders.listeners;

import com.coveiot.android.customreminders.model.CustomReminder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ReminderListener {
    void onError(@NotNull String str);

    void onSuccess(@NotNull CustomReminder customReminder);
}
