package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public final class EABleStepFrequencyData implements Comparable<EABleStepFrequencyData> {
    public int step_freq_value;
    public long time_stamp;

    public int getStep_freq_value() {
        return this.step_freq_value;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setStep_freq_value(int i) {
        this.step_freq_value = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABleStepFrequencyData{time_stamp=" + this.time_stamp + ", step_freq_value=" + this.step_freq_value + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleStepFrequencyData eABleStepFrequencyData) {
        if (eABleStepFrequencyData != null) {
            if (eABleStepFrequencyData.getTime_stamp() > getTime_stamp()) {
                return -1;
            }
            return eABleStepFrequencyData.getTime_stamp() == getTime_stamp() ? 0 : 1;
        }
        return 0;
    }
}
