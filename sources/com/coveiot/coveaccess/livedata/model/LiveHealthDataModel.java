package com.coveiot.coveaccess.livedata.model;

import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class LiveHealthDataModel {
    @SerializedName(DeviceKey.HRV)

    /* renamed from: a  reason: collision with root package name */
    private Integer f6636a;
    @SerializedName("hrvRecordedDate")
    private String b;
    public BodyTemperatureBean bodyTemperature;
    public int bpDiastolic;
    public String bpRecordedDate;
    public int bpSystolic;
    public int hr;
    public String hrRecordedDate;
    public LocationBean location;
    public SleepDetailsBean sleepDetails;
    public String sleepRecordedDate;
    public double spo2;
    public String spo2BaseUnit;
    public String spo2RecordedDate;
    public String stepsRecordedDate;
    public int stressLevel;
    public String stressRecordedDate;
    public SurfaceTemperatureBean surfaceTemperature;
    public String tagId;
    public double totalCalorie;
    public int totalDistance;
    public int totalSleep;
    public int totalSteps;

    /* loaded from: classes8.dex */
    public static class BodyTemperatureBean {
        public String baseUnit;
        public String recordedDate;
        public double value;

        public String getBaseUnit() {
            return this.baseUnit;
        }

        public String getRecordedDate() {
            return this.recordedDate;
        }

        public double getValue() {
            return this.value;
        }

        public void setBaseUnit(String str) {
            this.baseUnit = str;
        }

        public void setRecordedDate(String str) {
            this.recordedDate = str;
        }

        public void setValue(double d) {
            this.value = d;
        }
    }

    /* loaded from: classes8.dex */
    public static class LocationBean {
        public double latitude;
        public double longitude;

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }
    }

    /* loaded from: classes8.dex */
    public static class SleepDetailsBean {
        public int awake;
        public int deepSleep;
        public String endDate;
        public int lightSleep;
        public String startDate;

        public int getAwake() {
            return this.awake;
        }

        public int getDeepSleep() {
            return this.deepSleep;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public int getLightSleep() {
            return this.lightSleep;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setAwake(int i) {
            this.awake = i;
        }

        public void setDeepSleep(int i) {
            this.deepSleep = i;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public void setLightSleep(int i) {
            this.lightSleep = i;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SurfaceTemperatureBean {
        public String baseUnit;
        public String recordedDate;
        public double value;

        public String getBaseUnit() {
            return this.baseUnit;
        }

        public String getRecordedDate() {
            return this.recordedDate;
        }

        public double getValue() {
            return this.value;
        }

        public void setBaseUnit(String str) {
            this.baseUnit = str;
        }

        public void setRecordedDate(String str) {
            this.recordedDate = str;
        }

        public void setValue(double d) {
            this.value = d;
        }
    }

    public BodyTemperatureBean getBodyTemperature() {
        return this.bodyTemperature;
    }

    public int getBpDiastolic() {
        return this.bpDiastolic;
    }

    public String getBpRecordedDate() {
        return this.bpRecordedDate;
    }

    public int getBpSystolic() {
        return this.bpSystolic;
    }

    public int getHr() {
        return this.hr;
    }

    public String getHrRecordedDate() {
        return this.hrRecordedDate;
    }

    public Integer getHrv() {
        return this.f6636a;
    }

    public String getHrvRecordedDate() {
        return this.b;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public SleepDetailsBean getSleepDetails() {
        return this.sleepDetails;
    }

    public String getSleepRecordedDate() {
        return this.sleepRecordedDate;
    }

    public double getSpo2() {
        return this.spo2;
    }

    public String getSpo2BaseUnit() {
        return this.spo2BaseUnit;
    }

    public String getSpo2RecordedDate() {
        return this.spo2RecordedDate;
    }

    public String getStepsRecordedDate() {
        return this.stepsRecordedDate;
    }

    public int getStressLevel() {
        return this.stressLevel;
    }

    public String getStressRecordedDate() {
        return this.stressRecordedDate;
    }

    public SurfaceTemperatureBean getSurfaceTemperature() {
        return this.surfaceTemperature;
    }

    public String getTagId() {
        return this.tagId;
    }

    public double getTotalCalorie() {
        return this.totalCalorie;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public int getTotalSleep() {
        return this.totalSleep;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setBodyTemperature(BodyTemperatureBean bodyTemperatureBean) {
        this.bodyTemperature = bodyTemperatureBean;
    }

    public void setBpDiastolic(int i) {
        this.bpDiastolic = i;
    }

    public void setBpRecordedDate(String str) {
        this.bpRecordedDate = str;
    }

    public void setBpSystolic(int i) {
        this.bpSystolic = i;
    }

    public void setHr(int i) {
        this.hr = i;
    }

    public void setHrRecordedDate(String str) {
        this.hrRecordedDate = str;
    }

    public void setHrv(Integer num) {
        this.f6636a = num;
    }

    public void setHrvRecordedDate(String str) {
        this.b = str;
    }

    public void setLocation(LocationBean locationBean) {
        this.location = locationBean;
    }

    public void setSleepDetails(SleepDetailsBean sleepDetailsBean) {
        this.sleepDetails = sleepDetailsBean;
    }

    public void setSleepRecordedDate(String str) {
        this.sleepRecordedDate = str;
    }

    public void setSpo2(double d) {
        this.spo2 = d;
    }

    public void setSpo2BaseUnit(String str) {
        this.spo2BaseUnit = str;
    }

    public void setSpo2RecordedDate(String str) {
        this.spo2RecordedDate = str;
    }

    public void setStepsRecordedDate(String str) {
        this.stepsRecordedDate = str;
    }

    public void setStressLevel(int i) {
        this.stressLevel = i;
    }

    public void setStressRecordedDate(String str) {
        this.stressRecordedDate = str;
    }

    public void setSurfaceTemperature(SurfaceTemperatureBean surfaceTemperatureBean) {
        this.surfaceTemperature = surfaceTemperatureBean;
    }

    public void setTagId(String str) {
        this.tagId = str;
    }

    public void setTotalCalorie(double d) {
        this.totalCalorie = d;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public void setTotalSleep(int i) {
        this.totalSleep = i;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }
}
