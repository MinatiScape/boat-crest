package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.ActivityTypes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AutoActivityRecognitionSettingRequest extends BleBaseRequest {
    public boolean f;
    @NotNull
    public ActivityTypes g = ActivityTypes.WALK;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3475a;
        @NotNull
        public ActivityTypes b = ActivityTypes.WALK;

        @NotNull
        public final AutoActivityRecognitionSettingRequest build() {
            AutoActivityRecognitionSettingRequest autoActivityRecognitionSettingRequest = new AutoActivityRecognitionSettingRequest();
            autoActivityRecognitionSettingRequest.setActivityTpe(this.b);
            autoActivityRecognitionSettingRequest.setEnabled(this.f3475a);
            return autoActivityRecognitionSettingRequest;
        }

        @NotNull
        public final ActivityTypes getActivityTpe() {
            return this.b;
        }

        public final boolean isEnabled() {
            return this.f3475a;
        }

        public final void setActivityTpe(@NotNull ActivityTypes activityTypes) {
            Intrinsics.checkNotNullParameter(activityTypes, "<set-?>");
            this.b = activityTypes;
        }

        @NotNull
        public final Builder setActivityType(@NotNull ActivityTypes activityTypes) {
            Intrinsics.checkNotNullParameter(activityTypes, "activityTypes");
            this.b = activityTypes;
            return this;
        }

        public final void setEnabled(boolean z) {
            this.f3475a = z;
        }

        @NotNull
        public final Builder setIsEnabled(boolean z) {
            this.f3475a = z;
            return this;
        }
    }

    @NotNull
    public final ActivityTypes getActivityTpe() {
        return this.g;
    }

    public final boolean isEnabled() {
        return this.f;
    }

    public final void setActivityTpe(@NotNull ActivityTypes activityTypes) {
        Intrinsics.checkNotNullParameter(activityTypes, "<set-?>");
        this.g = activityTypes;
    }

    public final void setEnabled(boolean z) {
        this.f = z;
    }
}
