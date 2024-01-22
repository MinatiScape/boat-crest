package com.crrepa.n0;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes9.dex */
public final class a implements t<Date>, k<Date> {

    /* renamed from: a  reason: collision with root package name */
    public final DateFormat f7772a;
    public final DateFormat b;

    public a() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    public a(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    public a(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public a(DateFormat dateFormat, DateFormat dateFormat2) {
        this.f7772a = dateFormat;
        this.b = dateFormat2;
    }

    @Override // com.crrepa.n0.t
    /* renamed from: b */
    public l a(Date date, Type type, s sVar) {
        r rVar;
        synchronized (this.b) {
            rVar = new r(this.f7772a.format(date));
        }
        return rVar;
    }

    public final Date c(l lVar) {
        Date parse;
        synchronized (this.b) {
            try {
                try {
                    try {
                        parse = this.b.parse(lVar.r());
                    } catch (ParseException unused) {
                        return this.f7772a.parse(lVar.r());
                    }
                } catch (ParseException e) {
                    throw new v(lVar.r(), e);
                }
            } catch (ParseException unused2) {
                return com.crrepa.r0.a.a(lVar.r(), new ParsePosition(0));
            }
        }
        return parse;
    }

    @Override // com.crrepa.n0.k
    /* renamed from: d */
    public Date a(l lVar, Type type, j jVar) throws p {
        if (lVar instanceof r) {
            Date c = c(lVar);
            if (type == Date.class) {
                return c;
            }
            if (type == Timestamp.class) {
                return new Timestamp(c.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(c.getTime());
            }
            throw new IllegalArgumentException(a.class + " cannot deserialize to " + type);
        }
        throw new p("The date should be a string value");
    }

    public String toString() {
        return a.class.getSimpleName() + HexStringBuilder.COMMENT_BEGIN_CHAR + this.b.getClass().getSimpleName() + HexStringBuilder.COMMENT_END_CHAR;
    }
}
