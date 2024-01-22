package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import android.content.res.Resources;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.server.SwimmingStyleEnum;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutUiBeanProvider {
    @NotNull
    public static final WorkoutUiBeanProvider INSTANCE = new WorkoutUiBeanProvider();

    /* loaded from: classes2.dex */
    public enum ScreenType {
        ACTIVITY_HOME,
        ACTIVITY_HISTORY,
        ACTIVITY_DETAIL,
        ACTIVITY_SHARE,
        ACTIVITY_FITNESS
    }

    public static /* synthetic */ boolean f(WorkoutUiBeanProvider workoutUiBeanProvider, Context context, String str, Integer num, ScreenType screenType, Integer num2, Integer num3, int i, Object obj) {
        return workoutUiBeanProvider.e(context, str, num, screenType, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? 0 : num3);
    }

    public final boolean a(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isAvgSwolfApplicable(context, str, str2);
    }

    public final boolean b(Context context, String str, Float f, ScreenType screenType) {
        return f != null;
    }

    public final boolean c(Context context, String str, Integer num, ScreenType screenType) {
        return num != null;
    }

    public final boolean d(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isFastestPaceApplicable(context, str, null, 0, 0, str2);
    }

    public final boolean e(Context context, String str, Integer num, ScreenType screenType, Integer num2, Integer num3) {
        if (num != null) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isJstyleDevice(context) && !companion.isSmaDevice(context) && !companion.isMoyangDevice(context) && !companion.isMatrixDevice(context)) {
                if (companion.isIDODevice(context)) {
                    if (Intrinsics.areEqual(str, CoveApiConstants.SWIM)) {
                        return false;
                    }
                } else if (!companion.isCZDevice(context) && !companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                    if (!companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) {
                        return false;
                    }
                } else if (num3 != null && num3.intValue() != 17 && num3.intValue() != 12 && num3.intValue() != 1) {
                    num3.intValue();
                }
            }
            return true;
        }
        return false;
    }

    public final boolean g(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isLapsApplicable(context, str, str2);
    }

    @Nullable
    public final WorkoutUiBean getAvgSPeedUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Float f, @NotNull String activityMode, @Nullable String str) {
        String str2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isAvgSPeedApplicable(context, activityMode, str)) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str3 = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (f != null && f.floatValue() > 0.0f) {
                float floatValue = f.floatValue() / 100;
                if (companion.isSmaDevice(context)) {
                    floatValue = f.floatValue() / 1000;
                }
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    str2 = ((float) WorkoutUtils.INSTANCE.convertKMToMiles(floatValue)) + ' ' + context.getString(R.string.mil_per_hour);
                } else {
                    str2 = floatValue + ' ' + context.getString(R.string.km_per_hour);
                }
                str3 = str2;
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_avg_speed);
            String string = context.getResources().getString(R.string.avg_speed);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.avg_speed)");
            return new WorkoutUiBean(valueOf, string, str3, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getAvgStrideLengthUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isAvgStrideLengthApplicable(context, activityMode, str)) {
            String str2 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str2 = String.valueOf(num.intValue());
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_steps_activity);
            String string = context.getResources().getString(R.string.avg_stride_length);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…string.avg_stride_length)");
            return new WorkoutUiBean(valueOf, string, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getAvgSwolfUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (a(context, activityMode, str)) {
            String str2 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str2 = num.toString();
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_avg_swolf);
            String string = context.getResources().getString(R.string.avg_swolf);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.avg_swolf)");
            return new WorkoutUiBean(valueOf, string, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getCalorieUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Float f, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (b(context, activityMode, f, screenType)) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (f != null && f.floatValue() > 0.0f) {
                if (companion.isJstyleDevice(context)) {
                    str = f.toString();
                } else {
                    str = String.valueOf((int) f.floatValue());
                }
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_calories);
            String string = context.getResources().getString(R.string.cal);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.cal)");
            return new WorkoutUiBean(valueOf, string, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getDistanceUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str) {
        String valueOf;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isDistanceApplicable(context, activityMode, num, screenType, num2, num3, str)) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str2 = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    if (companion.isTouchELXDevice(context)) {
                        valueOf = String.valueOf((float) WorkoutUtils.INSTANCE.convertKMToMilesRoundingModeUp(num.intValue()));
                    } else {
                        valueOf = String.valueOf((float) WorkoutUtils.INSTANCE.convertKMToMiles(num.intValue()));
                    }
                } else if (companion.isIDODevice(context)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(WorkoutUtils.INSTANCE.convertCmToKmRoundingModeHalfUp(num.intValue()));
                    sb.append(' ');
                    valueOf = sb.toString();
                } else {
                    valueOf = String.valueOf(WorkoutUtils.INSTANCE.convertCmToKm(num.intValue()));
                }
                str2 = valueOf;
            }
            Integer valueOf2 = Integer.valueOf(R.drawable.distance_activity);
            Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(context).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile");
            String string = context.getString(isDistanceUnitInMile2.booleanValue() ? R.string.mil : R.string.km);
            Intrinsics.checkNotNullExpressionValue(string, "if (UserDataManager.getI…ring.km\n                )");
            return new WorkoutUiBean(valueOf2, string, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getDurationUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (c(context, activityMode, num, screenType)) {
            Integer valueOf = Integer.valueOf(R.drawable.duration_activity);
            String string = context.getResources().getString(R.string.hrs);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.hrs)");
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Intrinsics.checkNotNull(num);
            return new WorkoutUiBean(valueOf, string, workoutUtils.getFormattedDuration(num.intValue()), 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getFastestPaceUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Float f, @NotNull String activityMode, @Nullable String str) {
        String formattedPace;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (d(context, activityMode, str)) {
            Integer valueOf = Integer.valueOf(R.drawable.modes_clock_ic);
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str2 = "-";
            String str3 = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (companion.isIDODevice(context)) {
                if (f != null && f.floatValue() > 0.0f) {
                    Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
                    Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                    if (isDistanceUnitInMile.booleanValue()) {
                        formattedPace = WorkoutUtils.INSTANCE.getFormattedPace((int) (f.floatValue() / 0.62d));
                    } else {
                        formattedPace = WorkoutUtils.INSTANCE.getFormattedPace(f.floatValue());
                    }
                    str3 = formattedPace;
                }
            } else if (companion.isSmaDevice(context)) {
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    if (f != null && f.floatValue() > 0.0f) {
                        str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) ((f.floatValue() * 1.0f) / 0.621371f));
                    }
                } else if (f != null && f.floatValue() > 0.0f) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(f.floatValue() * 1.0f);
                }
                str3 = str2;
            }
            if (companion.isIDODevice(context)) {
                String string = context.getResources().getString(R.string.max_pace);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.max_pace)");
                return new WorkoutUiBean(valueOf, string, str3, 0);
            } else if (companion.isSmaDevice(context)) {
                String string2 = context.getResources().getString(R.string.fastest_pace);
                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.fastest_pace)");
                return new WorkoutUiBean(valueOf, string2, str3, 0);
            } else {
                String string3 = context.getResources().getString(R.string.pace);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getString(R.string.pace)");
                return new WorkoutUiBean(valueOf, string3, str3, 0);
            }
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getHeartRateUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @NotNull String activityMode, @Nullable Integer num4, @Nullable Integer num5) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (e(context, activityMode, num, screenType, num4, num5)) {
            int i = R.drawable.ic_heart;
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str = companion.isMigratedDevice(context) ? "-- *" : "-";
            String valueOf = String.valueOf(context.getResources().getString(R.string.hr_new));
            if (num != null && num.intValue() > 0) {
                str = num + ' ' + context.getResources().getString(R.string.bpm_small);
            }
            if ((companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isTouchELXDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) && num != null && num.intValue() > 0) {
                str = num + ' ' + context.getResources().getString(R.string.avg_bpm_new);
            }
            if (num2 != null && num2.intValue() > 0 && num3 != null && num3.intValue() > 0) {
                valueOf = num2 + " min - " + num3 + " max";
            }
            return new WorkoutUiBean(Integer.valueOf(i), valueOf, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getLapsUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (g(context, activityMode, str)) {
            String str2 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str2 = num.toString();
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_total_laps);
            String string = context.getResources().getString(R.string.laps);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.laps)");
            return new WorkoutUiBean(valueOf, string, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getLowestPaceUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Float f, @NotNull String activityMode, @Nullable String str) {
        String formattedPace;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (h(context, activityMode, str)) {
            Integer valueOf = Integer.valueOf(R.drawable.modes_clock_ic);
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str2 = "-";
            if (companion.isSmaDevice(context)) {
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    if (f != null && f.floatValue() > 0.0f) {
                        formattedPace = WorkoutUtils.INSTANCE.getFormattedPace((int) ((f.floatValue() * 1.0f) / 0.621371f));
                        str2 = formattedPace;
                    }
                } else if (f != null && f.floatValue() > 0.0f) {
                    formattedPace = WorkoutUtils.INSTANCE.getFormattedPace(f.floatValue() * 1.0f);
                    str2 = formattedPace;
                }
            }
            if (companion.isSmaDevice(context)) {
                String string = context.getResources().getString(R.string.slowest_pace);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.slowest_pace)");
                return new WorkoutUiBean(valueOf, string, str2, 0);
            }
            String string2 = context.getResources().getString(R.string.pace);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.pace)");
            return new WorkoutUiBean(valueOf, string2, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getMaxSPMUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (i(context, activityMode, str)) {
            Integer valueOf = Integer.valueOf(R.drawable.ic_spm_activity);
            String str2 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str2 = num.toString();
            }
            String string = context.getResources().getString(R.string.max_spm);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.max_spm)");
            return new WorkoutUiBean(valueOf, string, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getMinMaxHeartRateUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (f(this, context, activityMode, num, screenType, null, null, 48, null)) {
            String str = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            String valueOf = String.valueOf(context.getResources().getString(R.string.min_hr));
            Integer valueOf2 = Integer.valueOf(R.drawable.ic_heart);
            if (num2 != null && num2.intValue() > 0 && num3 != null && num3.intValue() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(num2);
                sb.append(Soundex.SILENT_MARKER);
                sb.append(num3);
                str = sb.toString();
            }
            return new WorkoutUiBean(valueOf2, valueOf, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getPaceUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Float f, int i, int i2, @NotNull String activityMode, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isPaceApplicable(context, activityMode, screenType, num, num2, str)) {
            Integer valueOf = Integer.valueOf(R.drawable.modes_clock_ic);
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str2 = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (companion.isSmaDevice(context)) {
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    if (num3 != null && num3.intValue() > 0) {
                        str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) ((num3.intValue() * 1.0f) / 0.621371f));
                    } else {
                        str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) (((i2 / (i / 100.0f)) * 1000.0f) / 0.621371f));
                    }
                } else if (num3 != null && num3.intValue() > 0) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(num3.intValue() * 1.0f);
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace((i2 / (i / 100)) * 1000.0f);
                }
            } else if (companion.isMatrixDevice(context)) {
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) (i2 / ((i / 100000.0f) * 0.62137d)));
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(i2 / (i / 100000.0f));
                }
            } else if (companion.isIDODevice(context)) {
                Boolean isDistanceUnitInMile3 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile3, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile3.booleanValue()) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) (i2 / ((i / 100000.0f) * 0.62137d)));
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(i2 / (i / 100000.0f));
                }
            } else if (companion.isTouchELXDevice(context)) {
                Boolean isDistanceUnitInMile4 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile4, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile4.booleanValue()) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace((int) (i2 / ((i / 100000.0f) * 0.62137d)));
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(i2 / (i / 100000.0f));
                }
            } else if (companion.isEastApexDevice(context) && num3 != null && num3.intValue() > 0) {
                Boolean isDistanceUnitInMile5 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile5, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile5.booleanValue()) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPaceL((float) (num3.intValue() / 0.621d));
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPaceL(num3.intValue());
                }
            } else if (f != null && f.floatValue() > 0.0f) {
                Boolean isDistanceUnitInMile6 = UserDataManager.getInstance(context).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile6, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile6.booleanValue()) {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace((float) Math.ceil(f.floatValue() * 1.609d));
                } else {
                    str2 = WorkoutUtils.INSTANCE.getFormattedPace(f.floatValue());
                }
            }
            if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) {
                String string = context.getResources().getString(R.string.pace);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.pace)");
                return new WorkoutUiBean(valueOf, string, str2, 0);
            }
            String string2 = context.getResources().getString(R.string.avg_pace);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.avg_pace)");
            return new WorkoutUiBean(valueOf, string2, str2, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getSPMUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @NotNull String activityMode, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isSPMApplicable(context, activityMode)) {
            Integer valueOf = Integer.valueOf(R.drawable.ic_spm_activity);
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            String str = companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0 && num2 != null && num2.intValue() > 0) {
                str = String.valueOf(WorkoutUtils.INSTANCE.getSPMValue(num.intValue(), num2.intValue()));
            }
            if (companion.isTouchELXDevice(context) && num3 != null && num3.intValue() > 0) {
                str = num3.toString();
            }
            if (companion.isSmaDevice(context)) {
                return new WorkoutUiBean(valueOf, SMAWorkoutUtils.Companion.getInstance(context).getSPMName(context), str, 0);
            }
            String string = context.getResources().getString(R.string.spm);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.spm)");
            return new WorkoutUiBean(valueOf, string, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getStepsUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable Integer num2, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isStepsApplicable(context, activityMode, num, screenType, num2, num3)) {
            Integer valueOf = Integer.valueOf(R.drawable.ic_steps_activity);
            String str = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str = num.toString();
            }
            String string = context.getResources().getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.steps)");
            return new WorkoutUiBean(valueOf, string, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getStrokesOrCounterFrequencyUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isStrokeFrequencyApplicable(context, activityMode)) {
            String str = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str = num.toString();
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_frequency);
            Resources resources = context.getResources();
            int i = R.string.frequency;
            String string = resources.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.frequency)");
            WorkoutUiBean workoutUiBean = new WorkoutUiBean(valueOf, string, str, 0);
            if (Intrinsics.areEqual(activityMode, "ROWING")) {
                String string2 = context.getResources().getString(i);
                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.frequency)");
                return new WorkoutUiBean(valueOf, string2, str, 0);
            } else if (Intrinsics.areEqual(activityMode, "SKIPPING")) {
                String string3 = context.getResources().getString(R.string.avg_frequency);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…g(R.string.avg_frequency)");
                return new WorkoutUiBean(valueOf, string3, str, 0);
            } else {
                return workoutUiBean;
            }
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getStrokesOrCounterUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isStrokeOrCountApplicable(context, activityMode)) {
            String str = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str = num.toString();
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_count);
            String string = context.getResources().getString(R.string.counter);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.counter)");
            return new WorkoutUiBean(valueOf, string, str, 0);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getSwimStrokeUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable String str, @NotNull String activityMode, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (j(context, activityMode, str2)) {
            String str3 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (str != null) {
                str3 = getSwimStyleName(context, str);
                Intrinsics.checkNotNull(str3);
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_swim_stroke);
            String string = context.getResources().getString(R.string.swimming_stroke);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…R.string.swimming_stroke)");
            return new WorkoutUiBean(valueOf, string, str3, 0);
        }
        return null;
    }

    @Nullable
    public final String getSwimStyleName(@NotNull Context context, @NotNull String swimStroke) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(swimStroke, "swimStroke");
        if (Intrinsics.areEqual(swimStroke, SwimmingStyleEnum.MEDLEY.getSwimStyle())) {
            return context.getResources().getString(R.string.medley);
        }
        if (Intrinsics.areEqual(swimStroke, SwimmingStyleEnum.FREE_STYLE.getSwimStyle())) {
            return context.getResources().getString(R.string.freestyle);
        }
        if (Intrinsics.areEqual(swimStroke, SwimmingStyleEnum.BREAST_STROKE.getSwimStyle())) {
            return context.getResources().getString(R.string.breaststroke);
        }
        if (Intrinsics.areEqual(swimStroke, SwimmingStyleEnum.BACK_STROKE.getSwimStyle())) {
            return context.getResources().getString(R.string.backstroke);
        }
        if (Intrinsics.areEqual(swimStroke, SwimmingStyleEnum.BUTTERFLY.getSwimStyle())) {
            return context.getResources().getString(R.string.butterfly);
        }
        return null;
    }

    @Nullable
    public final WorkoutUiBean getTotalStrokeUiBean(@NotNull Context context, @NotNull ScreenType screenType, @Nullable Integer num, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        if (k(context, activityMode, str)) {
            String str2 = DeviceUtils.Companion.isMigratedDevice(context) ? "-- *" : "-";
            if (num != null && num.intValue() > 0) {
                str2 = num.toString();
            }
            Integer valueOf = Integer.valueOf(R.drawable.ic_total_stroke);
            String string = context.getResources().getString(R.string.total_strokes);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…g(R.string.total_strokes)");
            return new WorkoutUiBean(valueOf, string, str2, 0);
        }
        return null;
    }

    public final boolean h(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isLowestPaceApplicable(context, str, null, 0, 0, str2);
    }

    public final boolean i(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isMaxSPMApplicable(context, str, str2);
    }

    public final boolean j(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isSwimStrokeApplicable(context, str, str2);
    }

    public final boolean k(Context context, String str, String str2) {
        return WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).isTotalStrokeApplicable(context, str, str2);
    }
}
