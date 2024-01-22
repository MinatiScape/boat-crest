package com.coveiot.android.remotecommandframework.alexa.ble;

import com.coveiot.android.remotecommandframework.alexa.CommandErrorResponse;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public abstract class CommandHandler extends CommandErrorResponse {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommandHandler(@NotNull Command command, @NotNull CommandResponseListener commandResponseListener) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
    }

    public void getAlarmFromBand(@NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        throw new CommandNotImplementedException();
    }

    public void getBattery() {
        throw new CommandNotImplementedException();
    }

    public void getDndFromBand(@NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        throw new CommandNotImplementedException();
    }

    @Nullable
    public abstract SCommandInfo getSCommandInfo(@NotNull Command command);

    public void setAlarm() {
        throw new CommandNotImplementedException();
    }

    public void setAutoHr() {
        throw new CommandNotImplementedException();
    }

    public void setDND() {
        throw new CommandNotImplementedException();
    }

    public void setFitnessConfig() {
        throw new CommandNotImplementedException();
    }

    public void setLiftWristToView() {
        throw new CommandNotImplementedException();
    }

    public void setMeasurementUnit() {
        throw new CommandNotImplementedException();
    }

    public void setNotification() {
        throw new CommandNotImplementedException();
    }

    public void setSedentaryReminder() {
        throw new CommandNotImplementedException();
    }

    public void setSleepTarget() {
        throw new CommandNotImplementedException();
    }

    public void setStepsTarget() {
        throw new CommandNotImplementedException();
    }

    public void setTimeFormat() {
        throw new CommandNotImplementedException();
    }

    public void syncData() {
        throw new CommandNotImplementedException();
    }
}
