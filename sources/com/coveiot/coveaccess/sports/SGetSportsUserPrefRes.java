package com.coveiot.coveaccess.sports;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetSportsUserPrefRes extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("coinsNotifications")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<CoinNotifications> f6746a = null;

        /* loaded from: classes8.dex */
        public class CoinNotifications {
            @SerializedName("title")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6747a;
            @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
            @Expose
            private String b;
            @SerializedName("earnedCoins")
            @Expose
            private Integer c;
            @SerializedName("notificationUrl")
            @Expose
            private String d;

            public CoinNotifications(Data data) {
            }

            public String getDescription() {
                return this.b;
            }

            public Integer getEarnedCoins() {
                return this.c;
            }

            public String getNotificationUrl() {
                return this.d;
            }

            public String getTitle() {
                return this.f6747a;
            }

            public void setDescription(String str) {
                this.b = str;
            }

            public void setEarnedCoins(Integer num) {
                this.c = num;
            }

            public void setNotificationUrl(String str) {
                this.d = str;
            }

            public void setTitle(String str) {
                this.f6747a = str;
            }
        }

        public Data(SGetSportsUserPrefRes sGetSportsUserPrefRes) {
        }

        public List<CoinNotifications> getCoinsNotifications() {
            return this.f6746a;
        }

        public void setCoinsNotifications(List<CoinNotifications> list) {
            this.f6746a = list;
        }
    }

    public SGetSportsUserPrefRes(int i) {
        super(i);
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
