package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class BatteryOptimizationConfig {
    @SerializedName("configs")

    /* renamed from: a  reason: collision with root package name */
    private List<Configs> f7015a;

    /* loaded from: classes8.dex */
    public static class Configs {
        @SerializedName("manufacturer")

        /* renamed from: a  reason: collision with root package name */
        private String f7016a;
        @SerializedName("model")
        private List<String> b;
        @SerializedName("android_version")
        private List<Integer> c;
        @SerializedName("snakbar_text")
        private String d;

        public List<Integer> getAndroidVersion() {
            return this.c;
        }

        public String getManufacturer() {
            return this.f7016a;
        }

        public List<String> getModel() {
            return this.b;
        }

        public String getSnakbarText() {
            return this.d;
        }

        public void setAndroidVersion(List<Integer> list) {
            this.c = list;
        }

        public void setManufacturer(String str) {
            this.f7016a = str;
        }

        public void setModel(List<String> list) {
            this.b = list;
        }

        public void setSnakbarText(String str) {
            this.d = str;
        }
    }

    public List<Configs> getConfigs() {
        return this.f7015a;
    }

    public void setConfigs(List<Configs> list) {
        this.f7015a = list;
    }
}
