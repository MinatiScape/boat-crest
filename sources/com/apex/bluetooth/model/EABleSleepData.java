package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleSleepData implements Comparable<EABleSleepData> {
    public SleepMode e_sleep_node;
    public long time_stamp;

    /* loaded from: classes.dex */
    public enum SleepMode {
        activity(0),
        enter(1),
        wake(2),
        rem(3),
        light(4),
        deep(5),
        quit(6),
        unknown(7),
        summary(8);
        
        public int value;

        SleepMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public SleepMode getE_sleep_node() {
        return this.e_sleep_node;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setE_sleep_node(SleepMode sleepMode) {
        this.e_sleep_node = sleepMode;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EABleSleepData{time_stamp=");
        sb.append(this.time_stamp);
        sb.append(", e_sleep_node=");
        SleepMode sleepMode = this.e_sleep_node;
        sb.append(sleepMode != null ? sleepMode.getValue() : 8);
        sb.append('}');
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleSleepData eABleSleepData) {
        if (eABleSleepData != null) {
            if (getTime_stamp() > eABleSleepData.getTime_stamp()) {
                return 1;
            }
            return getTime_stamp() == eABleSleepData.getTime_stamp() ? 0 : -1;
        }
        return 0;
    }
}
