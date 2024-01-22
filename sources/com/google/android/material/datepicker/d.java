package com.google.android.material.datepicker;

import android.os.Build;
import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.ido.ble.protocol.model.V3MessageNotice;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes10.dex */
public class d {
    public static Pair<String, String> a(@Nullable Long l, @Nullable Long l2) {
        return b(l, l2, null);
    }

    public static Pair<String, String> b(@Nullable Long l, @Nullable Long l2, @Nullable SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return Pair.create(null, null);
        }
        if (l == null) {
            return Pair.create(null, d(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return Pair.create(d(l.longValue(), simpleDateFormat), null);
        }
        Calendar o = j.o();
        Calendar q = j.q();
        q.setTimeInMillis(l.longValue());
        Calendar q2 = j.q();
        q2.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return Pair.create(simpleDateFormat.format(new Date(l.longValue())), simpleDateFormat.format(new Date(l2.longValue())));
        } else if (q.get(1) == q2.get(1)) {
            if (q.get(1) == o.get(1)) {
                return Pair.create(f(l.longValue(), Locale.getDefault()), f(l2.longValue(), Locale.getDefault()));
            }
            return Pair.create(f(l.longValue(), Locale.getDefault()), k(l2.longValue(), Locale.getDefault()));
        } else {
            return Pair.create(k(l.longValue(), Locale.getDefault()), k(l2.longValue(), Locale.getDefault()));
        }
    }

    public static String c(long j) {
        return d(j, null);
    }

    public static String d(long j, @Nullable SimpleDateFormat simpleDateFormat) {
        Calendar o = j.o();
        Calendar q = j.q();
        q.setTimeInMillis(j);
        if (simpleDateFormat != null) {
            return simpleDateFormat.format(new Date(j));
        }
        if (o.get(1) == q.get(1)) {
            return e(j);
        }
        return j(j);
    }

    public static String e(long j) {
        return f(j, Locale.getDefault());
    }

    public static String f(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return j.c(locale).format(new Date(j));
        }
        return j.j(locale).format(new Date(j));
    }

    public static String g(long j) {
        return h(j, Locale.getDefault());
    }

    public static String h(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return j.d(locale).format(new Date(j));
        }
        return j.h(locale).format(new Date(j));
    }

    public static String i(long j) {
        return DateUtils.formatDateTime(null, j, V3MessageNotice.TYPE_YOUTUBE);
    }

    public static String j(long j) {
        return k(j, Locale.getDefault());
    }

    public static String k(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return j.s(locale).format(new Date(j));
        }
        return j.i(locale).format(new Date(j));
    }

    public static String l(long j) {
        return m(j, Locale.getDefault());
    }

    public static String m(long j, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return j.t(locale).format(new Date(j));
        }
        return j.h(locale).format(new Date(j));
    }
}
