package com.coveiot.coveaccess.fitness.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class GetGoalHistoryResponse implements Serializable {
    @SerializedName("data")
    @Expose
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose
        private List<Item> items;

        /* loaded from: classes8.dex */
        public static class Item {
            @SerializedName("activityBaseUnit")
            @Expose
            private String activityBaseUnit;
            @SerializedName("activityType")
            @Expose
            private String activityType;
            @SerializedName("startDate")
            @Expose
            private String startDate;
            @SerializedName(TypedValues.AttributesType.S_TARGET)
            @Expose
            private Integer target;
            @SerializedName("targetedDays")
            @Expose
            private Integer targetedDays;

            public String getActivityBaseUnit() {
                return this.activityBaseUnit;
            }

            public String getActivityType() {
                return this.activityType;
            }

            public String getStartDate() {
                return this.startDate;
            }

            public Integer getTarget() {
                return this.target;
            }

            public Integer getTargetedDays() {
                return this.targetedDays;
            }

            public void setActivityBaseUnit(String str) {
                this.activityBaseUnit = str;
            }

            public void setActivityType(String str) {
                this.activityType = str;
            }

            public void setStartDate(String str) {
                this.startDate = str;
            }

            public void setTarget(Integer num) {
                this.target = num;
            }

            public void setTargetedDays(Integer num) {
                this.targetedDays = num;
            }
        }

        public List<Item> getItems() {
            return this.items;
        }

        public void setItems(List<Item> list) {
            this.items = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
