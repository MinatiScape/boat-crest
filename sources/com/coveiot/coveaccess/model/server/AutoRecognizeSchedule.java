package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AutoRecognizeSchedule {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean active = false;
    @SerializedName("slots")
    private List<AutoRecognizeSlot> slots = null;

    public List<AutoRecognizeSlot> getSlots() {
        return this.slots;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public void setSlots(List<AutoRecognizeSlot> list) {
        this.slots = list;
    }
}
