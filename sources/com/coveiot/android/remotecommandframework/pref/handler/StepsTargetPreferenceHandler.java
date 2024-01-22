package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SStepsTargetInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class StepsTargetPreferenceHandler implements IStepsTargetPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler
    public int getGoalId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(context).getFitnessGoalStepsData();
        if (fitnessGoalStepsData != null) {
            Integer num = fitnessGoalStepsData.goalId;
            Intrinsics.checkNotNullExpressionValue(num, "it.goalId");
            if (num.intValue() > 0) {
                Integer num2 = fitnessGoalStepsData.goalId;
                Intrinsics.checkNotNullExpressionValue(num2, "it.goalId");
                return num2.intValue();
            }
            return 0;
        }
        return 0;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SStepsTargetInfo sStepsTargetInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sStepsTargetInfo, "sStepsTargetInfo");
        Integer target = sStepsTargetInfo.getTarget();
        if (target != null) {
            int intValue = target.intValue();
            return (1000 <= intValue && intValue < 30001) && intValue % 1000 == 0;
        }
        return false;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler
    public void update(@NotNull Context context, @NotNull SStepsTargetInfo sStepsTargetInfo, @NotNull SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sStepsTargetInfo, "sStepsTargetInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(context).getFitnessGoalStepsData();
        if (fitnessGoalStepsData == null) {
            fitnessGoalStepsData = new FitnessGoal();
        }
        fitnessGoalStepsData.target = sStepsTargetInfo.getTarget();
        fitnessGoalStepsData.goalId = sStepsTargetInfo.getGoalId();
        fitnessGoalStepsData.activityBaseUnit = sStepsTargetInfo.getActivityBaseUnit();
        fitnessGoalStepsData.activityType = sStepsTargetInfo.getActivityType();
        fitnessGoalStepsData.createdDate = sStepsTargetInfo.getCreatedDate();
        fitnessGoalStepsData.modifiedDate = sStepsTargetInfo.getModifiedDate();
        fitnessGoalStepsData.targetAchieved = sStepsTargetInfo.getTargetAchieved();
        UserDataManager.getInstance(context).saveFitnessGoalSteps(fitnessGoalStepsData);
        GoalSettingsData goalSettingsData = GoalSettingsData.getInstance();
        Integer num = fitnessGoalStepsData.target;
        Intrinsics.checkNotNullExpressionValue(num, "it.target");
        goalSettingsData.setSteps(num.intValue());
        UserDataManager.getInstance(context).saveGoalSettings(goalSettingsData);
        successListener.onSuccess();
    }
}
