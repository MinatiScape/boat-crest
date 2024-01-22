package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.VibrationModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MotorVibrationRequest extends BleBaseRequest {
    public int f;
    @Nullable
    public List<? extends VibrationModel> g;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3505a;
        @Nullable
        public List<? extends VibrationModel> b;

        @NotNull
        public final MotorVibrationRequest build() {
            MotorVibrationRequest motorVibrationRequest = new MotorVibrationRequest();
            motorVibrationRequest.setTimes(this.f3505a);
            motorVibrationRequest.g = this.b;
            return motorVibrationRequest;
        }

        public final int getTimes$bleabstract_release() {
            return this.f3505a;
        }

        @Nullable
        public final List<VibrationModel> getVibrationModelList$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setTimes(int i) {
            this.f3505a = i;
            return this;
        }

        public final void setTimes$bleabstract_release(int i) {
            this.f3505a = i;
        }

        @NotNull
        public final Builder setVibrationModelList(@NotNull List<? extends VibrationModel> vibrationModel) {
            Intrinsics.checkNotNullParameter(vibrationModel, "vibrationModel");
            this.b = vibrationModel;
            return this;
        }

        public final void setVibrationModelList$bleabstract_release(@Nullable List<? extends VibrationModel> list) {
            this.b = list;
        }
    }

    public final int getTimes() {
        return this.f;
    }

    @Nullable
    public final List<VibrationModel> getVibrationModelList() {
        return this.g;
    }

    public final void setTimes(int i) {
        this.f = i;
    }
}
