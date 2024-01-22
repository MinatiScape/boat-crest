package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.api.request.SetWalkTargetReq;
/* loaded from: classes2.dex */
public class ActivityPauseResumeRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3471a = true;

        public static SetWalkTargetReq.Builder aSetWalkTargetReq() {
            return new SetWalkTargetReq.Builder();
        }

        public ActivityPauseResumeRequest build() {
            ActivityPauseResumeRequest activityPauseResumeRequest = new ActivityPauseResumeRequest();
            activityPauseResumeRequest.f = this.f3471a;
            return activityPauseResumeRequest;
        }

        public Builder pauseSession(boolean z) {
            this.f3471a = z;
            return this;
        }

        public Builder setId() {
            return this;
        }
    }

    public boolean isPause() {
        return this.f;
    }
}
