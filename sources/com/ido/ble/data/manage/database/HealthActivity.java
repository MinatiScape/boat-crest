package com.ido.ble.data.manage.database;

import android.text.TextUtils;
import com.ido.ble.common.k;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
public class HealthActivity {
    private Long activityId;
    public int aerobic_mins;
    public int anaerobicMins;
    public int avg_hr_value;
    public int burn_fat_mins;
    public int calories;
    private long dId;
    private Date date;
    public int day;
    public int distance;
    public int durations;
    public int hour;
    @Deprecated
    public int hr_data_interval_minute;
    private int[] hr_data_vlaue;
    private String hr_data_vlaue_json;
    private List<Item> items;
    private String items_json;
    public int limit_mins;
    public int max_hr_value;
    public int min_hr_value;
    public int minute;
    public int month;
    public int range1;
    public int range2;
    public int range3;
    public int range4;
    public int range5;
    public int second;
    public int step;
    public int type;
    public int warmUpMins;
    public int year;

    /* loaded from: classes11.dex */
    public static class Item {
        public int calories;
        public int distance;
        public int steps;

        public String toString() {
            return "Item{steps=" + this.steps + ", calories=" + this.calories + ", distance=" + this.distance + '}';
        }
    }

    public HealthActivity() {
    }

    public HealthActivity(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, String str, String str2, Date date) {
        this.activityId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
        this.hr_data_interval_minute = i7;
        this.type = i8;
        this.step = i9;
        this.durations = i10;
        this.calories = i11;
        this.distance = i12;
        this.avg_hr_value = i13;
        this.max_hr_value = i14;
        this.min_hr_value = i15;
        this.warmUpMins = i16;
        this.burn_fat_mins = i17;
        this.aerobic_mins = i18;
        this.anaerobicMins = i19;
        this.limit_mins = i20;
        this.range1 = i21;
        this.range2 = i22;
        this.range3 = i23;
        this.range4 = i24;
        this.range5 = i25;
        this.hr_data_vlaue_json = str;
        this.items_json = str2;
        this.date = date;
    }

    public Long getActivityId() {
        return this.activityId;
    }

    public int getAerobic_mins() {
        if (this.aerobic_mins == 0) {
            this.aerobic_mins = this.range3;
        }
        return this.aerobic_mins;
    }

    public int getAnaerobicMins() {
        if (this.anaerobicMins == 0) {
            this.anaerobicMins = this.range4;
        }
        return this.anaerobicMins;
    }

    public int getAvg_hr_value() {
        return this.avg_hr_value;
    }

    public int getBurn_fat_mins() {
        if (this.burn_fat_mins == 0) {
            this.burn_fat_mins = this.range2;
        }
        return this.burn_fat_mins;
    }

    public int getCalories() {
        return this.calories;
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

    public int getDistance() {
        return this.distance;
    }

    public int getDurations() {
        return this.durations;
    }

    public int getHour() {
        return this.hour;
    }

    public int getHr_data_interval_minute() {
        return this.hr_data_interval_minute;
    }

    public int[] getHr_data_vlaue() {
        return this.hr_data_vlaue;
    }

    public String getHr_data_vlaue_json() {
        return this.hr_data_vlaue_json;
    }

    public List<Item> getItems() {
        if (this.items == null && !TextUtils.isEmpty(this.items_json)) {
            this.items = k.a(this.items_json, Item[].class);
        }
        return this.items;
    }

    public String getItems_json() {
        return this.items_json;
    }

    public int getLimit_mins() {
        if (this.limit_mins == 0) {
            this.limit_mins = this.range5;
        }
        return this.limit_mins;
    }

    public int getMax_hr_value() {
        return this.max_hr_value;
    }

    public int getMin_hr_value() {
        return this.min_hr_value;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getRange1() {
        return this.range1;
    }

    public int getRange2() {
        return this.range2;
    }

    public int getRange3() {
        return this.range3;
    }

    public int getRange4() {
        return this.range4;
    }

    public int getRange5() {
        return this.range5;
    }

    public int getSecond() {
        return this.second;
    }

    public int getStep() {
        return this.step;
    }

    public int getType() {
        return this.type;
    }

    public int getWarmUpMins() {
        if (this.warmUpMins == 0) {
            this.warmUpMins = this.range1;
        }
        return this.warmUpMins;
    }

    public int getYear() {
        return this.year;
    }

    public void setActivityId(Long l) {
        this.activityId = l;
    }

    public void setAerobic_mins(int i) {
        this.aerobic_mins = i;
    }

    public void setAnaerobicMins(int i) {
        this.anaerobicMins = i;
    }

    public void setAvg_hr_value(int i) {
        this.avg_hr_value = i;
    }

    public void setBurn_fat_mins(int i) {
        this.burn_fat_mins = i;
    }

    public void setCalories(int i) {
        this.calories = i;
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

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDurations(int i) {
        this.durations = i;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setHr_data_interval_minute(int i) {
        this.hr_data_interval_minute = i;
    }

    public void setHr_data_vlaue(int[] iArr) {
        this.hr_data_vlaue = iArr;
    }

    public void setHr_data_vlaue_json(String str) {
        this.hr_data_vlaue_json = str;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }

    public void setItems_json(String str) {
        this.items_json = str;
    }

    public void setLimit_mins(int i) {
        this.limit_mins = i;
    }

    public void setMax_hr_value(int i) {
        this.max_hr_value = i;
    }

    public void setMin_hr_value(int i) {
        this.min_hr_value = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setRange1(int i) {
        this.range1 = i;
    }

    public void setRange2(int i) {
        this.range2 = i;
    }

    public void setRange3(int i) {
        this.range3 = i;
    }

    public void setRange4(int i) {
        this.range4 = i;
    }

    public void setRange5(int i) {
        this.range5 = i;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setWarmUpMins(int i) {
        this.warmUpMins = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthActivity{activityId=" + this.activityId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", hr_data_interval_minute=" + this.hr_data_interval_minute + ", type=" + this.type + ", step=" + this.step + ", durations=" + this.durations + ", calories=" + this.calories + ", distance=" + this.distance + ", avg_hr_value=" + this.avg_hr_value + ", max_hr_value=" + this.max_hr_value + ", min_hr_value=" + this.min_hr_value + ", warmUpMins=" + this.warmUpMins + ", burn_fat_mins=" + this.burn_fat_mins + ", aerobic_mins=" + this.aerobic_mins + ", anaerobicMins=" + this.anaerobicMins + ", limit_mins=" + this.limit_mins + ", range1=" + this.range1 + ", range2=" + this.range2 + ", range3=" + this.range3 + ", range4=" + this.range4 + ", range5=" + this.range5 + ", hr_data_vlaue=" + Arrays.toString(this.hr_data_vlaue) + ", hr_data_vlaue_json='" + this.hr_data_vlaue_json + "', items=" + this.items + ", items_json='" + this.items_json + "', date=" + this.date + '}';
    }
}
