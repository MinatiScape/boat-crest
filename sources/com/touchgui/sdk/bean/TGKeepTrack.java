package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGKeepTrack {
    private Date date;
    private boolean haveMoreData;
    @Nullable
    private List<ItemBean> items;
    private int offset;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int distance;
        private int heartRate;
        private double latitude;
        private double longitude;
        private int offset;
        private int speed;
        private int steps;

        public int getDistance() {
            return this.distance;
        }

        public int getHeartRate() {
            return this.heartRate;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getSpeed() {
            return this.speed;
        }

        public int getSteps() {
            return this.steps;
        }

        public void setDistance(int i) {
            this.distance = i;
        }

        public void setHeartRate(int i) {
            this.heartRate = i;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public void setSpeed(int i) {
            this.speed = i;
        }

        public void setSteps(int i) {
            this.steps = i;
        }
    }

    public Date getDate() {
        return this.date;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public int getOffset() {
        return this.offset;
    }

    public boolean isHaveMoreData() {
        return this.haveMoreData;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHaveMoreData(boolean z) {
        this.haveMoreData = z;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setOffset(int i) {
        this.offset = i;
    }
}
