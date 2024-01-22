package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGHeartRateData {
    private int aerobicMinutes;
    private int aerobicThreshold;
    private int anaerobicMinutes;
    private int anaerobicThreshold;
    private int burnFatMinutes;
    private int burnFatThreshold;
    @Nullable
    private Date date;
    private int itemCount;
    @Nullable
    private List<ItemBean> items;
    private int limitMinutes;
    private int limitThreshold;
    private int minuteOffset;
    private int packetCount;
    private int silentHr;
    private int warmUpMinutes;
    private int warmUpThreshold;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int data;
        private int offset;

        public ItemBean(int i, int i2) {
            this.offset = i;
            this.data = i2;
        }

        public int getData() {
            return this.data;
        }

        public int getOffset() {
            return this.offset;
        }

        public void setData(int i) {
            this.data = i;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public String toString() {
            return "ItemBean{offset=" + this.offset + ", data=" + this.data + '}';
        }
    }

    public int getAerobicMinutes() {
        return this.aerobicMinutes;
    }

    public int getAerobicThreshold() {
        return this.aerobicThreshold;
    }

    public int getAnaerobicMinutes() {
        return this.anaerobicMinutes;
    }

    public int getAnaerobicThreshold() {
        return this.anaerobicThreshold;
    }

    public int getBurnFatMinutes() {
        return this.burnFatMinutes;
    }

    public int getBurnFatThreshold() {
        return this.burnFatThreshold;
    }

    @Nullable
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

    public int getLimitMinutes() {
        return this.limitMinutes;
    }

    public int getLimitThreshold() {
        return this.limitThreshold;
    }

    public int getMinuteOffset() {
        return this.minuteOffset;
    }

    public int getPacketCount() {
        return this.packetCount;
    }

    public int getSilentHr() {
        return this.silentHr;
    }

    public int getWarmUpMinutes() {
        return this.warmUpMinutes;
    }

    public int getWarmUpThreshold() {
        return this.warmUpThreshold;
    }

    public void setAerobicMinutes(int i) {
        this.aerobicMinutes = i;
    }

    public void setAerobicThreshold(int i) {
        this.aerobicThreshold = i;
    }

    public void setAnaerobicMinutes(int i) {
        this.anaerobicMinutes = i;
    }

    public void setAnaerobicThreshold(int i) {
        this.anaerobicThreshold = i;
    }

    public void setBurnFatMinutes(int i) {
        this.burnFatMinutes = i;
    }

    public void setBurnFatThreshold(int i) {
        this.burnFatThreshold = i;
    }

    public void setDate(@Nullable Date date) {
        this.date = date;
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setLimitMinutes(int i) {
        this.limitMinutes = i;
    }

    public void setLimitThreshold(int i) {
        this.limitThreshold = i;
    }

    public void setMinuteOffset(int i) {
        this.minuteOffset = i;
    }

    public void setPacketCount(int i) {
        this.packetCount = i;
    }

    public void setSilentHr(int i) {
        this.silentHr = i;
    }

    public void setWarmUpMinutes(int i) {
        this.warmUpMinutes = i;
    }

    public void setWarmUpThreshold(int i) {
        this.warmUpThreshold = i;
    }

    public String toString() {
        return "HeartRateData{date=" + new SimpleDateFormat("yyyy-MM-dd").format(this.date) + ", minuteOffset=" + this.minuteOffset + ", silentHr=" + this.silentHr + ", itemsCount=" + this.itemCount + ", packetsCount=" + this.packetCount + ", burnFatThreshold=" + this.burnFatThreshold + ", burnFatMinutes=" + this.burnFatMinutes + ", aerobicThreshold=" + this.aerobicThreshold + ", aerobicMinutes=" + this.aerobicMinutes + ", limitThreshold=" + this.limitThreshold + ", limitMinutes=" + this.limitMinutes + ", warmUpThreshold=" + this.warmUpThreshold + ", warmUpMinutes=" + this.warmUpMinutes + ", anaerobicThreshold=" + this.anaerobicThreshold + ", anaerobicMinutes=" + this.anaerobicMinutes + ", hearts=" + this.items + '}';
    }
}
