package com.coveiot.android.remotecommandframework.alexa;

import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class CommandErrorResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Command f5591a;
    @NotNull
    public final CommandResponseListener b;

    public CommandErrorResponse(@NotNull Command command, @NotNull CommandResponseListener commandResponseListener) {
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        this.f5591a = command;
        this.b = commandResponseListener;
    }

    @NotNull
    public final Command getCommand() {
        return this.f5591a;
    }

    @NotNull
    public final CommandResponseListener getCommandResponseListener() {
        return this.b;
    }

    public void sendCommandFailed(@Nullable String str) {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.COMMAND_FAILED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendCommandNotSupported() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.COMMAND_NOT_SUPPORTED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendCommandParsingFailed() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.COMMAND_PARSING_FAILED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendDeviceNotConnected() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.DEVICE_NOT_CONNECTED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendInvalidCommandData() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.INVALID_COMMAND_DATA.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendMaxLimitExceeded() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.MAXIMUM_LIMIT_EXCEED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendNotificationAccessNotEnabled() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.NOTIFICATION_ACCESS_NOT_ENABLED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendSettingAlreadyApplied() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.SETTINGS_ALREADY_APPLIED.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }

    public void sendSyncIsInProgress() {
        this.f5591a.setStatus(ResponseType.ERROR.getStatus());
        this.f5591a.setMessage(ErrorType.SYNC_IS_IN_PROGRESS.getType());
        this.f5591a.setData(null);
        this.b.onResponse(this.f5591a);
    }
}
