package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.STimeFormatInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class TimeSettingHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final ITimeSettingsPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeSettingHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull ITimeSettingsPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = TimeSettingHandler.class.getSimpleName();
    }

    public final void a(STimeFormatInfo sTimeFormatInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            saveDeviceSpecificParamsReq.setClockFormat(sTimeFormatInfo.getClockFormat());
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.TimeSettingHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(TimeSettingHandler.this.getTAG(), "Clock format setting save to the server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    LogHelper.i(TimeSettingHandler.this.getTAG(), "Clock format setting saved to the server successfully.");
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final ITimeSettingsPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setTimeFormat() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                if (BleApiManager.getInstance(this.c).getBleApi() != null && BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isTimeFormatSettingsSupported()) {
                    final STimeFormatInfo sCommandInfo = getSCommandInfo(getCommand());
                    if (sCommandInfo != null) {
                        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                        SetHourFormatRequest setHourFormatRequest = (SetHourFormatRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                        if (setHourFormatRequest != null && this.d.isValidInput(this.c, sCommandInfo)) {
                            if (!this.d.isAlreadySet(this.c, sCommandInfo)) {
                                BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setHourFormatRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.TimeSettingHandler$setTimeFormat$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        TimeSettingHandler.this.sendCommandFailed(error.getErrorMsg());
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        TimeSettingHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                        TimeSettingHandler.this.getCommand().setData(null);
                                        TimeSettingHandler.this.getCommandResponseListener().onResponse(TimeSettingHandler.this.getCommand());
                                        ITimeSettingsPreferenceHandler preferenceHandler = TimeSettingHandler.this.getPreferenceHandler();
                                        Context context = TimeSettingHandler.this.getContext();
                                        final STimeFormatInfo sTimeFormatInfo = sCommandInfo;
                                        final TimeSettingHandler timeSettingHandler = TimeSettingHandler.this;
                                        preferenceHandler.update(context, sTimeFormatInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.TimeSettingHandler$setTimeFormat$1$onSettingsResponse$1
                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onFailure(@Nullable String str) {
                                                LogHelper.e(TimeSettingHandler.this.getTAG(), "Saving to preference failed.");
                                            }

                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onSuccess() {
                                                TimeSettingHandler.this.a(sTimeFormatInfo);
                                            }
                                        });
                                    }
                                });
                                return;
                            } else {
                                sendSettingAlreadyApplied();
                                return;
                            }
                        }
                        sendInvalidCommandData();
                        return;
                    }
                    sendCommandParsingFailed();
                    return;
                }
                sendCommandNotSupported();
                return;
            }
            sendSyncIsInProgress();
            return;
        }
        sendDeviceNotConnected();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public STimeFormatInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (STimeFormatInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) STimeFormatInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
