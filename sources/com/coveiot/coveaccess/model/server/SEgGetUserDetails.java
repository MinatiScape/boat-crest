package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class SEgGetUserDetails {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("age")
        private Integer age;
        @SerializedName("baseUnits")
        private BaseUnitsBean baseUnits;
        @SerializedName("gender")
        private String gender;
        @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
        private Integer height;
        @SerializedName("runningStrideLength")
        private Integer runningStrideLength;
        @SerializedName("swimmingStrokeLength")
        private Integer swimmingStrokeLength;
        @SerializedName("walkingStrideLength")
        private Integer walkingStrideLength;
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

        public Integer getAge() {
            return this.age;
        }

        public BaseUnitsBean getBaseUnits() {
            return this.baseUnits;
        }

        public String getGender() {
            return this.gender;
        }

        public Integer getHeight() {
            return this.height;
        }

        public Integer getRunningStrideLength() {
            return this.runningStrideLength;
        }

        public Integer getSwimmingStrokeLength() {
            return this.swimmingStrokeLength;
        }

        public Integer getWalkingStrideLength() {
            return this.walkingStrideLength;
        }

        public Integer getWeight() {
            return this.weight;
        }

        public void setAge(Integer num) {
            this.age = num;
        }

        public void setBaseUnits(BaseUnitsBean baseUnitsBean) {
            this.baseUnits = baseUnitsBean;
        }

        public void setGender(String str) {
            this.gender = str;
        }

        public void setHeight(Integer num) {
            this.height = num;
        }

        public void setRunningStrideLength(Integer num) {
            this.runningStrideLength = num;
        }

        public void setSwimmingStrokeLength(Integer num) {
            this.swimmingStrokeLength = num;
        }

        public void setWalkingStrideLength(Integer num) {
            this.walkingStrideLength = num;
        }

        public void setWeight(Integer num) {
            this.weight = num;
        }

        public String toString() {
            return "DataBean{gender='" + this.gender + "', age=" + this.age + ", height=" + this.height + ", weight=" + this.weight + ", walkingStrideLength=" + this.walkingStrideLength + ", runningStrideLength=" + this.runningStrideLength + ", swimmingStrokeLength=" + this.swimmingStrokeLength + ", baseUnits=" + this.baseUnits + '}';
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
        return "SEgGetUserDetails{message='" + this.message + "', status='" + this.status + "', data=" + this.data + '}';
    }
}
