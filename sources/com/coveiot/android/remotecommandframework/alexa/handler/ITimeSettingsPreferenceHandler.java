package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.STimeFormatInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface ITimeSettingsPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo);

    boolean isValidInput(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo);

    void update(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo, @NotNull SuccessListener successListener);
}
