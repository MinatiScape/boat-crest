package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BpCalibrationDataRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3477a;
        public int b;
        public int c;
        public int d;

        @NotNull
        public final BpCalibrationDataRequest build() {
            BpCalibrationDataRequest bpCalibrationDataRequest = new BpCalibrationDataRequest();
            bpCalibrationDataRequest.setSbpCalculatingSign(this.f3477a);
            bpCalibrationDataRequest.setSbp(this.b);
            bpCalibrationDataRequest.setDbpCalculatingSign(this.c);
            bpCalibrationDataRequest.setDbp(this.d);
            return bpCalibrationDataRequest;
        }

        public final int getDbp$bleabstract_release() {
            return this.d;
        }

        public final int getDbpCalculatingSign$bleabstract_release() {
            return this.c;
        }

        public final int getSbp$bleabstract_release() {
            return this.b;
        }

        public final int getSbpCalculatingSign$bleabstract_release() {
            return this.f3477a;
        }

        @NotNull
        public final Builder setDbp(int i) {
            this.d = i;
            return this;
        }

        public final void setDbp$bleabstract_release(int i) {
            this.d = i;
        }

        @NotNull
        public final Builder setDbpCalculatingSign(int i) {
            this.c = i;
            return this;
        }

        public final void setDbpCalculatingSign$bleabstract_release(int i) {
            this.c = i;
        }

        @NotNull
        public final Builder setSbp(int i) {
            this.b = i;
            return this;
        }

        public final void setSbp$bleabstract_release(int i) {
            this.b = i;
        }

        @NotNull
        public final Builder setSbpCalculatingSign(int i) {
            this.f3477a = i;
            return this;
        }

        public final void setSbpCalculatingSign$bleabstract_release(int i) {
            this.f3477a = i;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_BP_CALIBRATION;
    }

    public final int getDbp() {
        return this.i;
    }

    public final int getDbpCalculatingSign() {
        return this.h;
    }

    public final int getSbp() {
        return this.g;
    }

    public final int getSbpCalculatingSign() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setDbp(int i) {
        this.i = i;
    }

    public final void setDbpCalculatingSign(int i) {
        this.h = i;
    }

    public final void setSbp(int i) {
        this.g = i;
    }

    public final void setSbpCalculatingSign(int i) {
        this.f = i;
    }
}
