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
public final class TimeConverter {
    @NotNull
    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    @NotNull
    public static final String FORMAT_STR_2 = "yyyy-MM-dd";
    @NotNull
    public static final TimeConverter INSTANCE = new TimeConverter();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<SimpleDateFormat> f4618a = new ThreadLocal<>();
    @NotNull
    public static final ThreadLocal<SimpleDateFormat> b = new ThreadLocal<>();

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
    public static final String fromDateToYYYYMMHH(@Nullable Date date) {
        String format = INSTANCE.b().format(date);
        Intrinsics.checkNotNullExpressionValue(format, "format2.format(date)");
        return format;
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final Date fromStr(@Nullable String str) {
        try {
            return INSTANCE.a().parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return DateConverter.INSTANCE.getDEFAULT_DATE();
        }
    }

    public final SimpleDateFormat a() {
        ThreadLocal<SimpleDateFormat> threadLocal = f4618a;
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            threadLocal.set(simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }

    public final SimpleDateFormat b() {
        ThreadLocal<SimpleDateFormat> threadLocal = b;
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            threadLocal.set(simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }
}
