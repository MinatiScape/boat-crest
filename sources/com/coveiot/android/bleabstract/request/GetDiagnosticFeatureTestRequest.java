package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeature;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.sdk.ble.utils.BleUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetDiagnosticFeatureTestRequest extends BleBaseRequest {
    @Nullable
    public WatchDiagnosticFeature f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable
        public Object c;

        /* renamed from: a  reason: collision with root package name */
        public int f3493a = WatchDiagnosticFeatureType.RESERVED.getId();
        public int b = BleUtils.convertHexTo565("#ffffff");
        public int d = 3;
        public int e = 60;
        public int f = 3;
        public int g = 1;

        @NotNull
        public final GetDiagnosticFeatureTestRequest build() {
            GetDiagnosticFeatureTestRequest getDiagnosticFeatureTestRequest = new GetDiagnosticFeatureTestRequest();
            WatchDiagnosticFeature watchDiagnosticFeature = new WatchDiagnosticFeature(WatchDiagnosticFeatureType.getById(this.f3493a));
            watchDiagnosticFeature.setTimeout(this.e);
            watchDiagnosticFeature.setColor(this.b);
            watchDiagnosticFeature.setVibrationCount(this.f);
            watchDiagnosticFeature.setButtonPosition(this.d);
            watchDiagnosticFeature.setSensorType(this.g);
            getDiagnosticFeatureTestRequest.f = watchDiagnosticFeature;
            return getDiagnosticFeatureTestRequest;
        }

        @NotNull
        public final Builder setButtonPosition(int i) {
            this.d = i;
            return this;
        }

        @NotNull
        public final Builder setColor(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setFeatureType(@NotNull WatchDiagnosticFeatureType watchDiagnosticFeatureType) {
            Intrinsics.checkNotNullParameter(watchDiagnosticFeatureType, "watchDiagnosticFeatureType");
            this.f3493a = watchDiagnosticFeatureType.getId();
            return this;
        }

        public final void setId(@NotNull Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.c = id;
        }

        @NotNull
        public final Builder setSensorType(int i) {
            this.g = i;
            return this;
        }

        @NotNull
        public final Builder setSessionID(int i, int i2) {
            this.f3493a = i;
            this.b = i2;
            return this;
        }

        @NotNull
        public final Builder setTimeout(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setVibrationCount(int i) {
            this.f = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_DIAGNOSTIC_FEATURE_TEST;
    }

    @Nullable
    public final WatchDiagnosticFeature getWatchDiagnosticFeature() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }
}
