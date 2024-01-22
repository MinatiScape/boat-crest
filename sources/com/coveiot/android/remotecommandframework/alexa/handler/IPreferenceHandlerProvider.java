package com.coveiot.android.remotecommandframework.alexa.handler;

import android.content.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface IPreferenceHandlerProvider {
    @Nullable
    IPreferenceHandler getPreferenceHandler(@NotNull Context context, @NotNull String str);
}
