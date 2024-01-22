package com.coveiot.android.bleabstract.utils.smaUtils;

import com.coveiot.coveaccess.constants.CoveApiConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmaUtils {
    @NotNull
    public static final SmaUtils INSTANCE = new SmaUtils();

    @NotNull
    public static final ArrayList<Integer> getEmpty5MinIntervalHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmpty5MinIntervalHourCodedValuesList$annotations() {
    }

    @JvmStatic
    public static final int getMinutesDifference(@NotNull Calendar calendar1, @NotNull Calendar calendar2) {
        Intrinsics.checkNotNullParameter(calendar1, "calendar1");
        Intrinsics.checkNotNullParameter(calendar2, "calendar2");
        return (int) Math.ceil(((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / 1000) / 60);
    }

    @Nullable
    public final String convertSDKTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, i);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    @NotNull
    public final Calendar convertSDKTimeToCalender(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, i);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final String convertSDKTimeToDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, i);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    @NotNull
    public final String convertTimeToDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public final int covertCalendarToSdkTime(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return (int) ((calendar.getTimeInMillis() - getCalenderFor2000().getTimeInMillis()) / 1000);
    }

    @Nullable
    public final String getActivityMode(int i) {
        switch (i) {
            case 2:
            case 12:
            case 20:
                return "WALK";
            case 3:
            case 7:
            case 9:
                return CoveApiConstants.RUN;
            case 4:
            case 5:
            case 6:
            case 31:
            case 100:
            case 108:
            case 109:
            default:
                return "OTHER";
            case 8:
                return "TREADMILL";
            case 10:
                return CoveApiConstants.CYCLE;
            case 11:
                return CoveApiConstants.SWIM;
            case 13:
                return "CLIMBING";
            case 14:
                return "YOGA";
            case 15:
                return "SPINNING";
            case 16:
                return "BASKETBALL";
            case 17:
                return "FOOTBALL";
            case 18:
                return "BADMINTON";
            case 19:
                return "MARATHON";
            case 21:
                return "FREE_EXERCISE";
            case 22:
                return "AEROBICS_GYMS";
            case 23:
                return "STRENGTH_TRAINING";
            case 24:
                return "WEIGHTLIFTING";
            case 25:
                return "BOXING";
            case 26:
                return "SKIPPING";
            case 27:
                return "CLIMB_STAIRS";
            case 28:
                return "SKI";
            case 29:
                return "SKATING";
            case 30:
                return "ROLLER_SKATING";
            case 32:
                return "HULA_HOOP";
            case 33:
                return "GOLF";
            case 34:
                return "BASEBALL";
            case 35:
                return "DANCE";
            case 36:
                return "PING_PONG";
            case 37:
                return "HOCKEY";
            case 38:
                return "PILATES";
            case 39:
                return "TAEKWONDO";
            case 40:
                return "HANDBALL";
            case 41:
                return "STREET_DANCE";
            case 42:
                return "VOLLEYBALL";
            case 43:
                return "TENNIS";
            case 44:
                return "DARTS";
            case 45:
                return "GYMNASTICS";
            case 46:
                return "STEPPER";
            case 47:
                return "ELLIPTICAL";
            case 48:
                return "ZUMBA";
            case 49:
                return "CRICKET";
            case 50:
                return "TREKKING";
            case 51:
                return "AEROBICS";
            case 52:
                return "ROWING_MACHINE";
            case 53:
                return "RUGBY";
            case 54:
                return "SIT_UPS";
            case 55:
                return "DUMBBELLS";
            case 56:
                return "WORKOUT";
            case 57:
                return "KARATE";
            case 58:
                return "FENCING";
            case 59:
                return "MARTIAL_ARTS";
            case 60:
                return "TAI_CHI";
            case 61:
                return "FRISBEE";
            case 62:
                return "ARCHERY";
            case 63:
                return "HORSE_RIDING";
            case 64:
                return "BOWLING";
            case 65:
                return "SURFING";
            case 66:
                return "SOFTBALL";
            case 67:
                return "SQUASH";
            case 68:
                return "SAILBOATING";
            case 69:
                return "PULL_UPS";
            case 70:
                return "SKATEBOARDING";
            case 71:
                return "TRAMPOLINE";
            case 72:
                return "FISHING";
            case 73:
                return "POLE_DANCE";
            case 74:
                return "SQUARE_DANCE";
            case 75:
                return "JAZZ_DANCE";
            case 76:
                return "BALLET";
            case 77:
                return "DISCO";
            case 78:
                return "TAP_DANCE";
            case 79:
                return "MODERN_DANCE";
            case 80:
                return "PUSH_UPS";
            case 81:
                return "SCOOTER";
            case 82:
                return "PLANK";
            case 83:
                return "BILLIARDS";
            case 84:
                return "ROCK_CLIMBING";
            case 85:
                return "DISC_THROW";
            case 86:
                return "HORSE_RACING";
            case 87:
                return "WRESTLING";
            case 88:
                return "HIGH_JUMP";
            case 89:
                return "PARACHUTE";
            case 90:
                return "SHOT_PUT";
            case 91:
                return "LONG_JUMP";
            case 92:
                return "JAVELIN";
            case 93:
                return "HAMMER";
            case 94:
                return "SQUAT";
            case 95:
                return "LEG_PRESS";
            case 96:
                return "OFF_ROAD_BIKE";
            case 97:
                return "MOTOCROSS";
            case 98:
                return "ROWING";
            case 99:
                return "CROSS_FIT";
            case 101:
                return "KAYAKING";
            case 102:
                return "CROQUET";
            case 103:
                return "FLOORBALL";
            case 104:
                return "MUAY_THAI";
            case 105:
                return "JAI_ALAI";
            case 106:
                return "TENNIS_DOUBLES";
            case 107:
                return "BACK_TRAINING";
            case 110:
                return "MOUNTAINEERING";
            case 111:
                return "HIIT";
            case 112:
                return "BODY_COMBAT";
            case 113:
                return "BODY_BALANCING";
            case 114:
                return "TRX";
            case 115:
                return "TAE_BO";
        }
    }

    @Nullable
    public final String getActivitySite(int i) {
        return (i == 8 || i != 9) ? "INDOOR" : "OUTDOOR";
    }

    @NotNull
    public final Calendar getCalenderFor2000() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @Nullable
    public final Date getDateFromString(@Nullable String str, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        try {
            return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @NotNull
    public final Calendar getDayEndTimeForGivenDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final Calendar getDayStartTimeForGivenDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final Calendar getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
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
            return 6;
        }
        return StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Snow", true) ? 8 : 0;
    }
}
