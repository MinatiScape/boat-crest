package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface ISedentaryPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull SSedentaryInfo sSedentaryInfo);

    boolean isValidInput(@NotNull Context context, @NotNull SSedentaryInfo sSedentaryInfo);

    void update(@NotNull Context context, @NotNull SSedentaryInfo sSedentaryInfo, @NotNull SuccessListener successListener);
}
