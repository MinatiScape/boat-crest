package com.coveiot.coveaccess.leaderboard.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class BadgesCategoryModel implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose
        private List<ItemsList> itemsLists;

        /* loaded from: classes8.dex */
        public static class ItemsList {
            @SerializedName("categoryId")
            @Expose
            private String categoryId;
            @SerializedName("title")
            @Expose
            private String title;

            public String getCategoryId() {
                return this.categoryId;
            }

            public String getTitle() {
                return this.title;
            }

            public void setCategoryId(String str) {
                this.categoryId = str;
            }

            public void setTitle(String str) {
                this.title = str;
            }
        }

        public List<ItemsList> getItemsLists() {
            return this.itemsLists;
        }

        public void setItemsLists(List<ItemsList> list) {
            this.itemsLists = list;
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
