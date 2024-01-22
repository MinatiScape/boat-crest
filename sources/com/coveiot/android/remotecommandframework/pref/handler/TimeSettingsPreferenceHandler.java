package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.STimeFormatInfo;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class TimeSettingsPreferenceHandler implements ITimeSettingsPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sTimeFormatInfo, "sTimeFormatInfo");
        Boolean is12HourFormat = UserDataManager.getInstance(context).isTimeIn12HourFormat();
        Intrinsics.checkNotNullExpressionValue(is12HourFormat, "is12HourFormat");
        return Intrinsics.areEqual(sTimeFormatInfo.getClockFormat(), (is12HourFormat.booleanValue() ? Constants.TIME_FORMAT_12H : Constants.TIME_FORMAT_24H).getValue());
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sTimeFormatInfo, "sTimeFormatInfo");
        return sTimeFormatInfo.getClockFormat() != null && (Intrinsics.areEqual(sTimeFormatInfo.getClockFormat(), Constants.TIME_FORMAT_24H.getValue()) || Intrinsics.areEqual(sTimeFormatInfo.getClockFormat(), Constants.TIME_FORMAT_12H.getValue()));
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler
    public void update(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo, @NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sTimeFormatInfo, "sTimeFormatInfo");
        Intrinsics.checkNotNullParameter(listener, "listener");
        UserDataManager.getInstance(context).saveHourFormatPref(Boolean.valueOf(Intrinsics.areEqual(sTimeFormatInfo.getClockFormat(), Constants.TIME_FORMAT_12H.getValue())));
    }
}
