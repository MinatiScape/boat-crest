package com.coveiot.android.remotecommandframework.alexa.response.model;

import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
/* loaded from: classes6.dex */
public final class CommandParsingFailed extends RemoteBaseError {
    public CommandParsingFailed() {
        setStatus(ErrorType.COMMAND_PARSING_FAILED.getType());
    }
}
