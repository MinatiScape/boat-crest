package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSportRealTimeData {
    private Date date;
    private boolean haveMoreData;
    @Nullable
    private List<ItemBean> items;
    private int offset;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int heartRate;
        private int pace;
        private int speed;
        private int swolf;

        public int getHeartRate() {
            return this.heartRate;
        }

        public int getPace() {
            return this.pace;
        }

        public int getSpeed() {
            return this.speed;
        }

        public int getSwolf() {
            return this.swolf;
        }

        public void setHeartRate(int i) {
            this.heartRate = i;
        }

        public void setPace(int i) {
            this.pace = i;
        }

        public void setSpeed(int i) {
            this.speed = i;
        }

        public void setSwolf(int i) {
            this.swolf = i;
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
