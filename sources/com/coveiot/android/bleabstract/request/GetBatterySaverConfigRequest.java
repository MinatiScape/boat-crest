package com.coveiot.android.bleabstract.request;

import androidx.annotation.Nullable;
import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class GetBatterySaverConfigRequest extends BleBaseRequest {
    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.GET_BATTERY_SAVER_INFO;
    }
}