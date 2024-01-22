package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SNotificationInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface INotificationPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo);

    boolean isValidInput(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo);

    void update(@NotNull Context context, @NotNull SNotificationInfo sNotificationInfo, @NotNull SuccessListener successListener);
}
