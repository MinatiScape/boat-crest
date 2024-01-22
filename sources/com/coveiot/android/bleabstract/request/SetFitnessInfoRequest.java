package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
/* loaded from: classes2.dex */
public class SetFitnessInfoRequest extends BleBaseRequest {
    public int f;
    public double g;
    public boolean j;
    public boolean l;
    public int m;
    public double h = -1.0d;
    public double i = -1.0d;
    public int k = 30;
    public MeasurementUnitType n = MeasurementUnitType.METRIC;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3532a;
        public double b;
        public boolean e;
        public boolean g;
        public int h;
        public double c = -1.0d;
        public double d = -1.0d;
        public int f = 30;
        public MeasurementUnitType i = MeasurementUnitType.METRIC;

        public Builder(int i, double d) {
            this.f3532a = i;
            this.b = d;
        }

        public SetFitnessInfoRequest builder() {
            SetFitnessInfoRequest setFitnessInfoRequest = new SetFitnessInfoRequest();
            setFitnessInfoRequest.f = this.f3532a;
            setFitnessInfoRequest.g = this.b;
            setFitnessInfoRequest.h = this.c;
            setFitnessInfoRequest.i = this.d;
            setFitnessInfoRequest.j = this.e;
            setFitnessInfoRequest.k = this.f;
            setFitnessInfoRequest.n = this.i;
            setFitnessInfoRequest.l = this.g;
            setFitnessInfoRequest.m = this.h;
            return setFitnessInfoRequest;
        }

        public Builder setAge(int i) {
            this.f = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.f3532a = i;
            return this;
        }

        public Builder setLeftHand(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setMale(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setRunStride(double d) {
            this.d = d;
            return this;
        }

        public Builder setSkinColour(int i) {
            this.h = i;
            return this;
        }

        public Builder setStride(double d) {
            this.c = d;
            return this;
        }

        public Builder setUnitType(MeasurementUnitType measurementUnitType) {
            this.i = measurementUnitType;
            return this;
        }

        public Builder setWeight(double d) {
            this.b = d;
            return this;
        }
    }

    public int getAge() {
        return this.k;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_FITNESS_PERSONAL_INFO;
    }

    public int getHeight() {
        return this.f;
    }

    public double getRunstride() {
        return this.i;
    }

    public int getSkinColour() {
        return this.m;
    }

    public double getStride() {
        return this.h;
    }

    public MeasurementUnitType getUnitType() {
        return this.n;
    }

    public double getWeight() {
        return this.g;
    }

    public boolean isLeftHand() {
        return this.l;
    }

    public boolean isMale() {
        return this.j;
    }
}
