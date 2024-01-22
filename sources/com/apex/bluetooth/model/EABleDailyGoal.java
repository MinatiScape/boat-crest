package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDailyGoal {
    public EABleDaily s_calorie;
    public EABleDaily s_distance;
    public EABleDaily s_duration;
    public EABleDaily s_sleep;
    public EABleDaily s_step;

    /* loaded from: classes.dex */
    public static class EABleDaily {
        public int goal;
        public int sw;

        public int getGoal() {
            return this.goal;
        }

        public int getSw() {
            return this.sw;
        }

        public void setGoal(int i) {
            this.goal = i;
        }

        public void setSw(int i) {
            this.sw = i;
        }

        public String toString() {
            return "EABleDaily{sw=" + this.sw + ", goal=" + this.goal + '}';
        }
    }

    public EABleDaily getS_calorie() {
        return this.s_calorie;
    }

    public EABleDaily getS_distance() {
        return this.s_distance;
    }

    public EABleDaily getS_duration() {
        return this.s_duration;
    }

    public EABleDaily getS_sleep() {
        return this.s_sleep;
    }

    public EABleDaily getS_step() {
        return this.s_step;
    }

    public void setS_calorie(EABleDaily eABleDaily) {
        this.s_calorie = eABleDaily;
    }

    public void setS_distance(EABleDaily eABleDaily) {
        this.s_distance = eABleDaily;
    }

    public void setS_duration(EABleDaily eABleDaily) {
        this.s_duration = eABleDaily;
    }

    public void setS_sleep(EABleDaily eABleDaily) {
        this.s_sleep = eABleDaily;
    }

    public void setS_step(EABleDaily eABleDaily) {
        this.s_step = eABleDaily;
    }

    public String toString() {
        return "EABleDailyGoal{s_step=" + this.s_step + ", s_calorie=" + this.s_calorie + ", s_distance=" + this.s_distance + ", s_duration=" + this.s_duration + ", s_sleep=" + this.s_sleep + '}';
    }
}
