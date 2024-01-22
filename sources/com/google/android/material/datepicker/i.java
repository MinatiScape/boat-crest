package com.google.android.material.datepicker;

import androidx.annotation.Nullable;
import java.util.Calendar;
import java.util.TimeZone;
/* loaded from: classes10.dex */
public class i {
    public static final i c = new i(null, null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Long f10289a;
    @Nullable
    public final TimeZone b;

    public i(@Nullable Long l, @Nullable TimeZone timeZone) {
        this.f10289a = l;
        this.b = timeZone;
    }

    public static i c() {
        return c;
    }

    public Calendar a() {
        return b(this.b);
    }

    public Calendar b(@Nullable TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f10289a;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
