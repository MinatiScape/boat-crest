package com.coveiot.android.customreminders.listeners;

import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ReminderValidationListner {
    void addReminder(@NotNull CustomReminder customReminder);

    @NotNull
    ArrayList<MedicineReminder> getAllMedicineReminderList();

    boolean isAllRemindersValid();
}
