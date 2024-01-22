package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class BleConfiguredActivities {
    private List<ActivityInfo> activityInfoList;

    /* loaded from: classes9.dex */
    public static class ActivityInfo {
        private int activityId;
        private int activityNum;
        private int categoryId;
        private int orderId;

        public ActivityInfo(int i, int i2, int i3, int i4) {
            this.activityNum = i;
            this.activityId = i2;
            this.categoryId = i3;
            this.orderId = i4;
        }

        public int getActivityId() {
            return this.activityId;
        }

        public int getActivityNum() {
            return this.activityNum;
        }

        public int getCategoryId() {
            return this.categoryId;
        }

        public int getOrderId() {
            return this.orderId;
        }
    }

    public BleConfiguredActivities(List<ActivityInfo> list) {
        this.activityInfoList = list;
    }

    public List<ActivityInfo> getActivityInfoList() {
        return this.activityInfoList;
    }
}
