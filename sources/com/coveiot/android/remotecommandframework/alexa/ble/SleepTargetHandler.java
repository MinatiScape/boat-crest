package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetSleepTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SSleepTargetInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveFitness;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.common.FitnessGoal;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalResponse;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepTargetHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final ISleepTargetPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepTargetHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull ISleepTargetPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = SleepTargetHandler.class.getSimpleName();
    }

    public final void a(final SSleepTargetInfo sSleepTargetInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            int goalId = this.d.getGoalId(this.c);
            if (goalId > 0) {
                CoveFitness.updateFitnessGoal(Integer.valueOf(goalId), new UpdateFitnessGoalRequest(1, sSleepTargetInfo.getTarget()), new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler$saveToServer$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LogHelper.e(SleepTargetHandler.this.getTAG(), "Sleep goal update to the server failed.");
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable UpdateFitnessGoalResponse updateFitnessGoalResponse) {
                        LogHelper.i(SleepTargetHandler.this.getTAG(), "Sleep goal updated to the server successfully.");
                        if (updateFitnessGoalResponse == null || !updateFitnessGoalResponse.data.activityType.equals("SLEEP")) {
                            return;
                        }
                        FitnessGoal fitnessGoal = updateFitnessGoalResponse.data;
                        sSleepTargetInfo.setTarget(fitnessGoal.target);
                        sSleepTargetInfo.setGoalId(fitnessGoal.goalId);
                        sSleepTargetInfo.setActivityBaseUnit(fitnessGoal.activityBaseUnit);
                        sSleepTargetInfo.setActivityType(fitnessGoal.activityType);
                        sSleepTargetInfo.setCreatedDate(fitnessGoal.createdDate);
                        sSleepTargetInfo.setModifiedDate(fitnessGoal.modifiedDate);
                        sSleepTargetInfo.setTargetAchieved(fitnessGoal.targetAchieved.toString());
                        ISleepTargetPreferenceHandler preferenceHandler = SleepTargetHandler.this.getPreferenceHandler();
                        Context context = SleepTargetHandler.this.getContext();
                        SSleepTargetInfo sSleepTargetInfo2 = sSleepTargetInfo;
                        final SleepTargetHandler sleepTargetHandler = SleepTargetHandler.this;
                        preferenceHandler.update(context, sSleepTargetInfo2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler$saveToServer$1$onSuccess$1
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str) {
                                LogHelper.e(SleepTargetHandler.this.getTAG(), "Saving to preference failed.");
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                            }
                        });
                    }
                });
                return;
            }
            CoveFitness.createFitnessGoal(new CreateFitnessGoalRequest(ActivityType.SLEEP, ActivityBaseUnit.MINUTES, 1, sSleepTargetInfo.getTarget(), AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()))), new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler$saveToServer$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(SleepTargetHandler.this.getTAG(), "Sleep goal create to the server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessGoalResponse createFitnessGoalResponse) {
                    LogHelper.i(SleepTargetHandler.this.getTAG(), "Sleep goal created to the server successfully.");
                    if (createFitnessGoalResponse == null || !createFitnessGoalResponse.fitnessGoal.activityType.equals("SLEEP")) {
                        return;
                    }
                    FitnessGoal fitnessGoal = createFitnessGoalResponse.fitnessGoal;
                    sSleepTargetInfo.setTarget(fitnessGoal.target);
                    sSleepTargetInfo.setGoalId(fitnessGoal.goalId);
                    sSleepTargetInfo.setActivityBaseUnit(fitnessGoal.activityBaseUnit);
                    sSleepTargetInfo.setActivityType(fitnessGoal.activityType);
                    sSleepTargetInfo.setCreatedDate(fitnessGoal.createdDate);
                    sSleepTargetInfo.setModifiedDate(fitnessGoal.modifiedDate);
                    sSleepTargetInfo.setTargetAchieved(fitnessGoal.targetAchieved.toString());
                    ISleepTargetPreferenceHandler preferenceHandler = SleepTargetHandler.this.getPreferenceHandler();
                    Context context = SleepTargetHandler.this.getContext();
                    SSleepTargetInfo sSleepTargetInfo2 = sSleepTargetInfo;
                    final SleepTargetHandler sleepTargetHandler = SleepTargetHandler.this;
                    preferenceHandler.update(context, sSleepTargetInfo2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler$saveToServer$2$onSuccess$1
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str) {
                            LogHelper.e(SleepTargetHandler.this.getTAG(), "Saving to preference failed.");
                        }

                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onSuccess() {
                        }
                    });
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final ISleepTargetPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [T, com.coveiot.android.bleabstract.request.SetSleepTargetRequest] */
    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setSleepTarget() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SSleepTargetInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    if (BleApiManager.getInstance(this.c).getBleApi() != null && BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isSleepTargetSupported()) {
                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                        ?? r2 = (SetSleepTargetRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                        objectRef.element = r2;
                        if (r2 != 0 && this.d.isValidInput(this.c, sCommandInfo)) {
                            BleApiManager.getInstance(this.c).getBleApi().setUserSettings((BleBaseRequest) objectRef.element, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler$setSleepTarget$1
                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    SleepTargetHandler.this.sendCommandFailed(error.getErrorMsg());
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    SleepTargetHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                    SleepTargetHandler.this.getCommand().setData(null);
                                    SleepTargetHandler.this.getCommandResponseListener().onResponse(SleepTargetHandler.this.getCommand());
                                    ProfileRepository.getInstance().updateSleepTarget(SleepTargetHandler.this.getContext(), objectRef.element.getTarget());
                                    SleepTargetHandler.this.a(sCommandInfo);
                                }
                            });
                            return;
                        } else {
                            sendInvalidCommandData();
                            return;
                        }
                    }
                    getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                    getCommand().setData(null);
                    getCommandResponseListener().onResponse(getCommand());
                    Integer target = sCommandInfo.getTarget();
                    if (target != null) {
                        ProfileRepository.getInstance().updateSleepTarget(this.c, target.intValue());
                    }
                    a(sCommandInfo);
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
    public SSleepTargetInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SSleepTargetInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SSleepTargetInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
