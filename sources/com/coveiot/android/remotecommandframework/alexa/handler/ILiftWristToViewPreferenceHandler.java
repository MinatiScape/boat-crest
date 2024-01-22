package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SLiftWristToViewInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface ILiftWristToViewPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo);

    boolean isValidInput(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo);

    void update(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo, @NotNull SuccessListener successListener);
}
