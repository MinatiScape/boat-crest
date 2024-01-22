package com.ido.ble.gps.database;
/* loaded from: classes11.dex */
public class HealthGps {
    private long dId;
    private Integer data_interval;
    private Long date;
    private Integer day;
    private Long healthGpsId;
    private Integer hour;
    private Integer minute;
    private Integer month;
    private Integer second;
    private Integer year;

    public HealthGps() {
    }

    public HealthGps(Long l, long j, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Long l2) {
        this.healthGpsId = l;
        this.dId = j;
        this.year = num;
        this.month = num2;
        this.day = num3;
        this.hour = num4;
        this.minute = num5;
        this.second = num6;
        this.data_interval = num7;
        this.date = l2;
    }

    public long getDId() {
        return this.dId;
    }

    public Integer getData_interval() {
        return this.data_interval;
    }

    public Long getDate() {
        return this.date;
    }

    public Integer getDay() {
        return this.day;
    }

    public Long getHealthGpsId() {
        return this.healthGpsId;
    }

    public Integer getHour() {
        return this.hour;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public Integer getMonth() {
        return this.month;
    }

    public Integer getSecond() {
        return this.second;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setDId(long j) {
        this.dId = j;
    }

    public void setData_interval(Integer num) {
        this.data_interval = num;
    }

    public void setDate(Long l) {
        this.date = l;
    }

    public void setDay(Integer num) {
        this.day = num;
    }

    public void setHealthGpsId(Long l) {
        this.healthGpsId = l;
    }

    public void setHour(Integer num) {
        this.hour = num;
    }

    public void setMinute(Integer num) {
        this.minute = num;
    }

    public void setMonth(Integer num) {
        this.month = num;
    }

    public void setSecond(Integer num) {
        this.second = num;
    }

    public void setYear(Integer num) {
        this.year = num;
    }
}
