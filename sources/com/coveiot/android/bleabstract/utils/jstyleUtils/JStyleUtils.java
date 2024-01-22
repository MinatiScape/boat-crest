package com.coveiot.android.bleabstract.utils.jstyleUtils;

import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class JStyleUtils {
    @NotNull
    public static final JStyleUtils INSTANCE = new JStyleUtils();

    @JvmStatic
    @NotNull
    public static final Calendar addMinutesToDate(@NotNull String dateTime, int i, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        calendar.add(12, i);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @JvmStatic
    @NotNull
    public static final Calendar addSecondsToDate(@NotNull String dateTime, int i, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        calendar.add(13, i);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Integer> convertSleepDetailsStringToArrayList(@NotNull String sleepDetails) {
        Intrinsics.checkNotNullParameter(sleepDetails, "sleepDetails");
        String replace$default = m.replace$default(StringsKt__StringsKt.trim(sleepDetails).toString(), HexStringBuilder.DEFAULT_SEPARATOR, Constants.SEPARATOR_COMMA, false, 4, (Object) null);
        if (!m.startsWith$default(replace$default, "[", false, 2, null)) {
            replace$default = '[' + replace$default + ']';
        }
        return getIntegerArrayListFromString(replace$default);
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Integer> convertStepsDetailsStringToArrayList(@NotNull String stepsDetails) {
        Intrinsics.checkNotNullParameter(stepsDetails, "stepsDetails");
        String replace$default = m.replace$default(StringsKt__StringsKt.trim(stepsDetails).toString(), HexStringBuilder.DEFAULT_SEPARATOR, Constants.SEPARATOR_COMMA, false, 4, (Object) null);
        if (!m.startsWith$default(replace$default, "[", false, 2, null)) {
            replace$default = '[' + replace$default + ']';
        }
        return getIntegerArrayListFromString(replace$default);
    }

    @JvmStatic
    @Nullable
    public static final String getActivityModeFor1790(int i) {
        switch (i) {
            case 1:
                return CoveApiConstants.CYCLE;
            case 2:
                return "BADMINTON";
            case 3:
                return "FOOTBALL";
            case 4:
                return "TENNIS";
            case 5:
                return "YOGA";
            case 6:
                return "MEDITATION";
            case 7:
                return "DANCE";
            case 8:
                return "BASKETBALL";
            case 9:
                return CoveApiConstants.HIKING;
            case 10:
                return "WORKOUT";
            default:
                return CoveApiConstants.RUN;
        }
    }

    @JvmStatic
    @Nullable
    public static final String getActivityModeFor1810G(int i) {
        switch (i) {
            case 1:
                return CoveApiConstants.CYCLE;
            case 2:
                return "BADMINTON";
            case 3:
                return "FOOTBALL";
            case 4:
                return "TENNIS";
            case 5:
                return "YOGA";
            case 6:
                return "MEDITATION";
            case 7:
                return "DANCE";
            case 8:
                return "BASKETBALL";
            case 9:
                return CoveApiConstants.HIKING;
            case 10:
                return "WORKOUT";
            default:
                return CoveApiConstants.RUN;
        }
    }

    @JvmStatic
    @Nullable
    public static final String getActivityModeFor1860(int i) {
        switch (i) {
            case 1:
                return CoveApiConstants.RUN;
            case 2:
                return CoveApiConstants.CYCLE;
            case 3:
                return "BADMINTON";
            case 4:
                return "FOOTBALL";
            case 5:
                return "TENNIS";
            case 6:
                return "YOGA";
            case 7:
                return "MEDITATION";
            case 8:
                return "DANCE";
            case 9:
                return "BASKETBALL";
            case 10:
                return CoveApiConstants.HIKING;
            case 11:
                return "WORKOUT";
            default:
                return "WALK";
        }
    }

    @JvmStatic
    @Nullable
    public static final String getActivityModeFor1963D(int i) {
        switch (i) {
            case 0:
                return CoveApiConstants.RUN;
            case 1:
                return CoveApiConstants.CYCLE;
            case 2:
                return "BADMINTON";
            case 3:
                return "FOOTBALL";
            case 4:
                return "TENNIS";
            case 5:
                return "YOGA";
            case 6:
                return "MEDITATION";
            case 7:
            default:
                return "DANCE";
            case 8:
                return "BASKETBALL";
            case 9:
                return CoveApiConstants.HIKING;
            case 10:
                return "WORKOUT";
        }
    }

    @JvmStatic
    @Nullable
    public static final String getActivityModeFor2208(int i) {
        switch (i) {
            case 0:
                return CoveApiConstants.RUN;
            case 1:
                return CoveApiConstants.CYCLE;
            case 2:
                return "BADMINTON";
            case 3:
                return "FOOTBALL";
            case 4:
                return "TENNIS";
            case 5:
                return "YOGA";
            case 6:
            case 9:
            default:
                return "WALK";
            case 7:
                return "DANCE";
            case 8:
                return "BASKETBALL";
            case 10:
                return "WORKOUT";
            case 11:
                return "CRICKET";
            case 12:
                return CoveApiConstants.HIKING;
            case 13:
                return "AEROBICS";
            case 14:
                return "PING_PONG";
            case 15:
                return "SKIPPING";
            case 16:
                return "SIT_UPS";
            case 17:
                return "VOLLEYBALL";
        }
    }

    @JvmStatic
    @Nullable
    public static final Integer getAvgValueFromList(@Nullable List<Integer> list) {
        ArrayList<Integer> b = INSTANCE.b(list);
        int i = 0;
        if (b.size() != 0) {
            int size = b.size();
            int i2 = 0;
            while (i < size) {
                Integer num = b.get(i);
                Intrinsics.checkNotNullExpressionValue(num, "nonZeroList[i]");
                i2 += num.intValue();
                i++;
            }
            i = i2 / b.size();
        }
        return Integer.valueOf(i);
    }

    @JvmStatic
    @Nullable
    public static final Double getAvgValueFromListofDouble(@Nullable List<Double> list) {
        ArrayList<Double> a2 = INSTANCE.a(list);
        double d = 0.0d;
        if (a2.size() != 0) {
            int size = a2.size();
            for (int i = 0; i < size; i++) {
                Double d2 = a2.get(i);
                Intrinsics.checkNotNullExpressionValue(d2, "nonZeroList[i]");
                d += d2.doubleValue();
            }
            d /= a2.size();
        }
        return Double.valueOf(d);
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentDate() {
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(calendar)");
        return format;
    }

    @JvmStatic
    @Nullable
    public static final Date getDateFromString(@Nullable String str, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        try {
            return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Double> getDoubleArrayListFromString(@Nullable String str) {
        Object fromJson = new Gson().fromJson(str, new TypeToken<ArrayList<Double>>() { // from class: com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils$getDoubleArrayListFromString$listType$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson()\n            .fromJson(value, listType)");
        return (ArrayList) fromJson;
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

    @NotNull
    public static final ArrayList<Integer> getEmptyHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptyHourCodedValuesList$annotations() {
    }

    @NotNull
    public static final ArrayList<Double> getEmptyHourCodedValuesListofDouble() {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            arrayList.add(Double.valueOf(0.0d));
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptyHourCodedValuesListofDouble$annotations() {
    }

    @NotNull
    public static final ArrayList<Integer> getEmptySleepHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            arrayList.add(-1);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptySleepHourCodedValuesList$annotations() {
    }

    @JvmStatic
    @Nullable
    public static final String getFormattedDate(@Nullable String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(simpleDateFormat2.parse(simpleDateFormat2.format(simpleDateFormat.parse(str))));
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Integer> getIntegerArrayListFromString(@Nullable String str) {
        Object fromJson = new Gson().fromJson(str, new TypeToken<ArrayList<Integer>>() { // from class: com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils$getIntegerArrayListFromString$listType$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson()\n            .fromJson(value, listType)");
        return (ArrayList) fromJson;
    }

    @JvmStatic
    @Nullable
    public static final Integer getMaxValueFromList(@Nullable List<Integer> list) {
        if (list != null && !list.isEmpty()) {
            return (Integer) Collections.max(list);
        }
        return 0;
    }

    @JvmStatic
    @Nullable
    public static final Double getMaxValueFromListofDouble(@Nullable List<Double> list) {
        if (list != null && !list.isEmpty()) {
            return (Double) Collections.max(list);
        }
        return Double.valueOf(0.0d);
    }

    @JvmStatic
    @Nullable
    public static final Integer getMinValueFromList(@Nullable List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        ArrayList<Integer> b = INSTANCE.b(list);
        if (b.size() <= 0) {
            return 0;
        }
        return (Integer) Collections.min(b);
    }

    @JvmStatic
    @Nullable
    public static final Double getMinValueFromListofDouble(@Nullable List<Double> list) {
        Double valueOf = Double.valueOf(0.0d);
        if (list == null || list.isEmpty()) {
            return valueOf;
        }
        ArrayList<Double> a2 = INSTANCE.a(list);
        return a2.size() > 0 ? (Double) Collections.min(a2) : valueOf;
    }

    @JvmStatic
    public static final int getMinutesDifference(@NotNull Calendar calendar1, @NotNull Calendar calendar2) {
        Intrinsics.checkNotNullParameter(calendar1, "calendar1");
        Intrinsics.checkNotNullParameter(calendar2, "calendar2");
        return (int) Math.ceil(((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / 1000) / 60);
    }

    @JvmStatic
    @Nullable
    public static final String getStringFromIntegerArrayList(@Nullable List<Integer> list) {
        return new Gson().toJson(list);
    }

    @JvmStatic
    @Nullable
    public static final String getYYYYMMDDHHMMSSFormattedDate(@NotNull String mDate) {
        Intrinsics.checkNotNullParameter(mDate, "mDate");
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss", locale);
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).format(simpleDateFormat.parse(mDate));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final boolean isEndTimeInSameDay(@NotNull String dateTime, @NotNull String detailSleep, int i, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(detailSleep, "detailSleep");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        return calendar.get(5) == addMinutesToDate(dateTime, convertSleepDetailsStringToArrayList(detailSleep).size() * i, simpleDateFormat).get(5);
    }

    @JvmStatic
    public static final boolean isEndTimeInSameHour(@NotNull String dateTime, @NotNull String detailSteps, int i, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(detailSteps, "detailSteps");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        return calendar.get(11) == addMinutesToDate(dateTime, convertStepsDetailsStringToArrayList(detailSteps).size() * i, simpleDateFormat).get(11);
    }

    @JvmStatic
    public static final boolean isFutureDate(@NotNull String date) {
        String formattedDate;
        Intrinsics.checkNotNullParameter(date, "date");
        try {
            formattedDate = getFormattedDate(date);
        } catch (Exception unused) {
            formattedDate = getFormattedDate(date, "yyyy-MM-dd");
        }
        Date parseDate = AppUtils.parseDate(formattedDate, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(parseDate);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return calendar2.getTimeInMillis() > calendar.getTimeInMillis();
    }

    @JvmStatic
    @NotNull
    public static final Calendar setTimeToMidNight(@NotNull String dateTime, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        calendar.add(5, 1);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(11, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @JvmStatic
    @NotNull
    public static final Calendar setTimeToNextHour(@NotNull String dateTime, @NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        Intrinsics.checkNotNullParameter(simpleDateFormat, "simpleDateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
        calendar.set(11, calendar.get(11) + 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    public final ArrayList<Double> a(List<Double> list) {
        ArrayList<Double> arrayList = new ArrayList<>();
        if (list != null && (!list.isEmpty())) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i) != null) {
                    if (!(list.get(i).doubleValue() == 0.0d)) {
                        arrayList.add(list.get(i));
                    }
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<Integer> b(List<Integer> list) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (list != null && (!list.isEmpty())) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i) != null && list.get(i).intValue() != 0) {
                    arrayList.add(list.get(i));
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    @Nullable
    public static final String getFormattedDate(@Nullable String str, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String str2 = null;
        try {
            str2 = simpleDateFormat2.format(simpleDateFormat.parse(str));
            return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(simpleDateFormat2.parse(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    @JvmStatic
    public static final boolean isFutureDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return calendar2.getTimeInMillis() > calendar.getTimeInMillis();
    }
}
