package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABlePaceData implements Comparable<EABlePaceData> {
    public int step_pace_value;
    public long time_stamp;

    public int getStep_pace_value() {
        return this.step_pace_value;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setStep_pace_value(int i) {
        this.step_pace_value = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABlePaceData{time_stamp=" + this.time_stamp + ", step_pace_value=" + this.step_pace_value + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABlePaceData eABlePaceData) {
        if (eABlePaceData != null) {
            if (eABlePaceData.getTime_stamp() > getTime_stamp()) {
                return -1;
            }
            return eABlePaceData.getTime_stamp() == getTime_stamp() ? 0 : 1;
        }
        return 0;
    }
}
