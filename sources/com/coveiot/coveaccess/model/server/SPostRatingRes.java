package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostRatingRes {
    @SerializedName("data")
    private DataDTO data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataDTO {
        @SerializedName("positiveRating")
        private Boolean positiveRating;

        public Boolean getPositiveRating() {
            return this.positiveRating;
        }

        public void setPositiveRating(Boolean bool) {
            this.positiveRating = bool;
        }

        public String toString() {
            return "DataDTO{positiveRating=" + this.positiveRating + '}';
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

    public String toString() {
        return "SPostRatingRes{data=" + this.data + ", message='" + this.message + "', status='" + this.status + "'}";
    }
}
