package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class SilentModeConfig {
    public boolean isSilentModeEnabled;
    public boolean isVibrationEnabled;

    public SilentModeConfig(boolean z, boolean z2) {
        this.isSilentModeEnabled = z;
        this.isVibrationEnabled = z2;
    }

    public boolean isSilentModeEnabled() {
        return this.isSilentModeEnabled;
    }

    public boolean isVibrationEnabled() {
        return this.isVibrationEnabled;
    }
}
