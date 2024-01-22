package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public final class AmbientSoundHourData extends HourData {
    public int g;
    public int h;
    public double i;

    public final double getAvgAmbientSound() {
        return this.i;
    }

    public final int getMaxAmbientSound() {
        return this.h;
    }

    public final int getMinAmbientSound() {
        return this.g;
    }

    public final void setAvgAmbientSound(double d) {
        this.i = d;
    }

    public final void setMaxAmbientSound(int i) {
        this.h = i;
    }

    public final void setMinAmbientSound(int i) {
        this.g = i;
    }
}
