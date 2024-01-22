package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class FitnessConfigPreferenceHandler implements IFitnessConfigPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler
    public boolean isDistanceUnitInMile(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        return isDistanceUnitInMile.booleanValue();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler
    public boolean isValidInputHeight(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return 70 <= i && i < 273;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler
    public boolean isValidInputWeight(@NotNull Context context, double d) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = (int) d;
        return 10 <= i && i < 451;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler
    public void update(@NotNull Context context, int i, double d, @NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        userDetails.setHeight(String.valueOf(i));
        userDetails.setWeight(String.valueOf((int) d));
        SessionManager.getInstance(context).createLoginSession(userDetails);
    }
}
