package com.coveiot.android.remotecommandframework.alexa.response.model;

import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
/* loaded from: classes6.dex */
public final class CommandInputError extends RemoteBaseError {
    public CommandInputError() {
        setStatus(ErrorType.INVALID_COMMAND_DATA.getType());
    }
}
