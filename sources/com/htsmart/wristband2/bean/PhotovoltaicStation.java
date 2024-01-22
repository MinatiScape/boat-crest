package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class PhotovoltaicStation {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11964a;
    public boolean b;
    public float c;
    public float d;
    public int e;
    public long f;
    public String g;
    public String h;

    public int getEquivalentPlantTrees() {
        return this.e;
    }

    public String getMasterName() {
        return this.g;
    }

    public String getStationName() {
        return this.h;
    }

    public float getTodayPowerGeneration() {
        return this.c;
    }

    public float getTotalPowerGeneration() {
        return this.d;
    }

    public long getUpdateTime() {
        return this.f;
    }

    public boolean isFault() {
        return this.b;
    }

    public boolean isValid() {
        return this.f11964a;
    }

    public void setEquivalentPlantTrees(int i) {
        this.e = i;
    }

    public void setFault(boolean z) {
        this.b = z;
    }

    public void setMasterName(String str) {
        this.g = str;
    }

    public void setStationName(String str) {
        this.h = str;
    }

    public void setTodayPowerGeneration(float f) {
        this.c = f;
    }

    public void setTotalPowerGeneration(float f) {
        this.d = f;
    }

    public void setUpdateTime(long j) {
        this.f = j;
    }

    public void setValid(boolean z) {
        this.f11964a = z;
    }
}
