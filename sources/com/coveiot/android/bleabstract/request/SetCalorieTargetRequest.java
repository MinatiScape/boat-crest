package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetCalorieTargetRequest extends BleBaseRequest {
    public int f = 500;
    public double g;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Object f3523a;
        public int b = 500;
        public double c;

        @NotNull
        public final SetCalorieTargetRequest build() {
            SetCalorieTargetRequest setCalorieTargetRequest = new SetCalorieTargetRequest();
            setCalorieTargetRequest.setTarget$bleabstract_release(this.b);
            setCalorieTargetRequest.setBmr$bleabstract_release(this.c);
            return setCalorieTargetRequest;
        }

        public final double getBmr$bleabstract_release() {
            return this.c;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.f3523a;
        }

        public final int getTarget$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final Builder setBMR(double d) {
            this.c = d;
            return this;
        }

        public final void setBmr$bleabstract_release(double d) {
            this.c = d;
        }

        @NotNull
        public final Builder setId() {
            return this;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.f3523a = obj;
        }

        @NotNull
        public final Builder setTarget(int i) {
            this.b = i;
            return this;
        }

        public final void setTarget$bleabstract_release(int i) {
            this.b = i;
        }
    }

    public final double getBMR() {
        return this.g;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_CALORIE_TARGET;
    }

    public final double getBmr$bleabstract_release() {
        return this.g;
    }

    public final int getTarget() {
        return this.f;
    }

    public final int getTarget$bleabstract_release() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setBmr$bleabstract_release(double d) {
        this.g = d;
    }

    public final void setTarget$bleabstract_release(int i) {
        this.f = i;
    }
}
