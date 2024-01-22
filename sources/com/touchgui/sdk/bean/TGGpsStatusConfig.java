package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
/* loaded from: classes12.dex */
public class TGGpsStatusConfig {
    private int gnsValue;
    private int gsopCycle;
    private int gsopMode;
    private int startMode;
    @Nullable
    private Date utcTime;

    public int getGnsValue() {
        return this.gnsValue;
    }

    public int getGsopCycle() {
        return this.gsopCycle;
    }

    public int getGsopMode() {
        return this.gsopMode;
    }

    public int getStartMode() {
        return this.startMode;
    }

    @Nullable
    public Date getUtcTime() {
        return this.utcTime;
    }

    public void setGnsValue(int i) {
        this.gnsValue = i;
    }

    public void setGsopCycle(int i) {
        this.gsopCycle = i;
    }

    public void setGsopMode(int i) {
        this.gsopMode = i;
    }

    public void setStartMode(int i) {
        this.startMode = i;
    }

    public void setUtcTime(@Nullable Date date) {
        this.utcTime = date;
    }
}
