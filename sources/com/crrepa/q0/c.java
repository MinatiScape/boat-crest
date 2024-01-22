package com.crrepa.q0;

import com.crrepa.n0.v;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes9.dex */
public final class c extends x<Date> {
    public static final y c = new a();

    /* renamed from: a  reason: collision with root package name */
    public final DateFormat f7818a = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    public final DateFormat b = DateFormat.getDateTimeInstance(2, 2);

    /* loaded from: classes9.dex */
    public static class a implements y {
        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    @Override // com.crrepa.n0.x
    public synchronized void a(com.crrepa.t0.d dVar, Date date) throws IOException {
        if (date == null) {
            dVar.k();
        } else {
            dVar.e(this.f7818a.format(date));
        }
    }

    @Override // com.crrepa.n0.x
    /* renamed from: b */
    public Date a(com.crrepa.t0.a aVar) throws IOException {
        if (aVar.t() == com.crrepa.t0.c.NULL) {
            aVar.q();
            return null;
        }
        return c(aVar.r());
    }

    public final synchronized Date c(String str) {
        try {
            try {
                try {
                } catch (ParseException e) {
                    throw new v(str, e);
                }
            } catch (ParseException unused) {
                return com.crrepa.r0.a.a(str, new ParsePosition(0));
            }
        } catch (ParseException unused2) {
            return this.f7818a.parse(str);
        }
        return this.b.parse(str);
    }
}
