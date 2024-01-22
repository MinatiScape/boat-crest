package com.coveiot.coveaccess.boatcoins.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class CoinsDataRequestResponse extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("recipients")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<Recipient> f6433a = null;

        /* loaded from: classes8.dex */
        public class Recipient {
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6434a;
            @SerializedName(Constants.KEY_MESSAGE)
            @Expose
            private String b;
            @SerializedName("data")
            @Expose
            private RecipientData c;

            /* loaded from: classes8.dex */
            public class RecipientData {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private String f6435a;
                @SerializedName("mobileNumber")
                @Expose
                private String b;
                @SerializedName("coins")
                @Expose
                private int c;
                @SerializedName("inviteText")
                @Expose
                private String d;

                public RecipientData(Recipient recipient) {
                }

                public int getCoins() {
                    return this.c;
                }

                public String getInviteText() {
                    return this.d;
                }

                public String getMobileNumber() {
                    return this.b;
                }

                public String getName() {
                    return this.f6435a;
                }

                public void setCoins(int i) {
                    this.c = i;
                }

                public void setInviteText(String str) {
                    this.d = str;
                }

                public void setMobileNumber(String str) {
                    this.b = str;
                }

                public void setName(String str) {
                    this.f6435a = str;
                }
            }

            public Recipient(Data data) {
            }

            public RecipientData getData() {
                return this.c;
            }

            public String getMessage() {
                return this.b;
            }

            public String getStatus() {
                return this.f6434a;
            }

            public void setData(RecipientData recipientData) {
                this.c = recipientData;
            }

            public void setMessage(String str) {
                this.b = str;
            }

            public void setStatus(String str) {
                this.f6434a = str;
            }
        }

        public Data(CoinsDataRequestResponse coinsDataRequestResponse) {
        }

        public List<Recipient> getRecipients() {
            return this.f6433a;
        }

        public void setRecipients(List<Recipient> list) {
            this.f6433a = list;
        }
    }

    public CoinsDataRequestResponse(int i) {
        super(i);
    }
}
