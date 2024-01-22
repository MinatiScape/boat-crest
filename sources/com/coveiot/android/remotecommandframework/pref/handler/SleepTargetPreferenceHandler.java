package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SSleepTargetInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SleepTargetPreferenceHandler implements ISleepTargetPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler
    public int getGoalId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(context).getFitnessGoalSleepData();
        if (fitnessGoalSleepData != null) {
            Integer num = fitnessGoalSleepData.goalId;
            Intrinsics.checkNotNullExpressionValue(num, "it.goalId");
            if (num.intValue() > 0) {
                Integer num2 = fitnessGoalSleepData.goalId;
                Intrinsics.checkNotNullExpressionValue(num2, "it.goalId");
                return num2.intValue();
            }
            return 0;
        }
        return 0;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SSleepTargetInfo sSleepTargetInfo) {
        int intValue;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sSleepTargetInfo, "sSleepTargetInfo");
        Integer target = sSleepTargetInfo.getTarget();
        return target != null && 240 <= (intValue = target.intValue()) && intValue < 721;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler
    public void update(@NotNull Context context, @NotNull SSleepTargetInfo sSleepTargetInfo, @NotNull SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sSleepTargetInfo, "sSleepTargetInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(context).getFitnessGoalSleepData();
        if (fitnessGoalSleepData == null) {
            fitnessGoalSleepData = new FitnessGoal();
        }
        fitnessGoalSleepData.target = sSleepTargetInfo.getTarget();
        fitnessGoalSleepData.goalId = sSleepTargetInfo.getGoalId();
        fitnessGoalSleepData.activityBaseUnit = sSleepTargetInfo.getActivityBaseUnit();
        fitnessGoalSleepData.activityType = sSleepTargetInfo.getActivityType();
        fitnessGoalSleepData.createdDate = sSleepTargetInfo.getCreatedDate();
        fitnessGoalSleepData.modifiedDate = sSleepTargetInfo.getModifiedDate();
        String targetAchieved = sSleepTargetInfo.getTargetAchieved();
        if (targetAchieved == null) {
            targetAchieved = null;
        }
        fitnessGoalSleepData.targetAchieved = targetAchieved;
        UserDataManager.getInstance(context).saveFitnessGoalSleep(fitnessGoalSleepData);
        GoalSettingsData goalSettingsData = GoalSettingsData.getInstance();
        Integer num = fitnessGoalSleepData.target;
        Intrinsics.checkNotNullExpressionValue(num, "it.target");
        goalSettingsData.setSleep_hours(num.intValue());
        UserDataManager.getInstance(context).saveGoalSettings(goalSettingsData);
        successListener.onSuccess();
    }
}
