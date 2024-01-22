package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthSleep {
    public int awakeCount;
    private long dId;
    private Date date;
    public int day;
    public int deepSleepCount;
    public int deepSleepMinutes;
    public int lightSleepCount;
    public int lightSleepMinutes;
    public int month;
    private Long sleepDataId;
    public int sleepEndedTimeH;
    public int sleepEndedTimeM;
    public int sleepScore;
    public int totalSleepMinutes;
    public int year;

    public HealthSleep() {
    }

    public HealthSleep(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, Date date) {
        this.sleepDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.sleepEndedTimeH = i4;
        this.sleepEndedTimeM = i5;
        this.totalSleepMinutes = i6;
        this.lightSleepCount = i7;
        this.deepSleepCount = i8;
        this.awakeCount = i9;
        this.lightSleepMinutes = i10;
        this.deepSleepMinutes = i11;
        this.sleepScore = i12;
        this.date = date;
    }

    public int getAwakeCount() {
        return this.awakeCount;
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

    public int getDeepSleepCount() {
        return this.deepSleepCount;
    }

    public int getDeepSleepMinutes() {
        return this.deepSleepMinutes;
    }

    public int getLightSleepCount() {
        return this.lightSleepCount;
    }

    public int getLightSleepMinutes() {
        return this.lightSleepMinutes;
    }

    public int getMonth() {
        return this.month;
    }

    public Long getSleepDataId() {
        return this.sleepDataId;
    }

    public int getSleepEndedTimeH() {
        return this.sleepEndedTimeH;
    }

    public int getSleepEndedTimeM() {
        return this.sleepEndedTimeM;
    }

    public int getSleepScore() {
        return this.sleepScore;
    }

    public int getTotalSleepMinutes() {
        return this.totalSleepMinutes;
    }

    public int getYear() {
        return this.year;
    }

    public void setAwakeCount(int i) {
        this.awakeCount = i;
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

    public void setDeepSleepCount(int i) {
        this.deepSleepCount = i;
    }

    public void setDeepSleepMinutes(int i) {
        this.deepSleepMinutes = i;
    }

    public void setLightSleepCount(int i) {
        this.lightSleepCount = i;
    }

    public void setLightSleepMinutes(int i) {
        this.lightSleepMinutes = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSleepDataId(Long l) {
        this.sleepDataId = l;
    }

    public void setSleepEndedTimeH(int i) {
        this.sleepEndedTimeH = i;
    }

    public void setSleepEndedTimeM(int i) {
        this.sleepEndedTimeM = i;
    }

    public void setSleepScore(int i) {
        this.sleepScore = i;
    }

    public void setTotalSleepMinutes(int i) {
        this.totalSleepMinutes = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthSleep{dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", sleepEndedTimeH=" + this.sleepEndedTimeH + ", sleepEndedTimeM=" + this.sleepEndedTimeM + ", totalSleepMinutes=" + this.totalSleepMinutes + ", lightSleepCount=" + this.lightSleepCount + ", deepSleepCount=" + this.deepSleepCount + ", awakeCount=" + this.awakeCount + ", lightSleepMinutes=" + this.lightSleepMinutes + ", deepSleepMinutes=" + this.deepSleepMinutes + ", date=" + this.date + '}';
    }
}
