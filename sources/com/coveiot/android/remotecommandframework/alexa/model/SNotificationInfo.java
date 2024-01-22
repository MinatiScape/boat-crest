package com.coveiot.android.remotecommandframework.alexa.model;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
import com.szabh.smable3.entity.BleNotification;
import com.szabh.smable3.entity.BleNotificationSettings;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SNotificationInfo extends SCommandInfo {
    @SerializedName(NotificationCompat.CATEGORY_CALL)
    @Nullable
    private CallBean call;
    @SerializedName("otherApps")
    @Nullable
    private OtherApps otherApps;
    @SerializedName(BleNotificationSettings.SMS)
    @Nullable
    private SmsBean sms;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String APP_ID_TELEGRAM = BleNotificationSettings.TELEGRAM;
    @NotNull
    private static final String APP_ID_LINKEDIN = BleNotificationSettings.LINKED_IN;
    @NotNull
    private static final String APP_ID_FACEBOOK = "facebook";
    @NotNull
    private static final String APP_ID_FACEBOOK_MESSENGER = "fb_messenger";
    @NotNull
    private static final String APP_ID_GMAIL = "gmail";
    @NotNull
    private static final String APP_ID_INSTAGRAM = BleNotificationSettings.INSTAGRAM;
    @NotNull
    private static final String APP_ID_QQ_MESSENGER = "qq_messenger";
    @NotNull
    private static final String APP_ID_SNAPCHAT = "snapchat";
    @NotNull
    private static final String APP_ID_TWITTER = BleNotificationSettings.TWITTER;
    @NotNull
    private static final String APP_ID_WECHAT = BleNotificationSettings.WE_CHAT;
    @NotNull
    private static final String APP_ID_WHATSAPP = BleNotificationSettings.WHATS_APP;
    @NotNull
    private static final String APP_ID_SKYPE = BleNotificationSettings.SKYPE;
    @NotNull
    private static final String APP_ID_QZONE = "qzone";
    @NotNull
    private static final String APP_ID_VKCLIENT = "vkontakte";
    @NotNull
    private static final String APP_ID_LINE_MESSENGER = "line_messenger";
    @NotNull
    private static final String APP_ID_CALENDAR = "calendar";
    @NotNull
    private static final String PKG_ID_TELEGRAM = BleNotification.TELEGRAM;
    @NotNull
    private static final String PKG_ID_LINKEDIN = BleNotification.LINKED_IN;
    @NotNull
    private static final String PKG_ID_FACEBOOK = BleNotification.FACEBOOK;
    @NotNull
    private static final String PKG_ID_FACEBOOK_MESSENGER = BleNotification.FACEBOOK_MESSENGER;
    @NotNull
    private static final String PKG_ID_GMAIL = BleNotification.GMAIL;
    @NotNull
    private static final String PKG_ID_INSTAGRAM = BleNotification.INSTAGRAM;
    @NotNull
    private static final String PKG_ID_QQ_MESSENGER = BleNotification.QQ;
    @NotNull
    private static final String PKG_ID_SNAPCHAT = BleNotification.SNAPCHAT;
    @NotNull
    private static final String PKG_ID_TWITTER = BleNotification.TWITTER;
    @NotNull
    private static final String PKG_ID_WECHAT = BleNotification.WE_CHAT;
    @NotNull
    private static final String PKG_ID_WHATSAPP = BleNotification.WHATS_APP;
    @NotNull
    private static final String PKG_ID_SKYPE = BleNotification.SKYPE;
    @NotNull
    private static final String PKG_ID_QZONE = "com.o2m.gsk";
    @NotNull
    private static final String PKG_ID_VKCLIENT = "com.vkontakte.android";
    @NotNull
    private static final String PKG_ID_LINE_MESSENGER = BleNotification.LINE;
    @NotNull
    private static final String PKG_ID_CALENDAR = "com.google.android.calendar";

    /* loaded from: classes6.dex */
    public static final class AppBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Nullable
        private Boolean active;
        @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_ID)
        @Nullable
        private String appId;

        @Nullable
        public final Boolean getActive() {
            return this.active;
        }

        @Nullable
        public final String getAppId() {
            return this.appId;
        }

        public final void setActive(@Nullable Boolean bool) {
            this.active = bool;
        }

        public final void setAppId(@Nullable String str) {
            this.appId = str;
        }
    }

    /* loaded from: classes6.dex */
    public static final class CallBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Nullable
        private Boolean active;

        @Nullable
        public final Boolean getActive() {
            return this.active;
        }

        public final void setActive(@Nullable Boolean bool) {
            this.active = bool;
        }
    }

    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getAPP_ID_CALENDAR() {
            return SNotificationInfo.APP_ID_CALENDAR;
        }

        @NotNull
        public final String getAPP_ID_FACEBOOK() {
            return SNotificationInfo.APP_ID_FACEBOOK;
        }

        @NotNull
        public final String getAPP_ID_FACEBOOK_MESSENGER() {
            return SNotificationInfo.APP_ID_FACEBOOK_MESSENGER;
        }

        @NotNull
        public final String getAPP_ID_GMAIL() {
            return SNotificationInfo.APP_ID_GMAIL;
        }

        @NotNull
        public final String getAPP_ID_INSTAGRAM() {
            return SNotificationInfo.APP_ID_INSTAGRAM;
        }

        @NotNull
        public final String getAPP_ID_LINE_MESSENGER() {
            return SNotificationInfo.APP_ID_LINE_MESSENGER;
        }

        @NotNull
        public final String getAPP_ID_LINKEDIN() {
            return SNotificationInfo.APP_ID_LINKEDIN;
        }

        @NotNull
        public final String getAPP_ID_QQ_MESSENGER() {
            return SNotificationInfo.APP_ID_QQ_MESSENGER;
        }

        @NotNull
        public final String getAPP_ID_QZONE() {
            return SNotificationInfo.APP_ID_QZONE;
        }

        @NotNull
        public final String getAPP_ID_SKYPE() {
            return SNotificationInfo.APP_ID_SKYPE;
        }

        @NotNull
        public final String getAPP_ID_SNAPCHAT() {
            return SNotificationInfo.APP_ID_SNAPCHAT;
        }

        @NotNull
        public final String getAPP_ID_TELEGRAM() {
            return SNotificationInfo.APP_ID_TELEGRAM;
        }

        @NotNull
        public final String getAPP_ID_TWITTER() {
            return SNotificationInfo.APP_ID_TWITTER;
        }

        @NotNull
        public final String getAPP_ID_VKCLIENT() {
            return SNotificationInfo.APP_ID_VKCLIENT;
        }

        @NotNull
        public final String getAPP_ID_WECHAT() {
            return SNotificationInfo.APP_ID_WECHAT;
        }

        @NotNull
        public final String getAPP_ID_WHATSAPP() {
            return SNotificationInfo.APP_ID_WHATSAPP;
        }

        @NotNull
        public final String getPKG_ID_CALENDAR() {
            return SNotificationInfo.PKG_ID_CALENDAR;
        }

        @NotNull
        public final String getPKG_ID_FACEBOOK() {
            return SNotificationInfo.PKG_ID_FACEBOOK;
        }

        @NotNull
        public final String getPKG_ID_FACEBOOK_MESSENGER() {
            return SNotificationInfo.PKG_ID_FACEBOOK_MESSENGER;
        }

        @NotNull
        public final String getPKG_ID_GMAIL() {
            return SNotificationInfo.PKG_ID_GMAIL;
        }

        @NotNull
        public final String getPKG_ID_INSTAGRAM() {
            return SNotificationInfo.PKG_ID_INSTAGRAM;
        }

        @NotNull
        public final String getPKG_ID_LINE_MESSENGER() {
            return SNotificationInfo.PKG_ID_LINE_MESSENGER;
        }

        @NotNull
        public final String getPKG_ID_LINKEDIN() {
            return SNotificationInfo.PKG_ID_LINKEDIN;
        }

        @NotNull
        public final String getPKG_ID_QQ_MESSENGER() {
            return SNotificationInfo.PKG_ID_QQ_MESSENGER;
        }

        @NotNull
        public final String getPKG_ID_QZONE() {
            return SNotificationInfo.PKG_ID_QZONE;
        }

        @NotNull
        public final String getPKG_ID_SKYPE() {
            return SNotificationInfo.PKG_ID_SKYPE;
        }

        @NotNull
        public final String getPKG_ID_SNAPCHAT() {
            return SNotificationInfo.PKG_ID_SNAPCHAT;
        }

        @NotNull
        public final String getPKG_ID_TELEGRAM() {
            return SNotificationInfo.PKG_ID_TELEGRAM;
        }

        @NotNull
        public final String getPKG_ID_TWITTER() {
            return SNotificationInfo.PKG_ID_TWITTER;
        }

        @NotNull
        public final String getPKG_ID_VKCLIENT() {
            return SNotificationInfo.PKG_ID_VKCLIENT;
        }

        @NotNull
        public final String getPKG_ID_WECHAT() {
            return SNotificationInfo.PKG_ID_WECHAT;
        }

        @NotNull
        public final String getPKG_ID_WHATSAPP() {
            return SNotificationInfo.PKG_ID_WHATSAPP;
        }
    }

    /* loaded from: classes6.dex */
    public static final class OtherApps {
        @SerializedName("apps")
        @Nullable
        private List<AppBean> apps;
        @SerializedName("enableAll")
        @Nullable
        private Boolean enableAll;

        @Nullable
        public final List<AppBean> getApps() {
            return this.apps;
        }

        @Nullable
        public final Boolean getEnableAll() {
            return this.enableAll;
        }

        public final void setApps(@Nullable List<AppBean> list) {
            this.apps = list;
        }

        public final void setEnableAll(@Nullable Boolean bool) {
            this.enableAll = bool;
        }
    }

    /* loaded from: classes6.dex */
    public static final class SmsBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Nullable
        private Boolean active;

        @Nullable
        public final Boolean getActive() {
            return this.active;
        }

        public final void setActive(@Nullable Boolean bool) {
            this.active = bool;
        }
    }

    @Nullable
    public final CallBean getCall() {
        return this.call;
    }

    @Nullable
    public final OtherApps getOtherApps() {
        return this.otherApps;
    }

    @Nullable
    public final SmsBean getSms() {
        return this.sms;
    }

    public final void setCall(@Nullable CallBean callBean) {
        this.call = callBean;
    }

    public final void setOtherApps(@Nullable OtherApps otherApps) {
        this.otherApps = otherApps;
    }

    public final void setSms(@Nullable SmsBean smsBean) {
        this.sms = smsBean;
    }
}
