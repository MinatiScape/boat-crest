package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class BleNavigationDisclaimerData implements Serializable {
    public Calendar calendar;
    public boolean isUserAccepted;
    public String versionText;

    public Calendar getCalendar() {
        return this.calendar;
    }

    public String getVersionText() {
        return this.versionText;
    }

    public boolean isUserAccepted() {
        return this.isUserAccepted;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setUserAccepted(boolean z) {
        this.isUserAccepted = z;
    }

    public void setVersionText(String str) {
        this.versionText = str;
    }
}
