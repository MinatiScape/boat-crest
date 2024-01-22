package com.coveiot.android.khmatrixdb;

import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class Utils {
    @NotNull
    public static final Utils INSTANCE = new Utils();

    @NotNull
    public final Calendar convertSDKTimeToCalender(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final String convertSDKTimeToDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
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
        return new Pair<>(Long.valueOf(calendar2.getTimeInMillis()), Long.valueOf(calendar.getTimeInMillis()));
    }
}
