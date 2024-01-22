package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class AmbientSoundLevelData implements Serializable {
    public int avgAmbientSound;
    public String dwmValue;
    public int maxAmbientSound;
    public int minAmbientSound;
    public String type;
    public int year;

    public AmbientSoundLevelData(int i, int i2, int i3, String str) {
        this.minAmbientSound = i;
        this.maxAmbientSound = i2;
        this.avgAmbientSound = i3;
        this.dwmValue = str;
    }

    public int getAvgAmbientSound() {
        return this.avgAmbientSound;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public int getMaxAmbientSound() {
        return this.maxAmbientSound;
    }

    public int getMinAmbientSound() {
        return this.minAmbientSound;
    }

    public String getType() {
        return this.type;
    }

    public int getYear() {
        return this.year;
    }

    public void setAvgAmbientSound(int i) {
        this.avgAmbientSound = i;
    }

    public void setDwmValue(String str) {
        this.dwmValue = str;
    }

    public void setMaxAmbientSound(int i) {
        this.maxAmbientSound = i;
    }

    public void setMinAmbientSound(int i) {
        this.minAmbientSound = i;
    }

    public AmbientSoundLevelData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
