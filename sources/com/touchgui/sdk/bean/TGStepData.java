package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGStepData {
    private Date date;
    private int itemCount;
    @Nullable
    private List<ItemBean> items;
    private int minuteOffset;
    private int packetCount;
    private int perMinute;
    private int standCount;
    private int totalActiveTime;
    private int totalCal;
    private int totalDistance;
    private int totalSteps;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int activeTime;
        private int calories;
        private int distance;
        private int mode;
        private int stepCount;

        public int getActiveTime() {
            return this.activeTime;
        }

        public int getCalories() {
            return this.calories;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getMode() {
            return this.mode;
        }

        public int getStepCount() {
            return this.stepCount;
        }

        public boolean isStandUp() {
            return this.mode > 0;
        }

        public void setActiveTime(int i) {
            this.activeTime = i;
        }

        public void setCalories(int i) {
            this.calories = i;
        }

        public void setDistance(int i) {
            this.distance = i;
        }

        public void setMode(int i) {
            this.mode = i;
        }

        public void setStepCount(int i) {
            this.stepCount = i;
        }

        public String toString() {
            return "ItemBean{mode=" + this.mode + ", stepCount=" + this.stepCount + ", activeTime=" + this.activeTime + ", calories=" + this.calories + ", distance=" + this.distance + '}';
        }
    }

    public Date getDate() {
        return this.date;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public int getMinuteOffset() {
        return this.minuteOffset;
    }

    public int getPacketCount() {
        return this.packetCount;
    }

    public int getPerMinute() {
        return this.perMinute;
    }

    public int getStandCount() {
        return this.standCount;
    }

    public int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public int getTotalCal() {
        return this.totalCal;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setMinuteOffset(int i) {
        this.minuteOffset = i;
    }

    public void setPacketCount(int i) {
        this.packetCount = i;
    }

    public void setPerMinute(int i) {
        this.perMinute = i;
    }

    public void setStandCount(int i) {
        this.standCount = i;
    }

    public void setTotalActiveTime(int i) {
        this.totalActiveTime = i;
    }

    public void setTotalCal(int i) {
        this.totalCal = i;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public String toString() {
        return "TGStepData{date=" + new SimpleDateFormat("yyyy-MM-dd").format(this.date) + ", minuteOffset=" + this.minuteOffset + ", perMinute=" + this.perMinute + ", itemCount=" + this.itemCount + ", packetCount=" + this.packetCount + ", totalSteps=" + this.totalSteps + ", totalCal=" + this.totalCal + ", totalDistance=" + this.totalDistance + ", totalActiveTime=" + this.totalActiveTime + ", items=" + this.items + ", standCount=" + this.standCount + '}';
    }
}
