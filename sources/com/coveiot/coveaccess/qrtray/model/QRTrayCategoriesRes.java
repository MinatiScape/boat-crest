package com.coveiot.coveaccess.qrtray.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class QRTrayCategoriesRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    public List<QRItem> qrItemsList;

    /* loaded from: classes8.dex */
    public static class QRItem implements Serializable {
        @SerializedName("categoryId")
        @Expose
        public String categoryId;
        @SerializedName("iconUrl")
        @Expose
        public String iconUrl;
        @SerializedName("title")
        @Expose
        public String title;

        public String getCategoryId() {
            return this.categoryId;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }

        public String getTitle() {
            return this.title;
        }

        public void setCategoryId(String str) {
            this.categoryId = str;
        }

        public void setIconUrl(String str) {
            this.iconUrl = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public List<QRItem> getQrItemsList() {
        return this.qrItemsList;
    }

    public void setQrItemsList(List<QRItem> list) {
        this.qrItemsList = list;
    }
}
