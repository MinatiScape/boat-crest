package com.coveiot.android.leonardo.dashboard.model;

import com.coveiot.coveaccess.constants.CoveApiHeaderConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WebSocketSendHeaderRequest {
    @SerializedName("resType")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f4802a;
    @SerializedName("data")
    @Expose
    @Nullable
    private Data b;

    /* loaded from: classes3.dex */
    public static final class Data {
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_TIMEZONE)
        @Expose
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        private Integer f4803a;
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_CLOVE_API_VERSION)
        @Expose
        @Nullable
        private Integer b;
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_API_KEY)
        @Expose
        @Nullable
        private String c;
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_USER_AGENT)
        @Expose
        @Nullable
        private String d;
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_DEVICE_AGENT)
        @Expose
        @Nullable
        private String e;
        @SerializedName(CoveApiHeaderConstants.HTTP_HEADER_SESSION_ID)
        @Expose
        @Nullable
        private String f;

        public Data(@Nullable Integer num, @Nullable Integer num2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
            this.f4803a = num;
            this.b = num2;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
        }

        @Nullable
        public final String getXCloveApiKey() {
            return this.c;
        }

        @Nullable
        public final Integer getXCloveApiVersion() {
            return this.b;
        }

        @Nullable
        public final String getXCloveAppAgent() {
            return this.d;
        }

        @Nullable
        public final Integer getXCloveAppTimezoneOffset() {
            return this.f4803a;
        }

        @Nullable
        public final String getXCloveDeviceAgent() {
            return this.e;
        }

        @Nullable
        public final String getXCloveSessionId() {
            return this.f;
        }

        public final void setXCloveApiKey(@Nullable String str) {
            this.c = str;
        }

        public final void setXCloveApiVersion(@Nullable Integer num) {
            this.b = num;
        }

        public final void setXCloveAppAgent(@Nullable String str) {
            this.d = str;
        }

        public final void setXCloveAppTimezoneOffset(@Nullable Integer num) {
            this.f4803a = num;
        }

        public final void setXCloveDeviceAgent(@Nullable String str) {
            this.e = str;
        }

        public final void setXCloveSessionId(@Nullable String str) {
            this.f = str;
        }
    }

    public WebSocketSendHeaderRequest(@Nullable String str, @Nullable Data data) {
        this.f4802a = str;
        this.b = data;
    }

    @Nullable
    public final Data getData() {
        return this.b;
    }

    @Nullable
    public final String getResType() {
        return this.f4802a;
    }

    public final void setData(@Nullable Data data) {
        this.b = data;
    }

    public final void setResType(@Nullable String str) {
        this.f4802a = str;
    }
}
