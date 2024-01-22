package com.coveiot.android.bleabstract.request;

import androidx.annotation.Nullable;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.CustomReminderAbstract;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class SetCustomRemindersRequest extends BleBaseRequest {
    public ArrayList<CustomReminderAbstract> f;

    public SetCustomRemindersRequest(ArrayList<CustomReminderAbstract> arrayList) {
        this.f = arrayList;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_CUSTOM_REMINDERS;
    }

    public ArrayList<CustomReminderAbstract> getCustomReminderAbstractArrayList() {
        return this.f;
    }
}
