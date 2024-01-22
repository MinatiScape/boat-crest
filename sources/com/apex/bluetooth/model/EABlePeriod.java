package com.apex.bluetooth.model;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes.dex */
public class EABlePeriod {
    public List<EABlePeriodData> dataList;

    /* loaded from: classes.dex */
    public static class EABlePeriodData {
        public int days;
        public PeriodType periodType;
        public long time_stamp;

        public int getDays() {
            return this.days;
        }

        public PeriodType getPeriodType() {
            return this.periodType;
        }

        public long getTime_stamp() {
            return this.time_stamp;
        }

        public void setDays(int i) {
            this.days = i;
        }

        public void setPeriodType(@NonNull PeriodType periodType) {
            this.periodType = periodType;
        }

        public void setTime_stamp(long j) {
            this.time_stamp = j;
        }

        public String toString() {
            return "EABlePeriodData{time_stamp=" + this.time_stamp + ", days=" + this.days + ", periodType=" + this.periodType + '}';
        }
    }

    /* loaded from: classes.dex */
    public enum PeriodType {
        safety_period_1(0),
        safety_period_2(1),
        menstrual(2),
        ovulation(3);
        
        public int value;

        PeriodType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int i) {
            this.value = i;
        }
    }

    public List<EABlePeriodData> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<EABlePeriodData> list) {
        this.dataList = list;
    }

    public String toString() {
        return "EABlePeriod{dataList=" + this.dataList + '}';
    }
}
