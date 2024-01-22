package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SLiftWristToViewInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class LiftWristToViewPreferenceHandler implements ILiftWristToViewPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sLiftWristToViewInfo, "sLiftWristToViewInfo");
        return Intrinsics.areEqual(Boolean.valueOf(sLiftWristToViewInfo.getActive()), UserDataManager.getInstance(context).isLiftWristToViewEnable());
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sLiftWristToViewInfo, "sLiftWristToViewInfo");
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler
    public void update(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo, @NotNull SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sLiftWristToViewInfo, "sLiftWristToViewInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        UserDataManager.getInstance(context).saveLiftWristPref(Boolean.valueOf(sLiftWristToViewInfo.getActive()));
    }
}
