package com.coveiot.coveaccess.energyscore.model;

import java.util.List;
/* loaded from: classes8.dex */
public class FitnessData {
    public String activityBaseUnit;
    public String activityType;
    public String calories;
    public String createdDate;
    public String date;
    public String meters;
    public TimeLog timeLog;
    public String value;

    /* loaded from: classes8.dex */
    public static class TimeLog {
        public List<Log> logs;

        /* loaded from: classes8.dex */
        public static class Log {
            public String endTime;
            public int intervalCalories;
            public String startTime;

            public String getEndTime() {
                return this.endTime;
            }

            public int getIntervalCalories() {
                return this.intervalCalories;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setIntervalCalories(int i) {
                this.intervalCalories = i;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public List<Log> getLogs() {
            return this.logs;
        }

        public void setLogs(List<Log> list) {
            this.logs = list;
        }
    }

    public String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public String getCalories() {
        return this.calories;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public String getDate() {
        return this.date;
    }

    public String getMeters() {
        return this.meters;
    }

    public TimeLog getTimeLog() {
        return this.timeLog;
    }

    public String getValue() {
        return this.value;
    }

    public void setActivityBaseUnit(String str) {
        this.activityBaseUnit = str;
    }

    public void setActivityType(String str) {
        this.activityType = str;
    }

    public void setCalories(String str) {
        this.calories = str;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMeters(String str) {
        this.meters = str;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.timeLog = timeLog;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
