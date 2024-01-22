package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SFeedBackCategories {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("fields")
        private List<FieldsBean> fields;
        @SerializedName("formVersion")
        private int formVersion;

        /* loaded from: classes8.dex */
        public static class FieldsBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private String name;
            @SerializedName("options")
            private List<OptionsBean> options;
            @SerializedName("required")
            private boolean required;
            @SerializedName("type")
            private String type;

            /* loaded from: classes8.dex */
            public static class OptionsBean {
                @SerializedName("text")
                private String text;
                @SerializedName("value")
                private String value;

                public String getText() {
                    return this.text;
                }

                public String getValue() {
                    return this.value;
                }

                public void setText(String str) {
                    this.text = str;
                }

                public void setValue(String str) {
                    this.value = str;
                }
            }

            public String getName() {
                return this.name;
            }

            public List<OptionsBean> getOptions() {
                return this.options;
            }

            public String getType() {
                return this.type;
            }

            public boolean isRequired() {
                return this.required;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setOptions(List<OptionsBean> list) {
                this.options = list;
            }

            public void setRequired(boolean z) {
                this.required = z;
            }

            public void setType(String str) {
                this.type = str;
            }
        }

        public List<FieldsBean> getFields() {
            return this.fields;
        }

        public int getFormVersion() {
            return this.formVersion;
        }

        public void setFields(List<FieldsBean> list) {
            this.fields = list;
        }

        public void setFormVersion(int i) {
            this.formVersion = i;
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
