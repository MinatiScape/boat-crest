package com.crrepa.q0;

import com.crrepa.n0.v;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/* loaded from: classes9.dex */
public final class j extends x<Date> {
    public static final y b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final DateFormat f7824a = new SimpleDateFormat("MMM d, yyyy");

    /* loaded from: classes9.dex */
    public static class a implements y {
        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // com.crrepa.n0.x
    public synchronized void a(com.crrepa.t0.d dVar, Date date) throws IOException {
        dVar.e(date == null ? null : this.f7824a.format((java.util.Date) date));
    }

    @Override // com.crrepa.n0.x
    /* renamed from: b */
    public synchronized Date a(com.crrepa.t0.a aVar) throws IOException {
        if (aVar.t() == com.crrepa.t0.c.NULL) {
            aVar.q();
            return null;
        }
        try {
            return new Date(this.f7824a.parse(aVar.r()).getTime());
        } catch (ParseException e) {
            throw new v(e);
        }
    }
}
