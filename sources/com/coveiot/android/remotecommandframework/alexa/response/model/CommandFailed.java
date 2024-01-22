package com.coveiot.android.remotecommandframework.alexa.response.model;

import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
/* loaded from: classes6.dex */
public final class CommandFailed extends RemoteBaseError {
    public CommandFailed() {
        setStatus(ErrorType.COMMAND_FAILED.getType());
    }
}
