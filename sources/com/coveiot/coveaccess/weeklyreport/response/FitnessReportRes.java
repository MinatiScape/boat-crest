package com.coveiot.coveaccess.weeklyreport.response;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessReportRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<FitnessReportItem> items;

    /* loaded from: classes8.dex */
    public static class FitnessReportItem implements Serializable {
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("fileType")
        @Expose
        private String fileType;
        @SerializedName("fileUrl")
        @Expose
        private String fileUrl;
        @SerializedName("title")
        @Expose
        private String title;

        public FitnessReportItem(String str, String str2, String str3, String str4) {
            this.title = str;
            this.fileUrl = str2;
            this.fileType = str3;
            this.createdDate = str4;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getFileType() {
            return this.fileType;
        }

        public String getFileUrl() {
            return this.fileUrl;
        }

        public String getTitle() {
            return this.title;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setFileType(String str) {
            this.fileType = str;
        }

        public void setFileUrl(String str) {
            this.fileUrl = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public List<FitnessReportItem> getItems() {
        return this.items;
    }

    public void setItems(List<FitnessReportItem> list) {
        this.items = list;
    }
}
