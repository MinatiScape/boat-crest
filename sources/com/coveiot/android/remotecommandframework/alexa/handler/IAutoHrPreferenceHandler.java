package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SAutoHrInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface IAutoHrPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo);

    boolean isValidInput(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo);

    void update(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo, @NotNull SuccessListener successListener);
}
