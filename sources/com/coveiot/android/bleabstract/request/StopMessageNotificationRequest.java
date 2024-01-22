package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class StopMessageNotificationRequest extends BleBaseRequest {
    public NotificationType appNotificationType;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public NotificationType appNotificationType;

        public StopMessageNotificationRequest build() {
            StopMessageNotificationRequest stopMessageNotificationRequest = new StopMessageNotificationRequest();
            stopMessageNotificationRequest.appNotificationType = this.appNotificationType;
            return stopMessageNotificationRequest;
        }

        public Builder setNotificationType(NotificationType notificationType) {
            this.appNotificationType = notificationType;
            return this;
        }
    }

    public NotificationType getAppNotificationType() {
        return this.appNotificationType;
    }
}
