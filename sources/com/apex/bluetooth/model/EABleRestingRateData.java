package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleRestingRateData implements Comparable<EABleRestingRateData> {
    public int hr_value;
    public long time_stamp;

    public int getHr_value() {
        return this.hr_value;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setHr_value(int i) {
        this.hr_value = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABleRestingRateData{time_stamp=" + this.time_stamp + ", hr_value=" + this.hr_value + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleRestingRateData eABleRestingRateData) {
        if (eABleRestingRateData != null) {
            if (getTime_stamp() > eABleRestingRateData.getTime_stamp()) {
                return 1;
            }
            return getTime_stamp() == eABleRestingRateData.getTime_stamp() ? 0 : -1;
        }
        return 0;
    }
}