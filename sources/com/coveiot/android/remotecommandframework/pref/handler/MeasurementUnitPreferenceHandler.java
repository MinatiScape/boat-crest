package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SMeasurementUnitInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class MeasurementUnitPreferenceHandler implements IMeasurementUnitPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SMeasurementUnitInfo sMeasurementUnitInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sMeasurementUnitInfo, "sMeasurementUnitInfo");
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler
    public void update(@NotNull Context context, @NotNull SMeasurementUnitInfo sMeasurementUnitInfo, @NotNull SuccessListener successListener) {
        Integer unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sMeasurementUnitInfo, "sMeasurementUnitInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        UserDataManager.getInstance(context).saveDistanceUnitPref(Boolean.valueOf(sMeasurementUnitInfo.getUnit() == null || ((unit = sMeasurementUnitInfo.getUnit()) != null && unit.intValue() == 0)));
    }
}
