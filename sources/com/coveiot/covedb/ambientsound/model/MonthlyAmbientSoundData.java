package com.coveiot.covedb.ambientsound.model;
/* loaded from: classes8.dex */
public class MonthlyAmbientSoundData {

    /* renamed from: a  reason: collision with root package name */
    public String f6940a;
    public int avgAmbientSound;
    public String mac_address;
    public int maxAmbientSound;
    public int minAmbientSound;
    public String year;

    public int getAvgAmbientSound() {
        return this.avgAmbientSound;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public int getMaxAmbientSound() {
        return this.maxAmbientSound;
    }

    public int getMinAmbientSound() {
        return this.minAmbientSound;
    }

    public String getMonth() {
        return this.f6940a;
    }

    public void setAvgAmbientSound(int i) {
        this.avgAmbientSound = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setMaxAmbientSound(int i) {
        this.maxAmbientSound = i;
    }

    public void setMinAmbientSound(int i) {
        this.minAmbientSound = i;
    }

    public void setMonth(String str) {
        this.f6940a = str;
    }
}
