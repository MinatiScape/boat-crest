package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.icu.text.DateFormat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes10.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static AtomicReference<i> f10290a = new AtomicReference<>();

    public static long a(long j) {
        Calendar q = q();
        q.setTimeInMillis(j);
        return f(q).getTimeInMillis();
    }

    public static int b(@NonNull String str, @NonNull String str2, int i, int i2) {
        while (i2 >= 0 && i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            if (str.charAt(i2) == '\'') {
                do {
                    i2 += i;
                    if (i2 >= 0 && i2 < str.length()) {
                    }
                } while (str.charAt(i2) != '\'');
            }
            i2 += i;
        }
        return i2;
    }

    @TargetApi(24)
    public static DateFormat c(Locale locale) {
        return e("MMMd", locale);
    }

    @TargetApi(24)
    public static DateFormat d(Locale locale) {
        return e("MMMEd", locale);
    }

    @TargetApi(24)
    public static DateFormat e(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(p());
        return instanceForSkeleton;
    }

    public static Calendar f(Calendar calendar) {
        Calendar r = r(calendar);
        Calendar q = q();
        q.set(r.get(1), r.get(2), r.get(5));
        return q;
    }

    public static java.text.DateFormat g(int i, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i, locale);
        dateInstance.setTimeZone(n());
        return dateInstance;
    }

    public static java.text.DateFormat h(Locale locale) {
        return g(0, locale);
    }

    public static java.text.DateFormat i(Locale locale) {
        return g(2, locale);
    }

    public static java.text.DateFormat j(Locale locale) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) i(locale);
        simpleDateFormat.applyPattern(u(simpleDateFormat.toPattern()));
        return simpleDateFormat;
    }

    public static SimpleDateFormat k() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern().replaceAll("\\s+", ""), Locale.getDefault());
        simpleDateFormat.setTimeZone(n());
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    public static String l(Resources resources, SimpleDateFormat simpleDateFormat) {
        String pattern = simpleDateFormat.toPattern();
        String string = resources.getString(R.string.mtrl_picker_text_input_year_abbr);
        String string2 = resources.getString(R.string.mtrl_picker_text_input_month_abbr);
        String string3 = resources.getString(R.string.mtrl_picker_text_input_day_abbr);
        if (pattern.replaceAll("[^y]", "").length() == 1) {
            pattern = pattern.replace(EllipticCurveJsonWebKey.Y_MEMBER_NAME, "yyyy");
        }
        return pattern.replace("d", string3).replace("M", string2).replace(EllipticCurveJsonWebKey.Y_MEMBER_NAME, string);
    }

    public static i m() {
        i iVar = f10290a.get();
        return iVar == null ? i.c() : iVar;
    }

    public static TimeZone n() {
        return TimeZone.getTimeZone("UTC");
    }

    public static Calendar o() {
        Calendar a2 = m().a();
        a2.set(11, 0);
        a2.set(12, 0);
        a2.set(13, 0);
        a2.set(14, 0);
        a2.setTimeZone(n());
        return a2;
    }

    @TargetApi(24)
    public static android.icu.util.TimeZone p() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    public static Calendar q() {
        return r(null);
    }

    public static Calendar r(@Nullable Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(n());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    @TargetApi(24)
    public static DateFormat s(Locale locale) {
        return e("yMMMd", locale);
    }

    @TargetApi(24)
    public static DateFormat t(Locale locale) {
        return e("yMMMEd", locale);
    }

    @NonNull
    public static String u(@NonNull String str) {
        int b = b(str, "yY", 1, 0);
        if (b >= str.length()) {
            return str;
        }
        String str2 = "EMd";
        int b2 = b(str, "EMd", 1, b);
        if (b2 < str.length()) {
            str2 = "EMd" + Constants.SEPARATOR_COMMA;
        }
        return str.replace(str.substring(b(str, str2, -1, b) + 1, b2), HexStringBuilder.DEFAULT_SEPARATOR).trim();
    }
}
