package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import com.polidea.rxandroidble2.ClientComponent;
/* loaded from: classes8.dex */
public class RespiratoryRateRemoteConfiguration {
    @SerializedName("startTime")

    /* renamed from: a  reason: collision with root package name */
    private String f7037a = "21:00:00";
    @SerializedName("endTime")
    private String b = "09:00:00";
    @SerializedName("interval")
    private String c = "01:00:00";
    @SerializedName("duration")
    private int d = 60;
    @SerializedName(ClientComponent.NamedSchedulers.COMPUTATION)
    private Computation e = new Computation(this);

    /* loaded from: classes8.dex */
    public class Computation {
        @SerializedName("confidenceLevelThreshold")

        /* renamed from: a  reason: collision with root package name */
        private float f7038a = 60.0f;

        public Computation(RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfiguration) {
        }

        public float getConfidenceLevelThreshold() {
            return this.f7038a;
        }

        public void setConfidenceLevelThreshold(float f) {
            this.f7038a = f;
        }
    }

    public Computation getComputation() {
        return this.e;
    }

    public int getDuration() {
        return this.d;
    }

    public String getEndTime() {
        return this.b;
    }

    public String getInterval() {
        return this.c;
    }

    public String getStartTime() {
        return this.f7037a;
    }

    public void setComputation(Computation computation) {
        this.e = computation;
    }

    public void setDuration(int i) {
        this.d = i;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setInterval(String str) {
        this.c = str;
    }

    public void setStartTime(String str) {
        this.f7037a = str;
    }
}
