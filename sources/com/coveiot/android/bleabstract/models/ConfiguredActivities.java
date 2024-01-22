package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class ConfiguredActivities {

    /* renamed from: a  reason: collision with root package name */
    public List<ActivityInfo> f3418a;

    /* loaded from: classes2.dex */
    public static class ActivityInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f3419a;
        public int b;
        public int c;
        public int d;

        public ActivityInfo(int i, int i2, int i3, int i4) {
            this.f3419a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public int getActivityId() {
            return this.b;
        }

        public int getActivityNum() {
            return this.f3419a;
        }

        public int getCategoryId() {
            return this.c;
        }

        public int getOrderId() {
            return this.d;
        }

        public String toString() {
            return "ActivityInfo{activityNum=" + this.f3419a + ", activityId=" + this.b + ", categoryId=" + this.c + ", orderId=" + this.d + '}';
        }
    }

    public ConfiguredActivities(List<ActivityInfo> list) {
        this.f3418a = list;
    }

    public List<ActivityInfo> getActivityInfoList() {
        return this.f3418a;
    }

    public String toString() {
        return "ConfiguredActivities{activityInfoList=" + this.f3418a + '}';
    }
}
