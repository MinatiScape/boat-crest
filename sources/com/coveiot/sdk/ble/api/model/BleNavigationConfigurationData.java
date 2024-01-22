package com.coveiot.sdk.ble.api.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleNavigationConfigurationData implements Serializable {
    public boolean isAODEnabled;
    public boolean isAudioEnabled;
    public boolean isHapticEnabled;

    public BleNavigationConfigurationData() {
    }

    public boolean isAODEnabled() {
        return this.isAODEnabled;
    }

    public boolean isAudioEnabled() {
        return this.isAudioEnabled;
    }

    public boolean isHapticEnabled() {
        return this.isHapticEnabled;
    }

    public void setAODEnabled(boolean z) {
        this.isAODEnabled = z;
    }

    public void setAudioEnabled(boolean z) {
        this.isAudioEnabled = z;
    }

    public void setHapticEnabled(boolean z) {
        this.isHapticEnabled = z;
    }

    @NonNull
    public String toString() {
        return "BleNavigationConfigurationData{isAudioEnabled=" + this.isAudioEnabled + ", isHapticEnabled=" + this.isHapticEnabled + ", isAODEnabled=" + this.isAODEnabled + '}';
    }

    public BleNavigationConfigurationData(boolean z, boolean z2, boolean z3) {
    }
}
