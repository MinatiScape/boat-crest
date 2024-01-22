package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthHeartRate {
    private int Range1;
    private int Range2;
    private int Range3;
    private int Range4;
    private int Range5;
    public int UserMaxHr;
    public int aerobic_mins;
    public int aerobic_threshold;
    public int anaerobicMins;
    public int anaerobicThreshold;
    public int burn_fat_mins;
    public int burn_fat_threshold;
    private long dId;
    private Date date;
    public int day;
    public int limit_mins;
    public int limit_threshold;
    public int month;
    private Long rateDataId;
    public int silentHeart;
    public int startTime;
    public int warmUpMins;
    public int warmUpThreshold;
    public int year;

    public HealthHeartRate() {
    }

    public HealthHeartRate(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, Date date) {
        this.rateDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.startTime = i4;
        this.silentHeart = i5;
        this.warmUpThreshold = i6;
        this.burn_fat_threshold = i7;
        this.aerobic_threshold = i8;
        this.anaerobicThreshold = i9;
        this.limit_threshold = i10;
        this.warmUpMins = i11;
        this.burn_fat_mins = i12;
        this.aerobic_mins = i13;
        this.anaerobicMins = i14;
        this.limit_mins = i15;
        this.UserMaxHr = i16;
        this.Range1 = i17;
        this.Range2 = i18;
        this.Range3 = i19;
        this.Range4 = i20;
        this.Range5 = i21;
        this.date = date;
    }

    public int getAerobic_mins() {
        return this.aerobic_mins;
    }

    public int getAerobic_threshold() {
        return this.aerobic_threshold;
    }

    public int getAnaerobicMins() {
        return this.anaerobicMins;
    }

    public int getAnaerobicThreshold() {
        return this.anaerobicThreshold;
    }

    public int getBurn_fat_mins() {
        return this.burn_fat_mins;
    }

    public int getBurn_fat_threshold() {
        return this.burn_fat_threshold;
    }

    public long getDId() {
        return this.dId;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDay() {
        return this.day;
    }

    public int getLimit_mins() {
        return this.limit_mins;
    }

    public int getLimit_threshold() {
        return this.limit_threshold;
    }

    public int getMonth() {
        return this.month;
    }

    public int getRange1() {
        return this.Range1;
    }

    public int getRange2() {
        return this.Range2;
    }

    public int getRange3() {
        return this.Range3;
    }

    public int getRange4() {
        return this.Range4;
    }

    public int getRange5() {
        return this.Range5;
    }

    public Long getRateDataId() {
        return this.rateDataId;
    }

    public int getSilentHeart() {
        return this.silentHeart;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getUserMaxHr() {
        return this.UserMaxHr;
    }

    public int getWarmUpMins() {
        return this.warmUpMins;
    }

    public int getWarmUpThreshold() {
        return this.warmUpThreshold;
    }

    public int getYear() {
        return this.year;
    }

    public void setAerobic_mins(int i) {
        this.aerobic_mins = i;
    }

    public void setAerobic_threshold(int i) {
        this.aerobic_threshold = i;
    }

    public void setAnaerobicMins(int i) {
        this.anaerobicMins = i;
    }

    public void setAnaerobicThreshold(int i) {
        this.anaerobicThreshold = i;
    }

    public void setBurn_fat_mins(int i) {
        this.burn_fat_mins = i;
    }

    public void setBurn_fat_threshold(int i) {
        this.burn_fat_threshold = i;
    }

    public void setDId(long j) {
        this.dId = j;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setLimit_mins(int i) {
        this.limit_mins = i;
    }

    public void setLimit_threshold(int i) {
        this.limit_threshold = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setRange1(int i) {
        this.Range1 = i;
    }

    public void setRange2(int i) {
        this.Range2 = i;
    }

    public void setRange3(int i) {
        this.Range3 = i;
    }

    public void setRange4(int i) {
        this.Range4 = i;
    }

    public void setRange5(int i) {
        this.Range5 = i;
    }

    public void setRateDataId(Long l) {
        this.rateDataId = l;
    }

    public void setSilentHeart(int i) {
        this.silentHeart = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setUserMaxHr(int i) {
        this.UserMaxHr = i;
    }

    public void setWarmUpMins(int i) {
        this.warmUpMins = i;
    }

    public void setWarmUpThreshold(int i) {
        this.warmUpThreshold = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthHeartRate{rateDataId=" + this.rateDataId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", startTime=" + this.startTime + ", silentHeart=" + this.silentHeart + ", warmUpThreshold=" + this.warmUpThreshold + ", burn_fat_threshold=" + this.burn_fat_threshold + ", aerobic_threshold=" + this.aerobic_threshold + ", anaerobicThreshold=" + this.anaerobicThreshold + ", limit_threshold=" + this.limit_threshold + ", warmUpMins=" + this.warmUpMins + ", burn_fat_mins=" + this.burn_fat_mins + ", aerobic_mins=" + this.aerobic_mins + ", anaerobicMins=" + this.anaerobicMins + ", limit_mins=" + this.limit_mins + ", UserMaxHr=" + this.UserMaxHr + ", Range1=" + this.Range1 + ", Range2=" + this.Range2 + ", Range3=" + this.Range3 + ", Range4=" + this.Range4 + ", Range5=" + this.Range5 + ", date=" + this.date + '}';
    }
}
