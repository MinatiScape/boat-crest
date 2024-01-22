package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SLiftWristToViewInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LiftWristToViewHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final ILiftWristToViewPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiftWristToViewHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull ILiftWristToViewPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = LiftWristToViewHandler.class.getSimpleName();
    }

    public final void a(SLiftWristToViewInfo sLiftWristToViewInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SaveLiftWristToViewSettingsReq saveLiftWristToViewSettingsReq = new SaveLiftWristToViewSettingsReq();
            saveLiftWristToViewSettingsReq.setActive(sLiftWristToViewInfo.getActive());
            String startTime = sLiftWristToViewInfo.getStartTime();
            boolean z = false;
            if (!(startTime == null || startTime.length() == 0)) {
                String endTime = sLiftWristToViewInfo.getEndTime();
                if (endTime == null || endTime.length() == 0) {
                    z = true;
                }
                if (!z) {
                    saveLiftWristToViewSettingsReq.setStartTime(sLiftWristToViewInfo.getStartTime());
                    saveLiftWristToViewSettingsReq.setEndTime(sLiftWristToViewInfo.getEndTime());
                }
            }
            CoveUserAppSettings.saveLiftWristToViewSettings(saveLiftWristToViewSettingsReq, new CoveApiListener<SaveLiftWristToViewSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.LiftWristToViewHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(LiftWristToViewHandler.this.getTAG(), "Lift wrist to view saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveLiftWristToViewSettingsRes saveLiftWristToViewSettingsRes) {
                    LogHelper.i(LiftWristToViewHandler.this.getTAG(), "Lift wrist to view saved to server successfully.");
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final ILiftWristToViewPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setLiftWristToView() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                if (BleApiManager.getInstance(this.c).getBleApi() != null && BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isLiftWristToViewSettingsSupported()) {
                    final SLiftWristToViewInfo sCommandInfo = getSCommandInfo(getCommand());
                    if (sCommandInfo != null) {
                        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                        SetLiftWristRequest setLiftWristRequest = (SetLiftWristRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                        if (setLiftWristRequest != null && this.d.isValidInput(this.c, sCommandInfo)) {
                            if (!this.d.isAlreadySet(this.c, sCommandInfo)) {
                                BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setLiftWristRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.LiftWristToViewHandler$setLiftWristToView$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        LiftWristToViewHandler.this.sendCommandFailed(error.getErrorMsg());
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        LiftWristToViewHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                        LiftWristToViewHandler.this.getCommand().setData(null);
                                        LiftWristToViewHandler.this.getCommandResponseListener().onResponse(LiftWristToViewHandler.this.getCommand());
                                        ILiftWristToViewPreferenceHandler preferenceHandler = LiftWristToViewHandler.this.getPreferenceHandler();
                                        Context context = LiftWristToViewHandler.this.getContext();
                                        final SLiftWristToViewInfo sLiftWristToViewInfo = sCommandInfo;
                                        final LiftWristToViewHandler liftWristToViewHandler = LiftWristToViewHandler.this;
                                        preferenceHandler.update(context, sLiftWristToViewInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.LiftWristToViewHandler$setLiftWristToView$1$onSettingsResponse$1
                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onFailure(@Nullable String str) {
                                                LogHelper.e(LiftWristToViewHandler.this.getTAG(), "Saving to preference failed.");
                                            }

                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onSuccess() {
                                                LiftWristToViewHandler.this.a(sLiftWristToViewInfo);
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
    public SLiftWristToViewInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SLiftWristToViewInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SLiftWristToViewInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
