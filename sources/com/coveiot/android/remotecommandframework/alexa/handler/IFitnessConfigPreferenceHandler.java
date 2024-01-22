package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface IFitnessConfigPreferenceHandler extends IPreferenceHandler {
    boolean isDistanceUnitInMile(@NotNull Context context);

    boolean isValidInputHeight(@NotNull Context context, int i);

    boolean isValidInputWeight(@NotNull Context context, double d);

    void update(@NotNull Context context, int i, double d, @NotNull SuccessListener successListener);
}
