package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BatterySaverConfig {
    public int autoStart;
    public boolean isActive;
    public boolean isEnabled;
    public int mode;

    public BatterySaverConfig(boolean z, int i, int i2, boolean z2) {
        this.isEnabled = z;
        this.mode = i;
        this.autoStart = i2;
        this.isActive = z2;
    }

    public int getAutoStart() {
        return this.autoStart;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public int getMode() {
        return this.mode;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setAutoStart(int i) {
        this.autoStart = i;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setMode(int i) {
        this.mode = i;
    }
}
