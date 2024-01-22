package com.ido.ble.data.manage.database;

import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
public class HealthSwimming {
    public static final int TYPE_INDOOR = 1;
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_OPEN_POOL = 2;
    public int averageSWOLF;
    public int avg_frequency;
    public int avg_speed;
    public int calories;
    public int confirmDistance;
    private long dId;
    private Long dataId;
    private Date date;
    public int day;
    public int distance;
    public int duration;
    public int hour;
    public List<HealthSwimmingDetail> items;
    public int minute;
    public int month;
    public int poolDistance;
    public int second;
    public int swimmingPosture;
    public int totalStrokesNumber;
    public int trips;
    public int type;
    public int year;

    public HealthSwimming() {
    }

    public HealthSwimming(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, List<HealthSwimmingDetail> list, Long l, long j, Date date) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
        this.type = i7;
        this.calories = i8;
        this.distance = i9;
        this.duration = i10;
        this.trips = i11;
        this.averageSWOLF = i12;
        this.totalStrokesNumber = i13;
        this.swimmingPosture = i14;
        this.poolDistance = i15;
        this.confirmDistance = i16;
        this.items = list;
        this.dataId = l;
        this.dId = j;
        this.date = date;
    }

    public int getAverageSWOLF() {
        return this.averageSWOLF;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getConfirmDistance() {
        return this.confirmDistance;
    }

    public long getDId() {
        return this.dId;
    }

    public Long getDataId() {
        return this.dataId;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDay() {
        return this.day;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getHour() {
        return this.hour;
    }

    public List<HealthSwimmingDetail> getItems() {
        return this.items;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getPoolDistance() {
        return this.poolDistance;
    }

    public int getSecond() {
        return this.second;
    }

    public int getSwimmingPosture() {
        return this.swimmingPosture;
    }

    public int getTotalStrokesNumber() {
        return this.totalStrokesNumber;
    }

    public int getTrips() {
        return this.trips;
    }

    public int getType() {
        return this.type;
    }

    public int getYear() {
        return this.year;
    }

    public void setAverageSWOLF(int i) {
        this.averageSWOLF = i;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public void setConfirmDistance(int i) {
        this.confirmDistance = i;
    }

    public void setDId(long j) {
        this.dId = j;
    }

    public void setDataId(Long l) {
        this.dataId = l;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setItems(List<HealthSwimmingDetail> list) {
        this.items = list;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setPoolDistance(int i) {
        this.poolDistance = i;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setSwimmingPosture(int i) {
        this.swimmingPosture = i;
    }

    public void setTotalStrokesNumber(int i) {
        this.totalStrokesNumber = i;
    }

    public void setTrips(int i) {
        this.trips = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthSwimming{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", type=" + this.type + ", calories=" + this.calories + ", distance=" + this.distance + ", duration=" + this.duration + ", trips=" + this.trips + ", averageSWOLF=" + this.averageSWOLF + ", totalStrokesNumber=" + this.totalStrokesNumber + ", swimmingPosture=" + this.swimmingPosture + ", poolDistance=" + this.poolDistance + ", confirmDistance=" + this.confirmDistance + ", items=" + this.items + ", dataId=" + this.dataId + ", dId=" + this.dId + ", date=" + this.date + ", avg_speed=" + this.avg_speed + ", avg_frequency=" + this.avg_frequency + '}';
    }
}
