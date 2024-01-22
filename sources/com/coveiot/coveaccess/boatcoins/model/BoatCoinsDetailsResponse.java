package com.coveiot.coveaccess.boatcoins.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class BoatCoinsDetailsResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private DataBean f6426a;
    @SerializedName(Constants.KEY_MESSAGE)
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String c;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("firstName")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6427a;
        @SerializedName("lastName")
        @Expose
        private String b;
        @SerializedName("gender")
        @Expose
        private String c;
        @SerializedName("dpUrl")
        @Expose
        private String d;
        @SerializedName("coinsProfile")
        @Expose
        private CoinsProfile e;
        @SerializedName("levels")
        @Expose
        private List<LevelsItem> f;

        /* loaded from: classes8.dex */
        public static class CoinsProfile {
            @SerializedName("levelId")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private Integer f6428a;
            @SerializedName("levelName")
            @Expose
            private String b;
            @SerializedName("badgeUrl")
            @Expose
            private String c;
            @SerializedName("bgUrl")
            @Expose
            private String d;
            @SerializedName("coinsEarned")
            @Expose
            private Integer e;
            @SerializedName("coinsRedeemed")
            @Expose
            private Integer f;
            @SerializedName("coinsExpired")
            @Expose
            private Integer g;
            @SerializedName("coinsBalance")
            @Expose
            private Integer h;
            @SerializedName("coinsExpiring")
            @Expose
            private CoinsExpiring i;

            /* loaded from: classes8.dex */
            public static class CoinsExpiring {
                @SerializedName("value")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private Integer f6429a;
                @SerializedName("expiryDate")
                @Expose
                private String b;

                public String getExpiryDate() {
                    return this.b;
                }

                public Integer getValue() {
                    return this.f6429a;
                }

                public void setExpiryDate(String str) {
                    this.b = str;
                }

                public void setValue(Integer num) {
                    this.f6429a = num;
                }
            }

            public String getBadgeUrl() {
                return this.c;
            }

            public String getBgUrl() {
                return this.d;
            }

            public Integer getCoinsBalance() {
                return this.h;
            }

            public Integer getCoinsEarned() {
                return this.e;
            }

            public Integer getCoinsExpired() {
                return this.g;
            }

            public CoinsExpiring getCoinsExpiring() {
                return this.i;
            }

            public Integer getCoinsRedeemed() {
                return this.f;
            }

            public Integer getLevelId() {
                return this.f6428a;
            }

            public String getLevelName() {
                return this.b;
            }

            public void setBadgeUrl(String str) {
                this.c = str;
            }

            public void setBgUrl(String str) {
                this.d = str;
            }

            public void setCoinsBalance(Integer num) {
                this.h = num;
            }

            public void setCoinsEarned(Integer num) {
                this.e = num;
            }

            public void setCoinsExpired(Integer num) {
                this.g = num;
            }

            public void setCoinsExpiring(CoinsExpiring coinsExpiring) {
                this.i = coinsExpiring;
            }

            public void setCoinsRedeemed(Integer num) {
                this.f = num;
            }

            public void setLevelId(Integer num) {
                this.f6428a = num;
            }

            public void setLevelName(String str) {
                this.b = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class LevelsItem {
            @SerializedName("levelId")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private Integer f6430a;
            @SerializedName("levelName")
            @Expose
            private String b;
            @SerializedName("profileBgUrl")
            @Expose
            private String c;
            @SerializedName("coinsRequired")
            @Expose
            private Integer d;

            public Integer getCoinsRequired() {
                return this.d;
            }

            public Integer getLevelId() {
                return this.f6430a;
            }

            public String getLevelName() {
                return this.b;
            }

            public String getProfileBgUrl() {
                return this.c;
            }

            public void setCoinsRequired(Integer num) {
                this.d = num;
            }

            public void setLevelId(Integer num) {
                this.f6430a = num;
            }

            public void setLevelName(String str) {
                this.b = str;
            }

            public void setProfileBgUrl(String str) {
                this.c = str;
            }
        }

        public CoinsProfile getCoinsProfile() {
            return this.e;
        }

        public String getDpUrl() {
            return this.d;
        }

        public String getFirstName() {
            return this.f6427a;
        }

        public String getGender() {
            return this.c;
        }

        public String getLastName() {
            return this.b;
        }

        public List<LevelsItem> getLevels() {
            return this.f;
        }

        public void setCoinsProfile(CoinsProfile coinsProfile) {
            this.e = coinsProfile;
        }

        public void setDpUrl(String str) {
            this.d = str;
        }

        public void setFirstName(String str) {
            this.f6427a = str;
        }

        public void setGender(String str) {
            this.c = str;
        }

        public void setLastName(String str) {
            this.b = str;
        }

        public void setLevels(List<LevelsItem> list) {
            this.f = list;
        }
    }

    public DataBean getData() {
        return this.f6426a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(DataBean dataBean) {
        this.f6426a = dataBean;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }
}
