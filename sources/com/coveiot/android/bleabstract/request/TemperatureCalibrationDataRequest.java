package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class TemperatureCalibrationDataRequest extends BleBaseRequest {
    public int f;
    public double g;
    @NotNull
    public String h = "";

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3570a;
        public double b;
        @NotNull
        public String c = "";

        @NotNull
        public final TemperatureCalibrationDataRequest build() {
            TemperatureCalibrationDataRequest temperatureCalibrationDataRequest = new TemperatureCalibrationDataRequest();
            temperatureCalibrationDataRequest.setTempCalculatingSign(this.f3570a);
            temperatureCalibrationDataRequest.setTemperature(this.b);
            temperatureCalibrationDataRequest.setTemperatureUnit(this.c);
            return temperatureCalibrationDataRequest;
        }

        public final int getTempCalculatingSign$bleabstract_release() {
            return this.f3570a;
        }

        public final double getTemperature$bleabstract_release() {
            return this.b;
        }

        @NotNull
        public final String getTemperatureUnit$bleabstract_release() {
            return this.c;
        }

        @NotNull
        public final Builder setTempCalculatingSign(int i) {
            this.f3570a = i;
            return this;
        }

        public final void setTempCalculatingSign$bleabstract_release(int i) {
            this.f3570a = i;
        }

        @NotNull
        public final Builder setTemperature(double d) {
            this.b = d;
            return this;
        }

        public final void setTemperature$bleabstract_release(double d) {
            this.b = d;
        }

        @NotNull
        public final Builder setTemperatureUnit(@NotNull String temperatureUnit) {
            Intrinsics.checkNotNullParameter(temperatureUnit, "temperatureUnit");
            this.c = temperatureUnit;
            return this;
        }

        public final void setTemperatureUnit$bleabstract_release(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.c = str;
        }
    }

    public final int getTempCalculatingSign() {
        return this.f;
    }

    public final double getTemperature() {
        return this.g;
    }

    @NotNull
    public final String getTemperatureUnit() {
        return this.h;
    }

    public final void setTempCalculatingSign(int i) {
        this.f = i;
    }

    public final void setTemperature(double d) {
        this.g = d;
    }

    public final void setTemperatureUnit(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.h = str;
    }
}
