package com.coveiot.android.remotecommandframework.alexa.response.model;

import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
/* loaded from: classes6.dex */
public final class CommandSuccess extends RemoteBaseResponse {
    public CommandSuccess() {
        setStatus(ResponseType.SUCCESS.getStatus());
    }
}
