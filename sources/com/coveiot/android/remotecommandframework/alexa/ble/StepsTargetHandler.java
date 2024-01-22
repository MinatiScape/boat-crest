package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SStepsTargetInfo;
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
public final class StepsTargetHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IStepsTargetPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StepsTargetHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IStepsTargetPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = StepsTargetHandler.class.getSimpleName();
    }

    public final void a(final SStepsTargetInfo sStepsTargetInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            int goalId = this.d.getGoalId(this.c);
            if (goalId > 0) {
                CoveFitness.updateFitnessGoal(Integer.valueOf(goalId), new UpdateFitnessGoalRequest(1, sStepsTargetInfo.getTarget()), new CoveApiListener<UpdateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler$saveToServer$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LogHelper.e(StepsTargetHandler.this.getTAG(), "Steps target save to the server failed.");
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable UpdateFitnessGoalResponse updateFitnessGoalResponse) {
                        LogHelper.i(StepsTargetHandler.this.getTAG(), "Steps target saved to the server successfully.");
                        if (updateFitnessGoalResponse == null || !updateFitnessGoalResponse.data.activityType.equals("WALK")) {
                            return;
                        }
                        FitnessGoal fitnessGoal = updateFitnessGoalResponse.data;
                        sStepsTargetInfo.setTarget(fitnessGoal.target);
                        sStepsTargetInfo.setGoalId(fitnessGoal.goalId);
                        sStepsTargetInfo.setActivityBaseUnit(fitnessGoal.activityBaseUnit);
                        sStepsTargetInfo.setActivityType(fitnessGoal.activityType);
                        sStepsTargetInfo.setCreatedDate(fitnessGoal.createdDate);
                        sStepsTargetInfo.setModifiedDate(fitnessGoal.modifiedDate);
                        sStepsTargetInfo.setTargetAchieved(fitnessGoal.targetAchieved.toString());
                        IStepsTargetPreferenceHandler preferenceHandler = StepsTargetHandler.this.getPreferenceHandler();
                        Context context = StepsTargetHandler.this.getContext();
                        SStepsTargetInfo sStepsTargetInfo2 = sStepsTargetInfo;
                        final StepsTargetHandler stepsTargetHandler = StepsTargetHandler.this;
                        preferenceHandler.update(context, sStepsTargetInfo2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler$saveToServer$1$onSuccess$1
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str) {
                                LogHelper.e(StepsTargetHandler.this.getTAG(), "Saving to preference failed.");
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                            }
                        });
                    }
                });
                return;
            }
            CoveFitness.createFitnessGoal(new CreateFitnessGoalRequest(ActivityType.WALK, ActivityBaseUnit.STEPS, 1, sStepsTargetInfo.getTarget(), AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Calendar.getInstance().getTimeInMillis()))), new CoveApiListener<CreateFitnessGoalResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler$saveToServer$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(StepsTargetHandler.this.getTAG(), "Steps target save to the server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessGoalResponse createFitnessGoalResponse) {
                    LogHelper.i(StepsTargetHandler.this.getTAG(), "Steps target saved to the server successfully.");
                    if (createFitnessGoalResponse == null || !createFitnessGoalResponse.fitnessGoal.activityType.equals("WALK")) {
                        return;
                    }
                    FitnessGoal fitnessGoal = createFitnessGoalResponse.fitnessGoal;
                    sStepsTargetInfo.setTarget(fitnessGoal.target);
                    sStepsTargetInfo.setGoalId(fitnessGoal.goalId);
                    sStepsTargetInfo.setActivityBaseUnit(fitnessGoal.activityBaseUnit);
                    sStepsTargetInfo.setActivityType(fitnessGoal.activityType);
                    sStepsTargetInfo.setCreatedDate(fitnessGoal.createdDate);
                    sStepsTargetInfo.setModifiedDate(fitnessGoal.modifiedDate);
                    sStepsTargetInfo.setTargetAchieved(fitnessGoal.targetAchieved.toString());
                    IStepsTargetPreferenceHandler preferenceHandler = StepsTargetHandler.this.getPreferenceHandler();
                    Context context = StepsTargetHandler.this.getContext();
                    SStepsTargetInfo sStepsTargetInfo2 = sStepsTargetInfo;
                    final StepsTargetHandler stepsTargetHandler = StepsTargetHandler.this;
                    preferenceHandler.update(context, sStepsTargetInfo2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler$saveToServer$2$onSuccess$1
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str) {
                            LogHelper.e(StepsTargetHandler.this.getTAG(), "Saving to preference failed.");
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
    public final IStepsTargetPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [com.coveiot.android.bleabstract.request.StepsTargetRequest, T] */
    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setStepsTarget() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                if (BleApiManager.getInstance(this.c).getBleApi() != null && BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isStepGoalSupported()) {
                    final SStepsTargetInfo sCommandInfo = getSCommandInfo(getCommand());
                    if (sCommandInfo != null) {
                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sCommandInfo);
                        ?? r2 = (StepsTargetRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                        objectRef.element = r2;
                        if (r2 != 0 && this.d.isValidInput(this.c, sCommandInfo)) {
                            BleApiManager.getInstance(this.c).getBleApi().setUserSettings((BleBaseRequest) objectRef.element, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler$setStepsTarget$1
                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    StepsTargetHandler.this.sendCommandFailed(error.getErrorMsg());
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                    Intrinsics.checkNotNullParameter(response, "response");
                                    StepsTargetHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                    StepsTargetHandler.this.getCommand().setData(null);
                                    StepsTargetHandler.this.getCommandResponseListener().onResponse(StepsTargetHandler.this.getCommand());
                                    ProfileRepository.getInstance().updateStepsTarget(StepsTargetHandler.this.getContext(), objectRef.element.getTarget());
                                    StepsTargetHandler.this.a(sCommandInfo);
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
    public SStepsTargetInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SStepsTargetInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SStepsTargetInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
