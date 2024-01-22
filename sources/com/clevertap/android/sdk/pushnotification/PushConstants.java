package com.clevertap.android.sdk.pushnotification;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Logger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public interface PushConstants {
    @NonNull
    public static final String ADM_DELIVERY_TYPE = "adm";
    public static final String ADM_PROPERTY_REG_ID = "adm_token";
    public static final String ADM_SDK_CLASS = "com.amazon.device.messaging.ADM";
    public static final int ALL_DEVICES = 1;
    public static final int AMAZON_PLATFORM = 2;
    public static final int ANDROID_PLATFORM = 1;
    @NonNull
    public static final String BAIDU_DELIVERY_TYPE = "bps";
    public static final String BAIDU_SDK_CLASS = "com.baidu.android.pushservice.PushMessageReceiver";
    public static final String BPS_PROPERTY_REG_ID = "bps_token";
    public static final String CT_ADM_PROVIDER_CLASS = "com.clevertap.android.adm.AmazonPushProvider";
    public static final String CT_BAIDU_PROVIDER_CLASS = "com.clevertap.android.bps.BaiduPushProvider";
    public static final String CT_FIREBASE_PROVIDER_CLASS = "com.clevertap.android.sdk.pushnotification.fcm.FcmPushProvider";
    public static final String CT_HUAWEI_PROVIDER_CLASS = "com.clevertap.android.hms.HmsPushProvider";
    public static final String CT_XIAOMI_PROVIDER_CLASS = "com.clevertap.android.xps.XiaomiPushProvider";
    public static final String DEFAULT_PUSH_TYPE_REGION = "";
    @NonNull
    public static final String FCM_DELIVERY_TYPE = "fcm";
    public static final String FCM_LOG_TAG = PushType.FCM.toString();
    public static final String FCM_PROPERTY_REG_ID = "fcm_token";
    public static final String FIREBASE_SDK_CLASS = "com.google.firebase.messaging.FirebaseMessagingService";
    @NonNull
    public static final String HMS_DELIVERY_TYPE = "hps";
    public static final String HPS_PROPERTY_REG_ID = "hps_token";
    public static final String HUAWEI_SDK_CLASS = "com.huawei.hms.push.HmsMessageService";
    public static final String LOG_TAG = "PushProvider";
    public static final int NO_DEVICES = 3;
    @NonNull
    public static final String XIAOMI_DELIVERY_TYPE = "xps";
    public static final int XIAOMI_MIUI_DEVICES = 2;
    public static final String XIAOMI_SDK_CLASS = "com.xiaomi.mipush.sdk.MiPushClient";
    public static final String XPS_PROPERTY_REG_ID = "xps_token";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface CTPushProviderClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DeliveryType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Platform {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PushMessagingClass {
    }

    /* loaded from: classes2.dex */
    public enum PushType {
        FCM("fcm", PushConstants.FCM_PROPERTY_REG_ID, PushConstants.CT_FIREBASE_PROVIDER_CLASS, PushConstants.FIREBASE_SDK_CLASS, 1, ""),
        XPS(PushConstants.XIAOMI_DELIVERY_TYPE, PushConstants.XPS_PROPERTY_REG_ID, PushConstants.CT_XIAOMI_PROVIDER_CLASS, PushConstants.XIAOMI_SDK_CLASS, 1, ""),
        HPS(PushConstants.HMS_DELIVERY_TYPE, PushConstants.HPS_PROPERTY_REG_ID, PushConstants.CT_HUAWEI_PROVIDER_CLASS, PushConstants.HUAWEI_SDK_CLASS, 1, ""),
        BPS(PushConstants.BAIDU_DELIVERY_TYPE, PushConstants.BPS_PROPERTY_REG_ID, PushConstants.CT_BAIDU_PROVIDER_CLASS, PushConstants.BAIDU_SDK_CLASS, 1, ""),
        ADM(PushConstants.ADM_DELIVERY_TYPE, PushConstants.ADM_PROPERTY_REG_ID, PushConstants.CT_ADM_PROVIDER_CLASS, PushConstants.ADM_SDK_CLASS, 1, "");
        
        private final String ctProviderClassName;
        private final String messagingSDKClassName;
        private int runningDevices;
        private String serverRegion;
        private final String tokenPrefKey;
        private final String type;

        PushType(String str, String str2, String str3, String str4, int i, String str5) {
            this.type = str;
            this.tokenPrefKey = str2;
            this.ctProviderClassName = str3;
            this.messagingSDKClassName = str4;
            this.runningDevices = i;
            this.serverRegion = str5;
        }

        public String getCtProviderClassName() {
            return this.ctProviderClassName;
        }

        public String getMessagingSDKClassName() {
            return this.messagingSDKClassName;
        }

        public int getRunningDevices() {
            return this.runningDevices;
        }

        public String getServerRegion() {
            Logger.v("PushConstants: getServerRegion called, returning region:" + this.serverRegion);
            return this.serverRegion;
        }

        public String getTokenPrefKey() {
            return this.tokenPrefKey;
        }

        public String getType() {
            return this.type;
        }

        public void setRunningDevices(int i) {
            this.runningDevices = i;
        }

        public void setServerRegion(@NonNull String str) {
            Logger.v("PushConstants: setServerRegion called with region:" + str);
            this.serverRegion = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return " [PushType:" + name() + "] ";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RegKeyType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface XiaomiPush {
    }
}
