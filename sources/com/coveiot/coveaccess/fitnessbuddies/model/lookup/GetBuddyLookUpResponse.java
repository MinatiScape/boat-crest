package com.coveiot.coveaccess.fitnessbuddies.model.lookup;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class GetBuddyLookUpResponse implements Serializable {
    @SerializedName("data")
    @Expose
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("users")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<UsersList> f6529a;

        /* loaded from: classes8.dex */
        public static class UsersList {
            @SerializedName(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID)
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6530a;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            @Expose
            private String b;
            @SerializedName("dpUrl")
            @Expose
            private String c;
            @SerializedName("mobileNumber")
            @Expose
            private String d;
            @SerializedName("featureSet")
            @Expose
            private FeatureSet e;

            /* loaded from: classes8.dex */
            public static class FeatureSet {
                @SerializedName("fitnessSocial")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private FitnessSocial f6531a;

                /* loaded from: classes8.dex */
                public static class FitnessSocial {
                    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6532a;
                    @SerializedName("userSessions")
                    @Expose
                    private List<UserSessionDetails> b;

                    /* loaded from: classes8.dex */
                    public static class UserSessionDetails {
                        @SerializedName("appName")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private String f6533a;
                        @SerializedName("appDisplayName")
                        @Expose
                        private String b;
                        @SerializedName("osName")
                        @Expose
                        private String c;
                        @SerializedName("appHealthStatus")
                        @Expose
                        private String d;
                        @SerializedName("lastConnectDate")
                        @Expose
                        private String e;

                        public String getAppDisplayName() {
                            return this.b;
                        }

                        public String getAppHealthStatus() {
                            return this.d;
                        }

                        public String getAppName() {
                            return this.f6533a;
                        }

                        public String getLastConnectDate() {
                            return this.e;
                        }

                        public String getOsName() {
                            return this.c;
                        }

                        public void setAppDisplayName(String str) {
                            this.b = str;
                        }

                        public void setAppHealthStatus(String str) {
                            this.d = str;
                        }

                        public void setAppName(String str) {
                            this.f6533a = str;
                        }

                        public void setLastConnectDate(String str) {
                            this.e = str;
                        }

                        public void setOsName(String str) {
                            this.c = str;
                        }
                    }

                    public String getActive() {
                        return this.f6532a;
                    }

                    public List<UserSessionDetails> getUserSessions() {
                        return this.b;
                    }

                    public void setActive(String str) {
                        this.f6532a = str;
                    }

                    public void setUserSessions(List<UserSessionDetails> list) {
                        this.b = list;
                    }
                }

                public FitnessSocial getFitnessSocial() {
                    return this.f6531a;
                }

                public void setFitnessSocial(FitnessSocial fitnessSocial) {
                    this.f6531a = fitnessSocial;
                }
            }

            public String getDpUrl() {
                return this.c;
            }

            public FeatureSet getFeatureSet() {
                return this.e;
            }

            public String getMobileNumber() {
                return this.d;
            }

            public String getName() {
                return this.b;
            }

            public String getUserId() {
                return this.f6530a;
            }

            public void setDpUrl(String str) {
                this.c = str;
            }

            public void setFeatureSet(FeatureSet featureSet) {
                this.e = featureSet;
            }

            public void setMobileNumber(String str) {
                this.d = str;
            }

            public void setName(String str) {
                this.b = str;
            }

            public void setUserId(String str) {
                this.f6530a = str;
            }
        }

        public List<UsersList> getUsersLists() {
            return this.f6529a;
        }

        public void setUsersLists(List<UsersList> list) {
            this.f6529a = list;
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
