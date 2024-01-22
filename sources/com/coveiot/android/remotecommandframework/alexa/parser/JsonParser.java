package com.coveiot.android.remotecommandframework.alexa.parser;

import com.coveiot.android.remotecommandframework.alexa.request.model.Creds;
import com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest;
import com.coveiot.android.remotecommandframework.alexa.response.model.DuplicateRequest;
import com.coveiot.android.remotecommandframework.alexa.response.model.RequestAck;
import com.google.gson.Gson;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class JsonParser {
    @NotNull
    public static final JsonParser INSTANCE = new JsonParser();

    public final boolean isEmptyJsonMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return !new JSONObject(message).keys().hasNext();
    }

    @Nullable
    public final Creds parseCredMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return (Creds) new Gson().fromJson(message, (Class<Object>) Creds.class);
    }

    @Nullable
    public final DuplicateRequest parseDuplicateMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return (DuplicateRequest) new Gson().fromJson(message, (Class<Object>) DuplicateRequest.class);
    }

    @Nullable
    public final RemoteRequest parseMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return (RemoteRequest) new Gson().fromJson(message, (Class<Object>) RemoteRequest.class);
    }

    @Nullable
    public final RequestAck parseMessageAck(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return (RequestAck) new Gson().fromJson(message, (Class<Object>) RequestAck.class);
    }
}
