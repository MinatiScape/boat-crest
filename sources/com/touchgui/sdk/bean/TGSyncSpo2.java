package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSyncSpo2 {
    private Date date;
    private boolean haveMoreData;
    @Nullable
    private List<ItemBean> items;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int heartRate;
        private int timeSeconds;
        private int utcSeconds;
        private int value;

        public int getHeartRate() {
            return this.heartRate;
        }

        public int getTimeSeconds() {
            return this.timeSeconds;
        }

        @Deprecated
        public int getUtcSeconds() {
            return this.utcSeconds;
        }

        public int getValue() {
            return this.value;
        }

        public void setHeartRate(int i) {
            this.heartRate = i;
        }

        public void setTimeSeconds(int i) {
            this.timeSeconds = i;
        }

        public void setUtcSeconds(int i) {
            this.utcSeconds = i;
        }

        public void setValue(int i) {
            this.value = i;
        }

        public String toString() {
            return "ItemBean{timeSeconds=" + this.timeSeconds + ", value=" + this.value + ", heartRate=" + this.heartRate + '}';
        }
    }

    public Date getDate() {
        return this.date;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
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

    public String toString() {
        return "SyncSpo2{, haveMoreData=" + this.haveMoreData + ", date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(this.date) + ", items=" + this.items + '}';
    }
}
