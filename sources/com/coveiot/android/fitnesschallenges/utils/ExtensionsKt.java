package com.coveiot.android.fitnesschallenges.utils;

import android.content.Context;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ExtensionsKt {
    @NotNull
    public static final String formatToOneDecimalPlace(float f) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public static final String formatToTwoDecimalPlace(float f) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.02f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public static final Date formateDateAndTime(@NotNull Date date, boolean z) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            if (z) {
                Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
                setCalendarTime(calendar, 0, 0, 0, 0);
            } else {
                Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
                setCalendarTime(calendar, 23, 59, 59, 999);
            }
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "{\n        if (isStartDat…      calendar.time\n    }");
            return time;
        } catch (Exception unused) {
            return date;
        }
    }

    @NotNull
    public static final String getDaysLeftCalculation(@NotNull Context context, @NotNull String startDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date parse = simpleDateFormat.parse(startDate);
            Date parse2 = simpleDateFormat.parse(endDate);
            String format = simpleDateFormat.format(date);
            if (parse == null || parse2 == null) {
                return "";
            }
            long time = parse.getTime() - date.getTime();
            long time2 = parse2.getTime() - date.getTime();
            long j = (long) TimeConstants.DAY;
            long j2 = time / j;
            long j3 = time2 / j;
            int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i == 0) {
                if (Intrinsics.areEqual(format, startDate)) {
                    String string = context.getString(R.string.challenge_started);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.challenge_started)");
                    return string;
                }
                ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                if (themesUtils.timeConversionToHHMM(Long.valueOf(time)) != null) {
                    return context.getString(R.string.challenge_starts_in) + ' ' + themesUtils.timeConversionToHHMM(Long.valueOf(time));
                }
                return context.getString(R.string.challenge_starts_in) + ' ' + (j2 + 1) + ' ' + context.getString(R.string.day);
            }
            int i2 = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
            if (i2 == 0) {
                if (Intrinsics.areEqual(format, endDate)) {
                    parse2.setHours(24);
                    parse2.setMinutes(0);
                    long time3 = parse2.getTime() - date.getTime();
                    return context.getString(R.string.challenge_ends_in) + ' ' + ThemesUtils.INSTANCE.timeConversionToHHMM(Long.valueOf(time3));
                }
                return context.getString(R.string.challenge_ends_in) + ' ' + (j3 + 1) + ' ' + context.getString(R.string.day);
            } else if (i > 0) {
                return context.getString(R.string.challenge_starts_in) + ' ' + (j2 + 1) + ' ' + context.getString(R.string.days_small);
            } else if (i2 > 0) {
                return context.getString(R.string.challenge_ends_in) + ' ' + (j3 + 1) + ' ' + context.getString(R.string.days_small);
            } else {
                String string2 = context.getString(R.string.challenge_ended);
                Intrinsics.checkNotNullExpressionValue(string2, "{\n                contex…enge_ended)\n            }");
                return string2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static final void setCalendarTime(@NotNull Calendar calendar, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
    }

    @NotNull
    public static final String toFormattedDateStr(@NotNull Calendar calendar, @NotNull String format) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        String formatDate = AppUtils.formatDate(calendar.getTime(), format);
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(time, format)");
        return formatDate;
    }
}
