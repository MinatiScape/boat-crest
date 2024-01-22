package com.coveiot.android.navigation.services;

import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.utils.utility.LogHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class CoveNavigationService$setNavigationAliveTimerOnBand$1 implements SettingsResultListener {
    public final /* synthetic */ Function1<Boolean, Unit> h;
    public final /* synthetic */ CoveNavigationService i;

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        String str;
        Intrinsics.checkNotNullParameter(error, "error");
        this.h.invoke(Boolean.FALSE);
        str = this.i.l;
        LogHelper.d(str, "setNavigationAliveTimerOnBand onSettingsError " + error.getErrorMsg());
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        String str;
        Intrinsics.checkNotNullParameter(response, "response");
        this.h.invoke(Boolean.TRUE);
        str = this.i.l;
        LogHelper.d(str, "setNavigationAliveTimerOnBand onSettingsResponse");
    }
}
