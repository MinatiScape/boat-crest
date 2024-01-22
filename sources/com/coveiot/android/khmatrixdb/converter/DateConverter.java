package com.coveiot.android.khmatrixdb.converter;

import androidx.room.TypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DateConverter {
    @NotNull
    public static final String FORMAT_STR = "yyyy-MM-dd";
    @NotNull
    public static final DateConverter INSTANCE = new DateConverter();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<SimpleDateFormat> f4617a = new ThreadLocal<>();
    @NotNull
    public static Date b = new Date(1900, 0, 1);

    @JvmStatic
    @TypeConverter
    @NotNull
    public static final String fromDate(@Nullable Date date) {
        String format = INSTANCE.a().format(date);
        Intrinsics.checkNotNullExpressionValue(format, "format.format(date)");
        return format;
    }

    @JvmStatic
    @TypeConverter
    @NotNull
    public static final Date fromStr(@Nullable String str) {
        try {
            Date parse = INSTANCE.a().parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "format.parse(str)");
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return b;
        }
    }

    public final SimpleDateFormat a() {
        ThreadLocal<SimpleDateFormat> threadLocal = f4617a;
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            threadLocal.set(simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }

    @NotNull
    public final Date getDEFAULT_DATE() {
        return b;
    }

    public final void setDEFAULT_DATE(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        b = date;
    }
}
