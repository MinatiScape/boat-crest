package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class BannerImagesRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<Item> items;

    /* loaded from: classes8.dex */
    public static class Item implements Serializable {
        @SerializedName("bannerImageId")
        @Expose
        private Integer bannerImageId;
        @SerializedName("bannerImageUrl")
        @Expose
        private String bannerImageUrl;

        public Integer getBannerImageId() {
            return this.bannerImageId;
        }

        public String getBannerImageUrl() {
            return this.bannerImageUrl;
        }

        public void setBannerImageId(Integer num) {
            this.bannerImageId = num;
        }

        public void setBannerImageUrl(String str) {
            this.bannerImageUrl = str;
        }
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }
}
