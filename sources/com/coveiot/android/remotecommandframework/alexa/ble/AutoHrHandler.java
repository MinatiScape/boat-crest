package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SAutoHrInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AutoHrHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IAutoHrPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoHrHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IAutoHrPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = AutoHrHandler.class.getSimpleName();
    }

    public final void a(SAutoHrInfo sAutoHrInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SaveAutoHRSettingReq saveAutoHRSettingReq = new SaveAutoHRSettingReq();
            saveAutoHRSettingReq.setActive(sAutoHrInfo.getActive());
            saveAutoHRSettingReq.setInterval(sAutoHrInfo.getInterval());
            CoveUserAppSettings.saveAutoHeartRateInterval(saveAutoHRSettingReq, new CoveApiListener<SaveAutoHRSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AutoHrHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(AutoHrHandler.this.getTAG(), "Auto Hr saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAutoHRSettingsRes saveAutoHRSettingsRes) {
                    LogHelper.d(AutoHrHandler.this.getTAG(), "Auto Hr saved to server successfully.");
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final IAutoHrPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setAutoHr() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                if (BleApiManager.getInstance(this.c).getBleApi() != null && BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().getAutoHrSettingsSupported()) {
                    final SAutoHrInfo sCommandInfo = getSCommandInfo(getCommand());
                    if (sCommandInfo != null) {
                        if (BleApiManager.getInstance(this.c).getDeviceType() == DeviceType.matrix) {
                            sCommandInfo.setInterval("00:10:00");
                        }
                        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                        HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                        if (heartRateTimeIntervalRequest != null && this.d.isValidInput(this.c, sCommandInfo)) {
                            if (!this.d.isAlreadySet(this.c, sCommandInfo)) {
                                BleApiManager.getInstance(this.c).getBleApi().setUserSettings(heartRateTimeIntervalRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AutoHrHandler$setAutoHr$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        AutoHrHandler.this.sendCommandFailed(error.getErrorMsg());
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        AutoHrHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                        AutoHrHandler.this.getCommand().setData(null);
                                        AutoHrHandler.this.getCommandResponseListener().onResponse(AutoHrHandler.this.getCommand());
                                        IAutoHrPreferenceHandler preferenceHandler = AutoHrHandler.this.getPreferenceHandler();
                                        Context context = AutoHrHandler.this.getContext();
                                        final SAutoHrInfo sAutoHrInfo = sCommandInfo;
                                        final AutoHrHandler autoHrHandler = AutoHrHandler.this;
                                        preferenceHandler.update(context, sAutoHrInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AutoHrHandler$setAutoHr$1$onSettingsResponse$1
                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onFailure(@Nullable String str) {
                                                LogHelper.e(AutoHrHandler.this.getTAG(), "Saving to preference failed.");
                                            }

                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onSuccess() {
                                                AutoHrHandler.this.a(sAutoHrInfo);
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
    public SAutoHrInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SAutoHrInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SAutoHrInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
