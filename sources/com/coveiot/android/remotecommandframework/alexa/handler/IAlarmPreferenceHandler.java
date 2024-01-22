package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface IAlarmPreferenceHandler extends IPreferenceHandler {
    int getAlarmId(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo, int i);

    boolean isAlreadySet(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo);

    boolean isAtMaximum(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo, int i);

    boolean isValidInput(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo);

    void update(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo, int i, @NotNull SuccessListener successListener);

    void updatePreferenceWithLatestBandData(@NotNull Context context, @NotNull List<SAlarmInfo> list, @NotNull SuccessListener successListener);
}
