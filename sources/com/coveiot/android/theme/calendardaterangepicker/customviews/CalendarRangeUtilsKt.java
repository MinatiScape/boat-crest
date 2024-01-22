package com.coveiot.android.theme.calendardaterangepicker.customviews;

import com.coveiot.android.theme.calendardaterangepicker.models.DateTimingNew;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CalendarRangeUtilsKt {

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DateTimingNew.values().length];
            try {
                iArr[DateTimingNew.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DateTimingNew.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean isDateSame(@NotNull Calendar one, @NotNull Calendar second) {
        Intrinsics.checkNotNullParameter(one, "one");
        Intrinsics.checkNotNullParameter(second, "second");
        return isMonthSame(one, second) && one.get(5) == second.get(5);
    }

    public static final boolean isMonthSame(@NotNull Calendar one, @NotNull Calendar second) {
        Intrinsics.checkNotNullParameter(one, "one");
        Intrinsics.checkNotNullParameter(second, "second");
        return one.get(1) == second.get(1) && one.get(2) == second.get(2);
    }

    @NotNull
    public static final String printDate(@Nullable Calendar calendar) {
        if (calendar != null) {
            String format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(calendar.time)");
            return format;
        }
        return "null";
    }

    public static final void resetTime(@NotNull Calendar date, @NotNull DateTimingNew dateTimingNew) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(dateTimingNew, "dateTimingNew");
        int i = WhenMappings.$EnumSwitchMapping$0[dateTimingNew.ordinal()];
        if (i == 1) {
            date.set(11, 0);
            date.set(12, 0);
            date.set(13, 0);
            date.set(14, 0);
        } else if (i != 2) {
            date.set(11, 0);
            date.set(12, 0);
            date.set(13, 0);
            date.set(14, 0);
        } else {
            date.set(11, 23);
            date.set(12, 59);
            date.set(13, 59);
            date.set(14, 999);
        }
    }
}
