package com.coveiot.android.customreminders.model;

import com.coveiot.android.customreminders.preference.gsonadapters.JsonSubtype;
import com.coveiot.android.customreminders.preference.gsonadapters.JsonType;
import java.io.Serializable;
@JsonType(property = "type", subtypes = {@JsonSubtype(clazz = MedicineReminder.class, name = "MEDICINE"), @JsonSubtype(clazz = MeetingReminder.class, name = "MEETING"), @JsonSubtype(clazz = OtherReminder.class, name = "OTHERS"), @JsonSubtype(clazz = HandWashReminder.class, name = "HAND_WASH"), @JsonSubtype(clazz = DrinkWaterReminder.class, name = "DRINK")})
/* loaded from: classes3.dex */
public abstract class CustomReminder implements Serializable {
    public String description;
    public boolean isReminderOn;
    public RepeatModel repeatModel;
    public String type;

    public CustomReminder(RepeatModel repeatModel, String str, boolean z, String str2) {
        this.isReminderOn = true;
        this.repeatModel = repeatModel;
        this.description = str;
        this.isReminderOn = z;
        this.type = str2;
    }

    public String getDescription() {
        return this.description;
    }

    public RepeatModel getRepeatModel() {
        return this.repeatModel;
    }

    public String getType() {
        return this.type;
    }

    public boolean isReminderOn() {
        return this.isReminderOn;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setReminderOn(boolean z) {
        this.isReminderOn = z;
    }

    public void setRepeatModel(RepeatModel repeatModel) {
        this.repeatModel = repeatModel;
    }

    public void setType(String str) {
        this.type = str;
    }

    public CustomReminder(String str) {
        this.isReminderOn = true;
        this.type = str;
    }
}
