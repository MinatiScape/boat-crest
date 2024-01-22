package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SportNotificationControlRequest extends BleBaseRequest {
    public boolean f = false;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3563a;

        public Builder(boolean z) {
            this.f3563a = false;
            this.f3563a = z;
        }

        public SportNotificationControlRequest build() {
            SportNotificationControlRequest sportNotificationControlRequest = new SportNotificationControlRequest();
            sportNotificationControlRequest.f = this.f3563a;
            return sportNotificationControlRequest;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SPORTS_NOTIFICATION_CONTROL;
    }

    public boolean isEnabled() {
        return this.f;
    }
}
