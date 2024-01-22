package com.ido.ble.data.manage.database;

import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
public class HealthHeartRateSecond {
    private long dId;
    private Long dataId;
    private Date date;
    public int day;
    public int five_min_avg_data;
    public List<Integer> five_min_data;
    public int five_min_max_data;
    public int five_min_min_data;
    public List<HealthHeartRateSecond_Interval> hrInterval;
    public List<HealthHeartRateHighLowItem> hr_data;
    public int hr_data_count;
    public List<HealthHeartRateSecondItem> items;
    public int month;
    public int silentHR;
    public int startTime;
    public int year;

    public HealthHeartRateSecond() {
    }

    public HealthHeartRateSecond(List<HealthHeartRateSecond_Interval> list, List<HealthHeartRateSecondItem> list2, List<HealthHeartRateHighLowItem> list3, int i, int i2, int i3, int i4, int i5, Long l, long j, Date date, int i6) {
        this.hrInterval = list;
        this.items = list2;
        this.hr_data = list3;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.startTime = i4;
        this.silentHR = i5;
        this.dataId = l;
        this.dId = j;
        this.date = date;
        this.hr_data_count = i6;
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

    public List<HealthHeartRateSecond_Interval> getHrInterval() {
        return this.hrInterval;
    }

    public List<HealthHeartRateHighLowItem> getHr_data() {
        return this.hr_data;
    }

    public int getHr_data_count() {
        return this.hr_data_count;
    }

    public List<HealthHeartRateSecondItem> getItems() {
        return this.items;
    }

    public int getMonth() {
        return this.month;
    }

    public int getSilentHR() {
        return this.silentHR;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getYear() {
        return this.year;
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

    public void setHrInterval(List<HealthHeartRateSecond_Interval> list) {
        this.hrInterval = list;
    }

    public void setHr_data(List<HealthHeartRateHighLowItem> list) {
        this.hr_data = list;
    }

    public void setHr_data_count(int i) {
        this.hr_data_count = i;
    }

    public void setItems(List<HealthHeartRateSecondItem> list) {
        this.items = list;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSilentHR(int i) {
        this.silentHR = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
