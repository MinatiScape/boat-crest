package com.coveiot.coveaccess.feedback;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FeedbackCategoriesRes {
    @SerializedName("fields")

    /* renamed from: a  reason: collision with root package name */
    private List<FieldsBean> f6521a;

    /* loaded from: classes8.dex */
    public static class FieldsBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)

        /* renamed from: a  reason: collision with root package name */
        private String f6522a;
        @SerializedName("type")
        private String b;
        @SerializedName("required")
        private boolean c;
        @SerializedName("options")
        private List<OptionsBean> d;

        /* loaded from: classes8.dex */
        public static class OptionsBean {
            @SerializedName("value")

            /* renamed from: a  reason: collision with root package name */
            private String f6523a;
            @SerializedName("text")
            private String b;

            public String getText() {
                return this.b;
            }

            public String getValue() {
                return this.f6523a;
            }

            public void setText(String str) {
                this.b = str;
            }

            public void setValue(String str) {
                this.f6523a = str;
            }
        }

        public String getName() {
            return this.f6522a;
        }

        public List<OptionsBean> getOptions() {
            return this.d;
        }

        public String getType() {
            return this.b;
        }

        public boolean isRequired() {
            return this.c;
        }

        public void setName(String str) {
            this.f6522a = str;
        }

        public void setOptions(List<OptionsBean> list) {
            this.d = list;
        }

        public void setRequired(boolean z) {
            this.c = z;
        }

        public void setType(String str) {
            this.b = str;
        }
    }

    public List<FieldsBean> getFields() {
        return this.f6521a;
    }

    public void setFields(List<FieldsBean> list) {
        this.f6521a = list;
    }
}
