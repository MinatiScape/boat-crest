package com.coveiot.android.dashboard2.model.state;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class GoalState implements State {
    @NotNull
    private StepsGoalState ssGoalStepsState = new StepsGoalState();
    @NotNull
    private CalorieGoalState ssGoalCalorieState = new CalorieGoalState();
    @NotNull
    private DistanceGoalState ssGoalDistanceState = new DistanceGoalState();
    @NotNull
    private SleepGoalState ssGoalSleepState = new SleepGoalState();
    @NotNull
    private WeightGoalState ssGoalWeightState = new WeightGoalState();

    @NotNull
    public final CalorieGoalState getSsGoalCalorieState() {
        return this.ssGoalCalorieState;
    }

    @NotNull
    public final DistanceGoalState getSsGoalDistanceState() {
        return this.ssGoalDistanceState;
    }

    @NotNull
    public final SleepGoalState getSsGoalSleepState() {
        return this.ssGoalSleepState;
    }

    @NotNull
    public final StepsGoalState getSsGoalStepsState() {
        return this.ssGoalStepsState;
    }

    @NotNull
    public final WeightGoalState getSsGoalWeightState() {
        return this.ssGoalWeightState;
    }

    public final void setSsGoalCalorieState(@NotNull CalorieGoalState calorieGoalState) {
        Intrinsics.checkNotNullParameter(calorieGoalState, "<set-?>");
        this.ssGoalCalorieState = calorieGoalState;
    }

    public final void setSsGoalDistanceState(@NotNull DistanceGoalState distanceGoalState) {
        Intrinsics.checkNotNullParameter(distanceGoalState, "<set-?>");
        this.ssGoalDistanceState = distanceGoalState;
    }

    public final void setSsGoalSleepState(@NotNull SleepGoalState sleepGoalState) {
        Intrinsics.checkNotNullParameter(sleepGoalState, "<set-?>");
        this.ssGoalSleepState = sleepGoalState;
    }

    public final void setSsGoalStepsState(@NotNull StepsGoalState stepsGoalState) {
        Intrinsics.checkNotNullParameter(stepsGoalState, "<set-?>");
        this.ssGoalStepsState = stepsGoalState;
    }

    public final void setSsGoalWeightState(@NotNull WeightGoalState weightGoalState) {
        Intrinsics.checkNotNullParameter(weightGoalState, "<set-?>");
        this.ssGoalWeightState = weightGoalState;
    }
}
