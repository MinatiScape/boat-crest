package com.coveiot.android.customreminders.utils;

import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ExtensionsKt {
    @NotNull
    public static final String toFormattedDateStr(@NotNull Calendar calendar, @NotNull String format) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        String formatDate = AppUtils.formatDate(calendar.getTime(), format);
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(time, format)");
        return formatDate;
    }
}
