package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SedentaryHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final ISedentaryPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SedentaryHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull ISedentaryPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = SedentaryHandler.class.getSimpleName();
    }

    public final void a(SSedentaryInfo sSedentaryInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SUserAppSettingsReq.SedentaryAlertBean.SiestaBean siestaBean = new SUserAppSettingsReq.SedentaryAlertBean.SiestaBean();
            siestaBean.setActive(false);
            SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq = new SaveSedentaryAlertSettingsReq();
            String startTime = sSedentaryInfo.getStartTime();
            if (startTime == null) {
                startTime = "09:00:00";
            }
            saveSedentaryAlertSettingsReq.setStartTime(startTime);
            String endTime = sSedentaryInfo.getEndTime();
            if (endTime == null) {
                endTime = "21:00:00";
            }
            saveSedentaryAlertSettingsReq.setEndTime(endTime);
            saveSedentaryAlertSettingsReq.setInterval(sSedentaryInfo.getInterval());
            saveSedentaryAlertSettingsReq.setActive(Intrinsics.areEqual(sSedentaryInfo.getActive(), Boolean.TRUE));
            saveSedentaryAlertSettingsReq.setSiesta(siestaBean);
            CoveUserAppSettings.saveSedentaryAlertSettings(saveSedentaryAlertSettingsReq, new CoveApiListener<SaveSedentaryAlertSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SedentaryHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(SedentaryHandler.this.getTAG(), "Sedentary saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveSedentaryAlertSettingsRes saveSedentaryAlertSettingsRes) {
                    LogHelper.i(SedentaryHandler.this.getTAG(), "Sedentary saved to server successfully.");
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final ISedentaryPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setSedentaryReminder() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SSedentaryInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                    SetReminderRequest setReminderRequest = (SetReminderRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                    if (setReminderRequest != null && this.d.isValidInput(this.c, sCommandInfo)) {
                        if (!this.d.isAlreadySet(this.c, sCommandInfo)) {
                            BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setReminderRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SedentaryHandler$setSedentaryReminder$1
                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    SedentaryHandler.this.sendCommandFailed(error.getErrorMsg());
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    SedentaryHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                    SedentaryHandler.this.getCommand().setData(null);
                                    SedentaryHandler.this.getCommandResponseListener().onResponse(SedentaryHandler.this.getCommand());
                                    ISedentaryPreferenceHandler preferenceHandler = SedentaryHandler.this.getPreferenceHandler();
                                    Context context = SedentaryHandler.this.getContext();
                                    final SSedentaryInfo sSedentaryInfo = sCommandInfo;
                                    final SedentaryHandler sedentaryHandler = SedentaryHandler.this;
                                    preferenceHandler.update(context, sSedentaryInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SedentaryHandler$setSedentaryReminder$1$onSettingsResponse$1
                                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                        public void onFailure(@Nullable String str) {
                                            LogHelper.e(SedentaryHandler.this.getTAG(), "Saving to preference failed.");
                                        }

                                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                        public void onSuccess() {
                                            SedentaryHandler.this.a(sSedentaryInfo);
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
            sendSyncIsInProgress();
            return;
        }
        sendDeviceNotConnected();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SSedentaryInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SSedentaryInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SSedentaryInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
