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
import com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FitnessConfigHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IFitnessConfigPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessConfigHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IFitnessConfigPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = FitnessConfigHandler.class.getSimpleName();
    }

    public final void a(SFitnessConfigInfo sFitnessConfigInfo) {
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final IFitnessConfigPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [T, com.coveiot.android.bleabstract.request.SetFitnessInfoRequest] */
    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setFitnessConfig() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SFitnessConfigInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    sCommandInfo.setDistanceInMile(this.d.isDistanceUnitInMile(this.c));
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                    ?? r2 = (SetFitnessInfoRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                    objectRef.element = r2;
                    if (r2 != 0 && this.d.isValidInputHeight(this.c, ((SetFitnessInfoRequest) r2).getHeight()) && this.d.isValidInputWeight(this.c, ((SetFitnessInfoRequest) objectRef.element).getWeight())) {
                        BleApiManager.getInstance(this.c).getBleApi().setUserSettings((BleBaseRequest) objectRef.element, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.FitnessConfigHandler$setFitnessConfig$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                FitnessConfigHandler.this.sendCommandFailed(error.getErrorMsg());
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                FitnessConfigHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                FitnessConfigHandler.this.getCommand().setData(null);
                                FitnessConfigHandler.this.getCommandResponseListener().onResponse(FitnessConfigHandler.this.getCommand());
                                ProfileRepository.getInstance().updateHeightWeight(FitnessConfigHandler.this.getContext(), objectRef.element.getHeight(), objectRef.element.getWeight());
                                IFitnessConfigPreferenceHandler preferenceHandler = FitnessConfigHandler.this.getPreferenceHandler();
                                Context context = FitnessConfigHandler.this.getContext();
                                int height = objectRef.element.getHeight();
                                double weight = objectRef.element.getWeight();
                                final FitnessConfigHandler fitnessConfigHandler = FitnessConfigHandler.this;
                                final SFitnessConfigInfo sFitnessConfigInfo = sCommandInfo;
                                preferenceHandler.update(context, height, weight, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.FitnessConfigHandler$setFitnessConfig$1$onSettingsResponse$1
                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onFailure(@Nullable String str) {
                                        LogHelper.e(FitnessConfigHandler.this.getTAG(), "Saving to preference failed.");
                                    }

                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onSuccess() {
                                        FitnessConfigHandler.this.a(sFitnessConfigInfo);
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
    public SFitnessConfigInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SFitnessConfigInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SFitnessConfigInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
