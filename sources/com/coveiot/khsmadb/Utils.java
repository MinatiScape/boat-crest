package com.coveiot.khsmadb;

import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class Utils {
    @NotNull
    public static final Utils INSTANCE = new Utils();

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
    public final Calendar convertSDKTimeToCalendarTillMinute(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.add(13, i);
        calendar.set(13, 0);
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

    @NotNull
    public final Pair<Long, Long> getFromAndToTime(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(AppUtils.parseDate(date, "yyyy-MM-dd"));
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(6, -1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(AppUtils.parseDate(date, "yyyy-MM-dd"));
        calendar2.set(11, 11);
        calendar2.set(12, 59);
        calendar2.set(13, 59);
        calendar2.set(14, 999);
        long j = 1000;
        return new Pair<>(Long.valueOf((calendar2.getTimeInMillis() - getCalenderFor2000().getTimeInMillis()) / j), Long.valueOf((calendar.getTimeInMillis() - getCalenderFor2000().getTimeInMillis()) / j));
    }
}
