package com.coveiot.android.remotecommandframework.alexa.response.model;

import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
/* loaded from: classes6.dex */
public final class CommandNotFound extends RemoteBaseError {
    public CommandNotFound() {
        setStatus(ErrorType.COMMAND_NOT_SUPPORTED.getType());
    }
}
