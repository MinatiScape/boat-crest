package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface WorkoutParameterInterface {

    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean isDistanceApplicable$default(WorkoutParameterInterface workoutParameterInterface, Context context, String str, Integer num, WorkoutUiBeanProvider.ScreenType screenType, Integer num2, Integer num3, String str2, int i, Object obj) {
            if (obj == null) {
                return workoutParameterInterface.isDistanceApplicable(context, str, num, screenType, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? 0 : num3, (i & 64) != 0 ? null : str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isDistanceApplicable");
        }

        public static /* synthetic */ boolean isFastestPaceApplicable$default(WorkoutParameterInterface workoutParameterInterface, Context context, String str, WorkoutUiBeanProvider.ScreenType screenType, Integer num, Integer num2, String str2, int i, Object obj) {
            if (obj == null) {
                return workoutParameterInterface.isFastestPaceApplicable(context, str, screenType, (i & 8) != 0 ? 0 : num, (i & 16) != 0 ? 0 : num2, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isFastestPaceApplicable");
        }

        public static /* synthetic */ boolean isLowestPaceApplicable$default(WorkoutParameterInterface workoutParameterInterface, Context context, String str, WorkoutUiBeanProvider.ScreenType screenType, Integer num, Integer num2, String str2, int i, Object obj) {
            if (obj == null) {
                return workoutParameterInterface.isLowestPaceApplicable(context, str, screenType, (i & 8) != 0 ? 0 : num, (i & 16) != 0 ? 0 : num2, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isLowestPaceApplicable");
        }

        public static /* synthetic */ boolean isPaceApplicable$default(WorkoutParameterInterface workoutParameterInterface, Context context, String str, WorkoutUiBeanProvider.ScreenType screenType, Integer num, Integer num2, String str2, int i, Object obj) {
            if (obj == null) {
                return workoutParameterInterface.isPaceApplicable(context, str, screenType, (i & 8) != 0 ? 0 : num, (i & 16) != 0 ? 0 : num2, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isPaceApplicable");
        }

        public static /* synthetic */ boolean isStepsApplicable$default(WorkoutParameterInterface workoutParameterInterface, Context context, String str, Integer num, WorkoutUiBeanProvider.ScreenType screenType, Integer num2, Integer num3, int i, Object obj) {
            if (obj == null) {
                return workoutParameterInterface.isStepsApplicable(context, str, num, screenType, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? 0 : num3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isStepsApplicable");
        }
    }

    void getActivitiesListFromRemoteConfig(@NotNull Context context);

    @Nullable
    WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z);

    boolean isAvgSPeedApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isAvgStrideLengthApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isAvgSwolfApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isDistanceApplicable(@NotNull Context context, @NotNull String str, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str2);

    boolean isFastestPaceApplicable(@NotNull Context context, @NotNull String str, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str2);

    boolean isLapsApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isLowestPaceApplicable(@NotNull Context context, @NotNull String str, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str2);

    boolean isMaxSPMApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isPaceApplicable(@NotNull Context context, @NotNull String str, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str2);

    boolean isSPMApplicable(@NotNull Context context, @NotNull String str);

    boolean isStepsApplicable(@NotNull Context context, @NotNull String str, @Nullable Integer num, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num2, @Nullable Integer num3);

    boolean isStrokeFrequencyApplicable(@NotNull Context context, @NotNull String str);

    boolean isStrokeOrCountApplicable(@NotNull Context context, @NotNull String str);

    boolean isSwimStrokeApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);

    boolean isTotalStrokeApplicable(@NotNull Context context, @NotNull String str, @Nullable String str2);
}
