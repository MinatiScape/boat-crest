package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SMeasurementUnitInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface IMeasurementUnitPreferenceHandler extends IPreferenceHandler {
    boolean isValidInput(@NotNull Context context, @NotNull SMeasurementUnitInfo sMeasurementUnitInfo);

    void update(@NotNull Context context, @NotNull SMeasurementUnitInfo sMeasurementUnitInfo, @NotNull SuccessListener successListener);
}
