package com.coveiot.coveaccess.fitness.model;

import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public final class ActivityDataModel {
    private ActivityBaseUnit activityBaseUnit;
    private ActivityType activityType;
    private String awake;
    private String calories;
    private String createdDate;
    private String date;
    private String deepSleep;
    private String lightSleep;
    private String meters;
    private TimeLogBean timeLog;
    private String userDeviceId;
    private String value;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            private String endTime;
            private String intervalCalories;
            private String intervalValue;
            private OtherDataBean otherData;
            private String startTime;

            /* loaded from: classes8.dex */
            public static class OtherDataBean {
                @SerializedName("awake")
                private String awake;
                private List<Integer> codedValues;
                @SerializedName("deepSleep")
                private String deepSleep;
                @SerializedName("lightSleep")
                private String lightSleep;

                public String getAwake() {
                    return this.awake;
                }

                public List<Integer> getCodedValues() {
                    return this.codedValues;
                }

                public String getDeepSleep() {
                    return this.deepSleep;
                }

                public String getLightSleep() {
                    return this.lightSleep;
                }

                public void setAwake(String str) {
                    this.awake = str;
                }

                public void setCodedValues(List<Integer> list) {
                    this.codedValues = list;
                }

                public void setDeepSleep(String str) {
                    this.deepSleep = str;
                }

                public void setLightSleep(String str) {
                    this.lightSleep = str;
                }
            }

            public String getEndTime() {
                return this.endTime;
            }

            public String getIntervalCalories() {
                return this.intervalCalories;
            }

            public String getIntervalValue() {
                return this.intervalValue;
            }

            public OtherDataBean getOtherData() {
                return this.otherData;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setIntervalCalories(String str) {
                this.intervalCalories = str;
            }

            public void setIntervalValue(String str) {
                this.intervalValue = str;
            }

            public void setOtherData(OtherDataBean otherDataBean) {
                this.otherData = otherDataBean;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public TimeLogBean(List<LogsBean> list) {
            this.logs = list;
        }

        public List<LogsBean> getLogs() {
            return this.logs;
        }

        public void setLogs(List<LogsBean> list) {
            this.logs = list;
        }
    }

    public ActivityBaseUnit getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    public ActivityType getActivityType() {
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

    public String getDeepSleep() {
        if (getTimeLog() != null && getTimeLog().getLogs() != null && getTimeLog().getLogs().size() > 0) {
            int i = 0;
            for (TimeLogBean.LogsBean logsBean : getTimeLog().getLogs()) {
                if (logsBean.otherData.deepSleep != null) {
                    i += Integer.parseInt(logsBean.otherData.deepSleep);
                }
            }
            this.deepSleep = String.valueOf(i);
        }
        return this.deepSleep;
    }

    public String getLightSleep() {
        if (getTimeLog() != null && getTimeLog().getLogs() != null && getTimeLog().getLogs().size() > 0) {
            int i = 0;
            for (TimeLogBean.LogsBean logsBean : getTimeLog().getLogs()) {
                if (logsBean.otherData.lightSleep != null) {
                    i += Integer.parseInt(logsBean.otherData.lightSleep);
                }
            }
            this.lightSleep = String.valueOf(i);
        }
        return this.lightSleep;
    }

    public String getMeters() {
        return this.meters;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public String getUserDeviceId() {
        return this.userDeviceId;
    }

    public String getValue() {
        return this.value;
    }

    public void setActivityBaseUnit(ActivityBaseUnit activityBaseUnit) {
        this.activityBaseUnit = activityBaseUnit;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
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

    public void setDeepSleep(String str) {
        this.deepSleep = str;
    }

    public void setMeters(String str) {
        this.meters = str;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setUserDeviceId(String str) {
        this.userDeviceId = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
