package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AmbientSoundDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public int f3577a;
    public int b;
    public double c;
    @Nullable
    public AmbientSoundTimeLogBeanData d;
    public int e;

    public final double getAvgAmbientSound() {
        return this.c;
    }

    public final int getMaxAmbientSound() {
        return this.b;
    }

    public final int getMinAmbientSound() {
        return this.f3577a;
    }

    @Nullable
    public final AmbientSoundTimeLogBeanData getTimeLogBean() {
        return this.d;
    }

    public final int getTotalAmbientSoundTime() {
        return this.e;
    }

    public final void setAvgAmbientSound(double d) {
        this.c = d;
    }

    public final void setMaxAmbientSound(int i) {
        this.b = i;
    }

    public final void setMinAmbientSound(int i) {
        this.f3577a = i;
    }

    public final void setTimeLogBean(@Nullable AmbientSoundTimeLogBeanData ambientSoundTimeLogBeanData) {
        this.d = ambientSoundTimeLogBeanData;
    }

    public final void setTotalAmbientSoundTime(int i) {
        this.e = i;
    }
}
