package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public class TGBreathTrain {
    private int avgHr;
    private int avgStress;
    private int duration;
    @Nullable
    private List<ItemBean> items;
    private int startTime;
    private int startUtcTime;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int heartRate;
        private int offset;
        private int stressValue;

        public int getHeartRate() {
            return this.heartRate;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getStressValue() {
            return this.stressValue;
        }

        public void setHeartRate(int i) {
            this.heartRate = i;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public void setStressValue(int i) {
            this.stressValue = i;
        }
    }

    public int getAvgHr() {
        return this.avgHr;
    }

    public int getAvgStress() {
        return this.avgStress;
    }

    public int getDuration() {
        return this.duration;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getStartUtcTime() {
        return this.startUtcTime;
    }

    public void setAvgHr(int i) {
        this.avgHr = i;
    }

    public void setAvgStress(int i) {
        this.avgStress = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setStartUtcTime(int i) {
        this.startUtcTime = i;
    }
}
