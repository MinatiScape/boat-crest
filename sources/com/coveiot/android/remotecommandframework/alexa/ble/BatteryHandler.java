package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.handler.IBatteryPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.response.model.CommandSuccess;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class BatteryHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IBatteryPreferenceHandler d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IBatteryPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void getBattery() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                BleApiManager.getInstance(this.c).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.BatteryHandler$getBattery$1
                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        BatteryHandler.this.sendCommandFailed(error.getErrorMsg());
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onDataResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        if (response.getResponseData() instanceof BatteryLevelResponse) {
                            Object responseData = response.getResponseData();
                            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                            Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                            Intrinsics.checkNotNull(batteryLevel);
                            int intValue = batteryLevel.intValue();
                            CommandSuccess commandSuccess = new CommandSuccess();
                            commandSuccess.setData(Integer.valueOf(intValue));
                            BatteryHandler.this.getCommand().setRemoteBaseResponse(commandSuccess);
                            BatteryHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                            BatteryHandler.this.getCommand().setData(null);
                            BatteryHandler.this.getCommandResponseListener().onResponse(BatteryHandler.this.getCommand());
                            return;
                        }
                        BatteryHandler.this.sendCommandNotSupported();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                    public void onProgressUpdate(@NotNull ProgressData progress) {
                        Intrinsics.checkNotNullParameter(progress, "progress");
                    }
                });
                return;
            } else {
                sendSyncIsInProgress();
                return;
            }
        }
        sendDeviceNotConnected();
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final IBatteryPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SCommandInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        return null;
    }
}
