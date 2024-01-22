package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class SGetUserDetails {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("axTrackerId")
        private String axTrackerId;
        @SerializedName("baseUnits")
        private BaseUnitsBean baseUnits;
        @SerializedName("birthDate")
        private String birthDate;
        @SerializedName("dpUrl")
        private String dpUrl;
        @SerializedName("emailId")
        private String emailId;
        @SerializedName("emailVerified")
        private boolean emailVerified;
        @SerializedName("firstName")
        private String firstName;
        @SerializedName("gender")
        private String gender;
        @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
        private Integer height;
        @SerializedName("lastName")
        private String lastName;
        @SerializedName("mobileNumber")
        private String mobileNumber;
        @SerializedName("weight")
        private Integer weight;

        /* loaded from: classes8.dex */
        public static class BaseUnitsBean {
            @SerializedName("strideLength")
            private String strideLength;
            @SerializedName("strokeLength")
            private String strokeLength;
            @SerializedName("userHeight")
            private String userHeight;
            @SerializedName("userWeight")
            private String userWeight;

            public String getStrideLength() {
                return this.strideLength;
            }

            public String getStrokeLength() {
                return this.strokeLength;
            }

            public String getUserHeight() {
                return this.userHeight;
            }

            public String getUserWeight() {
                return this.userWeight;
            }

            public void setStrideLength(String str) {
                this.strideLength = str;
            }

            public void setStrokeLength(String str) {
                this.strokeLength = str;
            }

            public void setUserHeight(String str) {
                this.userHeight = str;
            }

            public void setUserWeight(String str) {
                this.userWeight = str;
            }

            public String toString() {
                return "BaseUnitsBean{userWeight='" + this.userWeight + "', userHeight='" + this.userHeight + "', strideLength='" + this.strideLength + "', strokeLength='" + this.strokeLength + "'}";
            }
        }

        public String getAxTrackerId() {
            return this.axTrackerId;
        }

        public BaseUnitsBean getBaseUnits() {
            return this.baseUnits;
        }

        public String getBirthDate() {
            return this.birthDate;
        }

        public String getDpUrl() {
            return this.dpUrl;
        }

        public String getEmailId() {
            return this.emailId;
        }

        public boolean getEmailVerified() {
            return this.emailVerified;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getGender() {
            return this.gender;
        }

        public Integer getHeight() {
            return this.height;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public Integer getWeight() {
            return this.weight;
        }

        public void setAxTrackerId(String str) {
            this.axTrackerId = str;
        }

        public void setBaseUnits(BaseUnitsBean baseUnitsBean) {
            this.baseUnits = baseUnitsBean;
        }

        public void setBirthDate(String str) {
            this.birthDate = str;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setEmailId(String str) {
            this.emailId = str;
        }

        public void setEmailVerified(boolean z) {
            this.emailVerified = z;
        }

        public void setFirstName(String str) {
            this.firstName = str;
        }

        public void setGender(String str) {
            this.gender = str;
        }

        public void setHeight(Integer num) {
            this.height = num;
        }

        public void setLastName(String str) {
            this.lastName = str;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setWeight(Integer num) {
            this.weight = num;
        }

        public String toString() {
            return "DataBean{gender='" + this.gender + "', birthDate=" + this.birthDate + "', height=" + this.height + ", weight=" + this.weight + ", mobileNumber=" + this.mobileNumber + "', emailId=" + this.emailId + "', firstName=" + this.firstName + "', lastName=" + this.lastName + "', dpUrl=" + this.dpUrl + "', baseUnits=" + this.baseUnits + '}';
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

    public String toString() {
        return "SGetUserDetails{message='" + this.message + "', status='" + this.status + "', data=" + this.data + '}';
    }
}
