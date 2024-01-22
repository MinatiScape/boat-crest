package com.coveiot.analytics;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AnalyticsUserProperty {

    /* loaded from: classes2.dex */
    public enum KeyName {
        USER_SESSION_ID("kh_user_session_id"),
        USER_PHONE_NUMBER("kh_user_phone_number"),
        KH_DEVICE_MODEL_NUMBER("kh_device_model_number"),
        KH_DEVICE_CUSTOMER_ID("kh_device_customer_id"),
        KH_DEVICE_SERIAL_NUMBER("kh_device_serial_number"),
        KH_APP_VERSION("kh_app_version"),
        KH_PHONE_MODEL_NUMBER("kh_phone_model_number"),
        KH_PHONE_OS_VERSION("kh_phone_os_version");
        
        @NotNull
        private String textValue;

        KeyName(String str) {
            this.textValue = str;
        }

        @NotNull
        public final String getTextValue$analytics_release() {
            return this.textValue;
        }

        public final void setTextValue$analytics_release(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.textValue = str;
        }

        @NotNull
        public final String textValue() {
            return this.textValue;
        }
    }
}
