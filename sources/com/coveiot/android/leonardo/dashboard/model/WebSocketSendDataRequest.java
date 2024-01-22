package com.coveiot.android.leonardo.dashboard.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WebSocketSendDataRequest {
    @SerializedName("resType")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f4797a;
    @SerializedName("data")
    @Expose
    @Nullable
    private Data b;

    public WebSocketSendDataRequest(@Nullable String str, @Nullable Data data) {
        this.f4797a = str;
        this.b = data;
    }

    @Nullable
    public final Data getData() {
        return this.b;
    }

    @Nullable
    public final String getResType() {
        return this.f4797a;
    }

    public final void setData(@Nullable Data data) {
        this.b = data;
    }

    public final void setResType(@Nullable String str) {
        this.f4797a = str;
    }

    /* loaded from: classes3.dex */
    public static final class Data {
        @SerializedName("raceJoinId")
        @Expose
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        private String f4798a;
        @SerializedName("hr")
        @Expose
        @Nullable
        private Integer b;
        @SerializedName("hrRecordedDate")
        @Expose
        @Nullable
        private String c;
        @SerializedName(DeviceKey.HRV)
        @Expose
        @Nullable
        private Integer d;
        @SerializedName("hrvRecordedDate")
        @Expose
        @Nullable
        private String e;
        @SerializedName("bpSystolic")
        @Expose
        @Nullable
        private Integer f;
        @SerializedName("bpDiastolic")
        @Expose
        @Nullable
        private Integer g;
        @SerializedName("bpRecordedDate")
        @Expose
        @Nullable
        private String h;
        @SerializedName("stressLevel")
        @Expose
        @Nullable
        private Integer i;
        @SerializedName("tagId")
        @Expose
        @Nullable
        private String j;
        @SerializedName("stressRecordedDate")
        @Expose
        @Nullable
        private String k;
        @SerializedName("eventId")
        @Expose
        @Nullable
        private String l;
        @SerializedName("alarmType")
        @Expose
        @Nullable
        private String m;
        @SerializedName("totalDuration")
        @Expose
        @Nullable
        private Integer n;
        @SerializedName(FirebaseAnalytics.Param.LOCATION)
        @Expose
        @Nullable
        private Location o;
        @SerializedName("sleepDetails")
        @Expose
        @Nullable
        private SleepDetails p;
        @SerializedName("bodyTemperature")
        @Expose
        @Nullable
        private Temperature q;
        @SerializedName("surfaceTemperature")
        @Expose
        @Nullable
        private Temperature r;
        @SerializedName("currentHr")
        @Expose
        @Nullable
        private Integer s;
        @SerializedName("totalSteps")
        @Expose
        @Nullable
        private Integer t;
        @SerializedName("totalDistance")
        @Expose
        @Nullable
        private Integer u;
        @SerializedName("totalCalorie")
        @Expose
        @Nullable
        private Float v;
        @SerializedName("stepsRecordedDate")
        @Expose
        @Nullable
        private String w;
        @SerializedName("fatigueLevel")
        @Expose
        @Nullable
        private Double x;
        @SerializedName("resting")
        @Expose
        private boolean y;
        @SerializedName("totalSleep")
        @Expose
        @Nullable
        private Integer z;

        /* loaded from: classes3.dex */
        public static final class Location {
            @SerializedName("latitude")
            @Expose
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            private Double f4799a;
            @SerializedName("longitude")
            @Expose
            @Nullable
            private Double b;

            public Location(@Nullable Double d, @Nullable Double d2) {
                this.f4799a = d;
                this.b = d2;
            }

            @Nullable
            public final Double getLatitude() {
                return this.f4799a;
            }

            @Nullable
            public final Double getLongitude() {
                return this.b;
            }

            public final void setLatitude(@Nullable Double d) {
                this.f4799a = d;
            }

            public final void setLongitude(@Nullable Double d) {
                this.b = d;
            }
        }

        /* loaded from: classes3.dex */
        public static final class SleepDetails {
            @SerializedName("startDate")
            @Expose
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            private String f4800a;
            @SerializedName("endDate")
            @Expose
            @Nullable
            private String b;
            @SerializedName("deepSleep")
            @Expose
            @Nullable
            private Integer c;
            @SerializedName("lightSleep")
            @Expose
            @Nullable
            private Integer d;
            @SerializedName("awake")
            @Expose
            @Nullable
            private Integer e;

            public SleepDetails(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
                this.f4800a = str;
                this.b = str2;
                this.c = num;
                this.d = num2;
                this.e = num3;
            }

            @Nullable
            public final Integer getAwake() {
                return this.e;
            }

            @Nullable
            public final Integer getDeepSleep() {
                return this.c;
            }

            @Nullable
            public final String getEndDate() {
                return this.b;
            }

            @Nullable
            public final Integer getLightSleep() {
                return this.d;
            }

            @Nullable
            public final String getStartDate() {
                return this.f4800a;
            }

            public final void setAwake(@Nullable Integer num) {
                this.e = num;
            }

            public final void setDeepSleep(@Nullable Integer num) {
                this.c = num;
            }

            public final void setEndDate(@Nullable String str) {
                this.b = str;
            }

            public final void setLightSleep(@Nullable Integer num) {
                this.d = num;
            }

            public final void setStartDate(@Nullable String str) {
                this.f4800a = str;
            }
        }

        /* loaded from: classes3.dex */
        public static final class Temperature {
            @SerializedName("value")
            @Expose
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            private Double f4801a;
            @SerializedName("baseUnit")
            @Expose
            @Nullable
            private String b;
            @SerializedName("recordedDate")
            @Expose
            @Nullable
            private String c;

            public Temperature(@Nullable Double d, @Nullable String str, @Nullable String str2) {
                this.f4801a = d;
                this.b = str;
                this.c = str2;
            }

            @Nullable
            public final String getBaseUnit() {
                return this.b;
            }

            @Nullable
            public final String getRecordedDate() {
                return this.c;
            }

            @Nullable
            public final Double getValue() {
                return this.f4801a;
            }

            public final void setBaseUnit(@Nullable String str) {
                this.b = str;
            }

            public final void setRecordedDate(@Nullable String str) {
                this.c = str;
            }

            public final void setValue(@Nullable Double d) {
                this.f4801a = d;
            }
        }

        public Data(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Location location, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Float f, boolean z) {
            this.f4798a = str;
            this.l = str2;
            this.n = num;
            this.o = location;
            this.s = num2;
            this.t = num3;
            this.u = num4;
            this.v = f;
            this.y = z;
        }

        @Nullable
        public final String getAlarmType() {
            return this.m;
        }

        @Nullable
        public final Temperature getBodyTemperature() {
            return this.q;
        }

        @Nullable
        public final Integer getBpDiastolic() {
            return this.g;
        }

        @Nullable
        public final String getBpRecordedDate() {
            return this.h;
        }

        @Nullable
        public final Integer getBpSystolic() {
            return this.f;
        }

        @Nullable
        public final Integer getCurrentHr() {
            return this.s;
        }

        @Nullable
        public final String getEventId() {
            return this.l;
        }

        @Nullable
        public final Double getFatigueLevel() {
            return this.x;
        }

        @Nullable
        public final Integer getHr() {
            return this.b;
        }

        @Nullable
        public final String getHrRecordedDate() {
            return this.c;
        }

        @Nullable
        public final Integer getHrv() {
            return this.d;
        }

        @Nullable
        public final String getHrvRecordedDate() {
            return this.e;
        }

        @Nullable
        public final Location getLocation() {
            return this.o;
        }

        @Nullable
        public final String getRaceJoinId() {
            return this.f4798a;
        }

        public final boolean getResting() {
            return this.y;
        }

        @Nullable
        public final SleepDetails getSleepDetails() {
            return this.p;
        }

        @Nullable
        public final String getStepsRecordedDate() {
            return this.w;
        }

        @Nullable
        public final Integer getStressLevel() {
            return this.i;
        }

        @Nullable
        public final String getStressRecordedDate() {
            return this.k;
        }

        @Nullable
        public final Temperature getSurfaceTemperature() {
            return this.r;
        }

        @Nullable
        public final String getTagId() {
            return this.j;
        }

        @Nullable
        public final Float getTotalCalorie() {
            return this.v;
        }

        @Nullable
        public final Integer getTotalDistance() {
            return this.u;
        }

        @Nullable
        public final Integer getTotalDuration() {
            return this.n;
        }

        @Nullable
        public final Integer getTotalSleep() {
            return this.z;
        }

        @Nullable
        public final Integer getTotalSteps() {
            return this.t;
        }

        public final void setAlarmType(@Nullable String str) {
            this.m = str;
        }

        public final void setBodyTemperature(@Nullable Temperature temperature) {
            this.q = temperature;
        }

        public final void setBpDiastolic(@Nullable Integer num) {
            this.g = num;
        }

        public final void setBpRecordedDate(@Nullable String str) {
            this.h = str;
        }

        public final void setBpSystolic(@Nullable Integer num) {
            this.f = num;
        }

        public final void setCurrentHr(@Nullable Integer num) {
            this.s = num;
        }

        public final void setEventId(@Nullable String str) {
            this.l = str;
        }

        public final void setFatigueLevel(@Nullable Double d) {
            this.x = d;
        }

        public final void setHr(@Nullable Integer num) {
            this.b = num;
        }

        public final void setHrRecordedDate(@Nullable String str) {
            this.c = str;
        }

        public final void setHrv(@Nullable Integer num) {
            this.d = num;
        }

        public final void setHrvRecordedDate(@Nullable String str) {
            this.e = str;
        }

        public final void setLocation(@Nullable Location location) {
            this.o = location;
        }

        public final void setRaceJoinId(@Nullable String str) {
            this.f4798a = str;
        }

        public final void setResting(boolean z) {
            this.y = z;
        }

        public final void setSleepDetails(@Nullable SleepDetails sleepDetails) {
            this.p = sleepDetails;
        }

        public final void setStepsRecordedDate(@Nullable String str) {
            this.w = str;
        }

        public final void setStressLevel(@Nullable Integer num) {
            this.i = num;
        }

        public final void setStressRecordedDate(@Nullable String str) {
            this.k = str;
        }

        public final void setSurfaceTemperature(@Nullable Temperature temperature) {
            this.r = temperature;
        }

        public final void setTagId(@Nullable String str) {
            this.j = str;
        }

        public final void setTotalCalorie(@Nullable Float f) {
            this.v = f;
        }

        public final void setTotalDistance(@Nullable Integer num) {
            this.u = num;
        }

        public final void setTotalDuration(@Nullable Integer num) {
            this.n = num;
        }

        public final void setTotalSleep(@Nullable Integer num) {
            this.z = num;
        }

        public final void setTotalSteps(@Nullable Integer num) {
            this.t = num;
        }

        public /* synthetic */ Data(String str, Integer num, String str2, Integer num2, Integer num3, Float f, String str3, Integer num4, Integer num5, Integer num6, String str4, Integer num7, String str5, SleepDetails sleepDetails, String str6, Integer num8, Temperature temperature, Temperature temperature2, double d, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, num, str2, num2, num3, f, str3, num4, num5, num6, str4, num7, str5, sleepDetails, str6, num8, (i & 65536) != 0 ? null : temperature, (i & 131072) != 0 ? null : temperature2, (i & 262144) != 0 ? 0.0d : d);
        }

        public Data(@Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable Integer num2, @Nullable Integer num3, @Nullable Float f, @Nullable String str3, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6, @Nullable String str4, @Nullable Integer num7, @Nullable String str5, @Nullable SleepDetails sleepDetails, @Nullable String str6, @Nullable Integer num8, @Nullable Temperature temperature, @Nullable Temperature temperature2, double d) {
            this.j = str;
            this.b = num;
            this.c = str2;
            this.t = num2;
            this.u = num3;
            this.v = f;
            this.w = str3;
            this.z = num4;
            this.i = num7;
            this.p = sleepDetails;
            this.k = str5;
            this.g = num6;
            this.f = num5;
            this.h = str4;
            this.e = str6;
            if (temperature != null) {
                this.q = temperature;
            }
            if (temperature2 != null) {
                this.r = temperature2;
            }
            this.d = num8;
            this.x = Double.valueOf(d);
        }

        public Data(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Location location, @Nullable Integer num, @Nullable Double d) {
            this.f4798a = str;
            this.l = str2;
            this.m = str3;
            this.o = location;
            this.s = num;
            this.x = d;
        }
    }
}
