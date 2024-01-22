package com.coveiot.android.bleabstract.utils.idoUtils;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.c;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOUtils {
    public static final int DEFAULT_CALORIE = 500;
    public static final int DEFAULT_DISTANCE = 5000;
    public static final long DEFAULT_EXERCISE_TIME = 1800;
    public static final int DEFAULT_WALK_HOUR_TIME = 12;
    @NotNull
    public static final IDOUtils INSTANCE = new IDOUtils();

    @NotNull
    public static final ArrayList<Integer> getEmptyDayCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1440; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptyDayCodedValuesList$annotations() {
    }

    @NotNull
    public static final ArrayList<Integer> getEmptyDaySleepCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1440; i++) {
            arrayList.add(-1);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptyDaySleepCodedValuesList$annotations() {
    }

    public final int calculateCalorieMax(float f) {
        int roundToInt = c.roundToInt(f * 0.8f);
        if (roundToInt <= 50) {
            return 50;
        }
        return roundToInt % 50 != 0 ? ((roundToInt / 50) + 1) * 50 : roundToInt;
    }

    public final int calculateCalorieMin(float f) {
        int roundToInt = c.roundToInt(0.8f * f);
        int roundToInt2 = c.roundToInt(f * 0.15f);
        if (roundToInt <= 50) {
            return 10;
        }
        return roundToInt2 % 50 != 0 ? (roundToInt2 / 50) * 50 : roundToInt2;
    }

    @NotNull
    public final String convertDateFormat(int i, int i2, int i3) {
        Object valueOf;
        Object valueOf2;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(Soundex.SILENT_MARKER);
        if (i2 < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i2);
            valueOf = sb2.toString();
        } else {
            valueOf = Integer.valueOf(i2);
        }
        sb.append(valueOf);
        sb.append(Soundex.SILENT_MARKER);
        if (i3 < 10) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('0');
            sb3.append(i3);
            valueOf2 = sb3.toString();
        } else {
            valueOf2 = Integer.valueOf(i3);
        }
        sb.append(valueOf2);
        return sb.toString();
    }

    @NotNull
    public final String convertDateIntoTimeFormat(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        String format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(date);
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date)");
        return format;
    }

    @NotNull
    public final String convertTimestampToDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    @Nullable
    public final String getActivityMode(int i) {
        if (i != 8) {
            if (i != 9) {
                if (i != 101) {
                    if (i != 102) {
                        if (i != 4) {
                            if (i != 11) {
                                if (i != 18) {
                                    if (i != 22) {
                                        if (i != 29) {
                                            if (i != 32) {
                                                if (i != 35) {
                                                    if (i != 75) {
                                                        if (i != 104) {
                                                            if (i != 110) {
                                                                switch (i) {
                                                                    case 48:
                                                                    case 49:
                                                                        return CoveApiConstants.RUN;
                                                                    case 50:
                                                                    case 51:
                                                                        return CoveApiConstants.CYCLE;
                                                                    case 52:
                                                                    case 53:
                                                                    default:
                                                                        return "WALK";
                                                                    case 54:
                                                                    case 55:
                                                                        return CoveApiConstants.SWIM;
                                                                    case 56:
                                                                        break;
                                                                    case 57:
                                                                        return "ROWING_MACHINE";
                                                                    case 58:
                                                                        return "HIIT";
                                                                }
                                                            } else {
                                                                return "TRADITIONAL_STRENGTH_TRAINING";
                                                            }
                                                        } else {
                                                            return "COOLDOWN";
                                                        }
                                                    } else {
                                                        return "CRICKET";
                                                    }
                                                } else {
                                                    return "ZUMBA";
                                                }
                                            } else {
                                                return "PILATES";
                                            }
                                        } else {
                                            return "DANCE";
                                        }
                                    } else {
                                        return "FOOTBALL";
                                    }
                                } else {
                                    return "YOGA";
                                }
                            }
                            return "ELLIPTICAL";
                        }
                        return CoveApiConstants.HIKING;
                    }
                    return "CORE_TRAINING";
                }
                return "FUNCTIONAL_STRENGTH_TRAINING";
            }
            return "WORKOUT";
        }
        return "OTHER";
    }

    @Nullable
    public final String getActivitySite(int i) {
        return (i == 48 || i == 50 || i == 52 || i == 55) ? "OUTDOOR" : "INDOOR";
    }

    public final long getCalendarMillisFromDate(int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, i3);
        calendar.set(11, i4);
        calendar.set(12, i5);
        calendar.set(13, i6);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    @NotNull
    public final ArrayList<Integer> getEmptyHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    @NotNull
    public final String getMacAddress(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getMacAddress();
    }

    public final int getMaxAlarmCount() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.alarmCount;
        }
        return 10;
    }

    @NotNull
    public final ArrayList<Integer> getSupportedSportsTypeList() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (supportFunctionInfo == null) {
            return arrayList;
        }
        if (supportFunctionInfo.outdoor_run) {
            arrayList.add(48);
        }
        if (supportFunctionInfo.indoor_run) {
            arrayList.add(49);
        }
        if (supportFunctionInfo.sport_type0_run) {
            arrayList.add(2);
        }
        if (supportFunctionInfo.outdoor_walk) {
            arrayList.add(52);
        }
        if (supportFunctionInfo.sport_type0_walk) {
            arrayList.add(1);
        }
        if (supportFunctionInfo.indoor_walk) {
            arrayList.add(53);
        }
        if (supportFunctionInfo.sport_type0_on_foot) {
            arrayList.add(4);
        }
        if (supportFunctionInfo.outdoor_cycle) {
            arrayList.add(50);
        }
        if (supportFunctionInfo.indoor_cycle) {
            arrayList.add(51);
        }
        if (supportFunctionInfo.sport_type0_by_bike) {
            arrayList.add(3);
        }
        if (supportFunctionInfo.cricket) {
            arrayList.add(75);
        }
        if (supportFunctionInfo.sport_type2_footballl) {
            arrayList.add(22);
        }
        if (supportFunctionInfo.pool_swim) {
            arrayList.add(54);
        }
        if (supportFunctionInfo.open_water_swim) {
            arrayList.add(55);
        }
        if (supportFunctionInfo.sport_type2_yoga) {
            arrayList.add(18);
        }
        if (supportFunctionInfo.pilates) {
            arrayList.add(32);
        }
        if (supportFunctionInfo.sport_type3_dance) {
            arrayList.add(29);
        }
        if (supportFunctionInfo.zumba) {
            arrayList.add(35);
        }
        if (supportFunctionInfo.rower) {
            arrayList.add(57);
        }
        if (supportFunctionInfo.elliptical) {
            arrayList.add(56);
        }
        if (supportFunctionInfo.sport_type3_core_training) {
            arrayList.add(102);
        }
        if (supportFunctionInfo.sport_type_traditional_strength_training) {
            arrayList.add(110);
        }
        if (supportFunctionInfo.sport_type3_strength_training) {
            arrayList.add(101);
        }
        if (supportFunctionInfo.HIIT) {
            arrayList.add(58);
        }
        if (supportFunctionInfo.sport_type3_tidy_up_relax) {
            arrayList.add(104);
        }
        if (supportFunctionInfo.sport_type1_fitness) {
            arrayList.add(9);
        }
        if (supportFunctionInfo.sport_type0_swim) {
            arrayList.add(5);
        }
        if (supportFunctionInfo.sport_type0_mountain_climbing) {
            arrayList.add(6);
        }
        if (supportFunctionInfo.sport_type0_badminton) {
            arrayList.add(7);
        }
        if (supportFunctionInfo.sport_type1_spinning) {
            arrayList.add(10);
        }
        if (supportFunctionInfo.sport_type1_treadmill) {
            arrayList.add(12);
        }
        if (supportFunctionInfo.sport_type2_basketball) {
            arrayList.add(21);
        }
        if (supportFunctionInfo.sport_type2_tennis) {
            arrayList.add(24);
        }
        if (supportFunctionInfo.sport_type1_ellipsoid) {
            arrayList.add(11);
        }
        if (supportFunctionInfo.sport_type1_sit_up) {
            arrayList.add(13);
        }
        if (supportFunctionInfo.sport_type1_push_up) {
            arrayList.add(14);
        }
        if (supportFunctionInfo.sport_type1_dumbbell) {
            arrayList.add(15);
        }
        if (supportFunctionInfo.sport_type1_weightlifting) {
            arrayList.add(16);
        }
        if (supportFunctionInfo.sport_type2_bodybuilding_exercise) {
            arrayList.add(17);
        }
        if (supportFunctionInfo.sport_type2_rope_skipping) {
            arrayList.add(19);
        }
        if (supportFunctionInfo.sport_type2_volleyball) {
            arrayList.add(23);
        }
        if (supportFunctionInfo.sport_type2_table_tennis) {
            arrayList.add(20);
        }
        if (supportFunctionInfo.sport_type3_golf) {
            arrayList.add(25);
        }
        if (supportFunctionInfo.sport_type3_baseball) {
            arrayList.add(26);
        }
        if (supportFunctionInfo.sport_type3_skiing) {
            arrayList.add(27);
        }
        if (supportFunctionInfo.sport_type3_roller_skating) {
            arrayList.add(28);
        }
        if (supportFunctionInfo.sport_type0_other) {
            arrayList.add(8);
        }
        return arrayList;
    }

    public final int getWeatherType(@NotNull String weatherType) {
        Intrinsics.checkNotNullParameter(weatherType, "weatherType");
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clear", true)) {
            return 1;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clouds", true)) {
            return 2;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Rain", true)) {
            return 4;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Thunderstorm", true)) {
            return 5;
        }
        return StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Snow", true) ? 7 : 0;
    }
}
