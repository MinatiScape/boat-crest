package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSyncSwim {
    private int avgArmpull;
    private int avgSwolf;
    private int calories;
    private int confirmDistance;
    private Date date;
    private int distance;
    private int duration;
    private boolean haveMoreData;
    @Nullable
    private List<ItemBean> items;
    private int maxArmpull;
    private int offset;
    private int poolDistance;
    private int posture;
    private int totalStrokes;
    private int trips;
    private int type;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int distance;
        private int duration;
        private int posture;
        private int strokesNum;
        private int swolf;

        public int getDistance() {
            return this.distance;
        }

        public int getDuration() {
            return this.duration;
        }

        public int getPosture() {
            return this.posture;
        }

        public int getStrokesNum() {
            return this.strokesNum;
        }

        public int getSwolf() {
            return this.swolf;
        }

        public void setDistance(int i) {
            this.distance = i;
        }

        public void setDuration(int i) {
            this.duration = i;
        }

        public void setPosture(int i) {
            this.posture = i;
        }

        public void setStrokesNum(int i) {
            this.strokesNum = i;
        }

        public void setSwolf(int i) {
            this.swolf = i;
        }

        public String toString() {
            return "ItemBean{swolf=" + this.swolf + ", posture=" + this.posture + ", strokesNum=" + this.strokesNum + ", duration=" + this.duration + ", distance=" + this.distance + '}';
        }
    }

    public int getAvgArmpull() {
        return this.avgArmpull;
    }

    public int getAvgSwolf() {
        return this.avgSwolf;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getConfirmDistance() {
        return this.confirmDistance;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public int getMaxArmpull() {
        return this.maxArmpull;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getPoolDistance() {
        return this.poolDistance;
    }

    public int getPosture() {
        return this.posture;
    }

    public int getTotalStrokes() {
        return this.totalStrokes;
    }

    public int getTrips() {
        return this.trips;
    }

    public int getType() {
        return this.type;
    }

    public boolean isHaveMoreData() {
        return this.haveMoreData;
    }

    public void setAvgArmpull(int i) {
        this.avgArmpull = i;
    }

    public void setAvgSwolf(int i) {
        this.avgSwolf = i;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public void setConfirmDistance(int i) {
        this.confirmDistance = i;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setHaveMoreData(boolean z) {
        this.haveMoreData = z;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setMaxArmpull(int i) {
        this.maxArmpull = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setPoolDistance(int i) {
        this.poolDistance = i;
    }

    public void setPosture(int i) {
        this.posture = i;
    }

    public void setTotalStrokes(int i) {
        this.totalStrokes = i;
    }

    public void setTrips(int i) {
        this.trips = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "SyncSwim{offset=" + this.offset + ", haveMoreData=" + this.haveMoreData + ", date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(this.date) + ", type=" + this.type + ", calories=" + this.calories + ", distance=" + this.distance + ", confirmDistance=" + this.confirmDistance + ", trips=" + this.trips + ", duration=" + this.duration + ", avgSwolf=" + this.avgSwolf + ", totalStrokes=" + this.totalStrokes + ", posture=" + this.posture + ", poolDistance=" + this.poolDistance + ", items=" + this.items + '}';
    }
}
