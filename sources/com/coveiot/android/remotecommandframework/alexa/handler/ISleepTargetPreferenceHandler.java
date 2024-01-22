package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SSleepTargetInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface ISleepTargetPreferenceHandler extends IPreferenceHandler {
    int getGoalId(@NotNull Context context);

    boolean isValidInput(@NotNull Context context, @NotNull SSleepTargetInfo sSleepTargetInfo);

    void update(@NotNull Context context, @NotNull SSleepTargetInfo sSleepTargetInfo, @NotNull SuccessListener successListener);
}
