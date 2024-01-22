package com.coveiot.coveaccess.qrtray.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class QRTrayCodesRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    public List<QRTrayCodeData> items;

    /* loaded from: classes8.dex */
    public static class QRTrayCodeData implements Serializable {
        @SerializedName("applied")
        @Expose
        public Boolean applied;
        @SerializedName("categoryId")
        @Expose
        public String categoryId;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("imageRefId")
        @Expose
        public Integer imageRefId;
        @SerializedName("imageUrl")
        @Expose
        public String imageUrl;
        @SerializedName("lastAppliedDate")
        @Expose
        public String lastAppliedDate;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        public String name;
        @SerializedName("title")
        @Expose
        public String title;

        public Boolean getApplied() {
            return this.applied;
        }

        public String getCategoryId() {
            return this.categoryId;
        }

        public String getId() {
            return this.id;
        }

        public Integer getImageRefId() {
            return this.imageRefId;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public String getLastAppliedDate() {
            return this.lastAppliedDate;
        }

        public String getName() {
            return this.name;
        }

        public String getTitle() {
            return this.title;
        }

        public void setApplied(Boolean bool) {
            this.applied = bool;
        }

        public void setCategoryId(String str) {
            this.categoryId = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setImageRefId(Integer num) {
            this.imageRefId = num;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public void setLastAppliedDate(String str) {
            this.lastAppliedDate = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public List<QRTrayCodeData> getItems() {
        return this.items;
    }

    public void setItems(List<QRTrayCodeData> list) {
        this.items = list;
    }
}
