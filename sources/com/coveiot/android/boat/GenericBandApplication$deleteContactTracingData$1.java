package com.coveiot.android.boat;

import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class GenericBandApplication$deleteContactTracingData$1 implements SettingsResultListener {
    public final /* synthetic */ GenericBandApplication h;

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        String tag = this.h.getTAG();
        LogHelper.d(tag, "delete contact tracing data cmd failed " + error.getErrorMsg());
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        LogHelper.d(this.h.getTAG(), "delete contact tracing data cmd Success");
    }
}
