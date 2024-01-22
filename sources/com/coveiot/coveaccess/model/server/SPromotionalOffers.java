package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SPromotionalOffers {
    @SerializedName("data")
    private DataDTO data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataDTO {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        private List<ItemsDTO> items;
        @SerializedName("totalItems")
        private Integer totalItems;

        /* loaded from: classes8.dex */
        public static class ItemsDTO {
            @SerializedName("bannerImageUrl")
            private String bannerImageUrl;
            @SerializedName("redirectUrl")
            private String redirectUrl;
            @SerializedName("redirectionType")
            private String redirectionType;

            public String getBannerImageUrl() {
                return this.bannerImageUrl;
            }

            public String getRedirectUrl() {
                return this.redirectUrl;
            }

            public String getRedirectionType() {
                return this.redirectionType;
            }

            public void setBannerImageUrl(String str) {
                this.bannerImageUrl = str;
            }

            public void setRedirectUrl(String str) {
                this.redirectUrl = str;
            }

            public void setRedirectionType(String str) {
                this.redirectionType = str;
            }
        }

        public List<ItemsDTO> getItems() {
            return this.items;
        }

        public Integer getTotalItems() {
            return this.totalItems;
        }

        public void setItems(List<ItemsDTO> list) {
            this.items = list;
        }

        public void setTotalItems(Integer num) {
            this.totalItems = num;
        }
    }

    public DataDTO getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataDTO dataDTO) {
        this.data = dataDTO;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
