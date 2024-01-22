package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class STrackListModel implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        @SerializedName(SavingTrackHelper.DATABASE_NAME)
        private List<TracksBean> tracks;

        /* loaded from: classes8.dex */
        public static class TracksBean implements Serializable {
            @SerializedName("bannerDuration")
            private int bannerDuration;
            @SerializedName("bannerName")
            private String bannerName;
            @SerializedName("id")
            private int id;
            @SerializedName("mediaDuration")
            private int mediaDuration;
            @SerializedName("mediaImgUrl")
            private String mediaImgUrl;
            @SerializedName("mediaUrl")
            private String mediaUrl;
            @SerializedName("trackDesc")
            private String trackDesc;
            @SerializedName("trackName")
            private String trackName;

            public int getBannerDuration() {
                return this.bannerDuration;
            }

            public String getBannerName() {
                return this.bannerName;
            }

            public int getId() {
                return this.id;
            }

            public int getMediaDuration() {
                return this.mediaDuration;
            }

            public String getMediaImgUrl() {
                return this.mediaImgUrl;
            }

            public String getMediaUrl() {
                return this.mediaUrl;
            }

            public String getTrackDesc() {
                return this.trackDesc;
            }

            public String getTrackName() {
                return this.trackName;
            }

            public void setBannerDuration(int i) {
                this.bannerDuration = i;
            }

            public void setBannerName(String str) {
                this.bannerName = str;
            }

            public void setId(int i) {
                this.id = i;
            }

            public void setMediaDuration(int i) {
                this.mediaDuration = i;
            }

            public void setMediaImgUrl(String str) {
                this.mediaImgUrl = str;
            }

            public void setMediaUrl(String str) {
                this.mediaUrl = str;
            }

            public void setTrackDesc(String str) {
                this.trackDesc = str;
            }

            public void setTrackName(String str) {
                this.trackName = str;
            }
        }

        public List<TracksBean> getTracks() {
            return this.tracks;
        }

        public void setTracks(List<TracksBean> list) {
            this.tracks = list;
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
