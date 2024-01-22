package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.List;
/* loaded from: classes8.dex */
public class SWorkoutVideosList {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        private List<ItemsBean> items;
        @SerializedName("itemsPerPage")
        private int itemsPerPage;
        @SerializedName("pageIndex")
        private int pageIndex;
        @SerializedName("totalItems")
        private int totalItems;

        /* loaded from: classes8.dex */
        public static class ItemsBean {
            @SerializedName("categoryIds")
            private List<String> categoryIds = null;
            @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
            private String description;
            @SerializedName("duration")
            private Integer duration;
            @SerializedName("thumbnailUrl")
            private String thumbnailUrl;
            @SerializedName("title")
            private String title;
            @SerializedName("videoId")
            private String videoId;
            @SerializedName("videoUrl")
            private String videoUrl;
            @SerializedName("ytVideoId")
            private String ytVideoId;

            public List<String> getCategoryIds() {
                return this.categoryIds;
            }

            public String getDescription() {
                return this.description;
            }

            public Integer getDuration() {
                return this.duration;
            }

            public String getThumbnailUrl() {
                return this.thumbnailUrl;
            }

            public String getTitle() {
                return this.title;
            }

            public String getVideoId() {
                return this.videoId;
            }

            public String getVideoUrl() {
                return this.videoUrl;
            }

            public String getYtVideoId() {
                return this.ytVideoId;
            }

            public void setCategoryIds(List<String> list) {
                this.categoryIds = list;
            }

            public void setDescription(String str) {
                this.description = str;
            }

            public void setDuration(Integer num) {
                this.duration = num;
            }

            public void setThumbnailUrl(String str) {
                this.thumbnailUrl = str;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public void setVideoId(String str) {
                this.videoId = str;
            }

            public void setVideoUrl(String str) {
                this.videoUrl = str;
            }

            public void setYtVideoId(String str) {
                this.ytVideoId = str;
            }
        }

        public List<ItemsBean> getItems() {
            return this.items;
        }

        public int getItemsPerPage() {
            return this.itemsPerPage;
        }

        public int getPageIndex() {
            return this.pageIndex;
        }

        public int getTotalItems() {
            return this.totalItems;
        }

        public void setItems(List<ItemsBean> list) {
            this.items = list;
        }

        public void setItemsPerPage(int i) {
            this.itemsPerPage = i;
        }

        public void setPageIndex(int i) {
            this.pageIndex = i;
        }

        public void setTotalItems(int i) {
            this.totalItems = i;
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
