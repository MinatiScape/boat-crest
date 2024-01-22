package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface IDNDPreferenceHandler extends IPreferenceHandler {
    boolean isAlreadySet(@NotNull Context context, @NotNull SDNDInfo sDNDInfo);

    boolean isValidInput(@NotNull Context context, @NotNull SDNDInfo sDNDInfo);

    void update(@NotNull Context context, @NotNull SDNDInfo sDNDInfo, @NotNull SuccessListener successListener);

    void updatePreferenceWithLatestBandData(@NotNull Context context, @NotNull SDNDInfo sDNDInfo, @NotNull SuccessListener successListener);
}
