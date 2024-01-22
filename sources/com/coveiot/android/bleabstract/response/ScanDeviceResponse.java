package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ScanDeviceResponse extends BleBaseResponse {
    @Nullable
    public List<? extends BleDevice> d;
    public boolean e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanDeviceResponse(@NotNull BleBaseRequest baseRequest) {
        super(baseRequest);
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
    }

    @Nullable
    public final List<BleDevice> getBluetoothDevices() {
        return this.d;
    }

    public final boolean isScanComplete() {
        return this.e;
    }

    public final void setBluetoothDevices(@Nullable List<? extends BleDevice> list) {
        this.d = list;
    }

    public final void setScanComplete(boolean z) {
        this.e = z;
    }
}
