package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSleepData {
    public static final int STATUS_ACTIVITY = 5;
    public static final int STATUS_AWAKE = 1;
    public static final int STATUS_DEEP = 3;
    public static final int STATUS_LIGHT = 2;
    public static final int STATUS_REM = 4;
    private Date date;
    private int deepCount;
    private int deepMinute;
    private int endHour;
    private int endMinute;
    private int eyeMoveCount;
    private int eyeMoveMinute;
    private int itemCount;
    @Nullable
    private List<ItemBean> items;
    private int lightCount;
    private int lightMinute;
    private int packetCount;
    private int sleepMinute;
    private int sleepScore;
    private int totalMinute;
    private int wakeCount;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int duration;
        private int status;

        public int getDuration() {
            return this.duration;
        }

        public int getStatus() {
            return this.status;
        }

        public void setDuration(int i) {
            this.duration = i;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String toString() {
            return "ItemBean{status=" + this.status + ", duration=" + this.duration + '}';
        }
    }

    public Date getDate() {
        return this.date;
    }

    public int getDeepCount() {
        return this.deepCount;
    }

    public int getDeepMinute() {
        return this.deepMinute;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public int getEyeMoveCount() {
        return this.eyeMoveCount;
    }

    public int getEyeMoveMinute() {
        return this.eyeMoveMinute;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public int getLightCount() {
        return this.lightCount;
    }

    public int getLightMinute() {
        return this.lightMinute;
    }

    public int getPacketCount() {
        return this.packetCount;
    }

    public int getSleepMinute() {
        return this.sleepMinute;
    }

    public int getSleepScore() {
        return this.sleepScore;
    }

    public int getTotalMinute() {
        return this.totalMinute;
    }

    public int getWakeCount() {
        return this.wakeCount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDeepCount(int i) {
        this.deepCount = i;
    }

    public void setDeepMinute(int i) {
        this.deepMinute = i;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public void setEyeMoveCount(int i) {
        this.eyeMoveCount = i;
    }

    public void setEyeMoveMinute(int i) {
        this.eyeMoveMinute = i;
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setLightCount(int i) {
        this.lightCount = i;
    }

    public void setLightMinute(int i) {
        this.lightMinute = i;
    }

    public void setPacketCount(int i) {
        this.packetCount = i;
    }

    public void setSleepMinute(int i) {
        this.sleepMinute = i;
    }

    public void setSleepScore(int i) {
        this.sleepScore = i;
    }

    public void setTotalMinute(int i) {
        this.totalMinute = i;
    }

    public void setWakeCount(int i) {
        this.wakeCount = i;
    }

    public String toString() {
        return "SleepData{date=" + new SimpleDateFormat("yyyy-MM-dd").format(this.date) + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", totalMinute=" + this.totalMinute + ", itemCount=" + this.itemCount + ", packetCount=" + this.packetCount + ", lightCount=" + this.lightCount + ", deepCount=" + this.deepCount + ", wakeCount=" + this.wakeCount + ", lightMinute=" + this.lightMinute + ", deepMinute=" + this.deepMinute + ", sleepScore=" + this.sleepScore + ", eyeMoveCount=" + this.eyeMoveCount + ", eyeMoveMinute=" + this.eyeMoveMinute + ", items=" + this.items + '}';
    }
}
