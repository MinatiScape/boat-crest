package com.coveiot.android.remotecommandframework.alexa.handler;

import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class PreferenceHandlerProviderNotFound extends Exception {
    @Override // java.lang.Throwable
    @Nullable
    public String getMessage() {
        return ErrorType.COMMAND_NOT_IMPLEMENTED.getType();
    }
}
