package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetMessageContentRequest extends BleBaseRequest {
    public NotificationType appNotificationType;
    public String customPackage;
    public String message;
    public String title;

    public NotificationType getAppNotificationType() {
        return this.appNotificationType;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        public NotificationType appNotificationType;
        public String customPackage;
        public String message;
        public String title;

        public SetMessageContentRequest build() {
            SetMessageContentRequest setMessageContentRequest = new SetMessageContentRequest();
            setMessageContentRequest.appNotificationType = this.appNotificationType;
            setMessageContentRequest.message = this.message;
            setMessageContentRequest.customPackage = this.customPackage;
            setMessageContentRequest.title = this.title;
            return setMessageContentRequest;
        }

        public Builder setCustomPackage(String str) {
            this.customPackage = str;
            return this;
        }

        public Builder setNotificationType(NotificationType notificationType, String str) {
            this.appNotificationType = notificationType;
            this.message = str;
            return this;
        }

        public Builder setNotificationType(NotificationType notificationType, String str, String str2) {
            this.appNotificationType = notificationType;
            this.message = str;
            this.title = str2;
            return this;
        }
    }
}
