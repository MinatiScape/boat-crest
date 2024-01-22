package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SMeasurementUnitInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class MeasurementUnitHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IMeasurementUnitPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MeasurementUnitHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IMeasurementUnitPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = MeasurementUnitHandler.class.getSimpleName();
    }

    public final void a(SMeasurementUnitInfo sMeasurementUnitInfo) {
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final IMeasurementUnitPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setMeasurementUnit() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SMeasurementUnitInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                    SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                    if (setFitnessInfoRequest != null && this.d.isValidInput(this.c, sCommandInfo)) {
                        BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setFitnessInfoRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.MeasurementUnitHandler$setMeasurementUnit$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                MeasurementUnitHandler.this.sendCommandFailed(error.getErrorMsg());
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                MeasurementUnitHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                MeasurementUnitHandler.this.getCommand().setData(null);
                                MeasurementUnitHandler.this.getCommandResponseListener().onResponse(MeasurementUnitHandler.this.getCommand());
                                IMeasurementUnitPreferenceHandler preferenceHandler = MeasurementUnitHandler.this.getPreferenceHandler();
                                Context context = MeasurementUnitHandler.this.getContext();
                                final SMeasurementUnitInfo sMeasurementUnitInfo = sCommandInfo;
                                final MeasurementUnitHandler measurementUnitHandler = MeasurementUnitHandler.this;
                                preferenceHandler.update(context, sMeasurementUnitInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.MeasurementUnitHandler$setMeasurementUnit$1$onSettingsResponse$1
                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onFailure(@Nullable String str) {
                                        LogHelper.e(MeasurementUnitHandler.this.getTAG(), "Saving to preference failed.");
                                    }

                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onSuccess() {
                                        MeasurementUnitHandler.this.a(sMeasurementUnitInfo);
                                    }
                                });
                            }
                        });
                        return;
                    } else {
                        sendInvalidCommandData();
                        return;
                    }
                }
                sendCommandParsingFailed();
                return;
            }
            sendSyncIsInProgress();
            return;
        }
        sendDeviceNotConnected();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SMeasurementUnitInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SMeasurementUnitInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SMeasurementUnitInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
