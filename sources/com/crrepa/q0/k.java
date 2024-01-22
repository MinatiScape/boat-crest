package com.crrepa.q0;

import com.crrepa.n0.v;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes9.dex */
public final class k extends x<Time> {
    public static final y b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final DateFormat f7825a = new SimpleDateFormat("hh:mm:ss a");

    /* loaded from: classes9.dex */
    public static class a implements y {
        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // com.crrepa.n0.x
    public synchronized void a(com.crrepa.t0.d dVar, Time time) throws IOException {
        dVar.e(time == null ? null : this.f7825a.format((Date) time));
    }

    @Override // com.crrepa.n0.x
    /* renamed from: b */
    public synchronized Time a(com.crrepa.t0.a aVar) throws IOException {
        if (aVar.t() == com.crrepa.t0.c.NULL) {
            aVar.q();
            return null;
        }
        try {
            return new Time(this.f7825a.parse(aVar.r()).getTime());
        } catch (ParseException e) {
            throw new v(e);
        }
    }
}
