package com.crrepa.n0;

import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes9.dex */
public final class r extends l {
    public static final Class<?>[] b = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* renamed from: a  reason: collision with root package name */
    public Object f7783a;

    public r(Boolean bool) {
        w(bool);
    }

    public r(Character ch) {
        w(ch);
    }

    public r(Number number) {
        w(number);
    }

    public r(Object obj) {
        w(obj);
    }

    public r(String str) {
        w(str);
    }

    public static boolean a(r rVar) {
        Object obj = rVar.f7783a;
        if (obj instanceof Number) {
            Number number = (Number) obj;
            return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
        }
        return false;
    }

    public static boolean e(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : b) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public Boolean A() {
        return (Boolean) this.f7783a;
    }

    @Override // com.crrepa.n0.l
    public BigDecimal b() {
        Object obj = this.f7783a;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(this.f7783a.toString());
    }

    @Override // com.crrepa.n0.l
    public BigInteger c() {
        Object obj = this.f7783a;
        return obj instanceof BigInteger ? (BigInteger) obj : new BigInteger(this.f7783a.toString());
    }

    @Override // com.crrepa.n0.l
    public boolean d() {
        return x() ? A().booleanValue() : Boolean.parseBoolean(r());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r.class != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        if (this.f7783a == null) {
            return rVar.f7783a == null;
        } else if (a(this) && a(rVar)) {
            return p().longValue() == rVar.p().longValue();
        } else {
            Object obj2 = this.f7783a;
            if ((obj2 instanceof Number) && (rVar.f7783a instanceof Number)) {
                double doubleValue = p().doubleValue();
                double doubleValue2 = rVar.p().doubleValue();
                if (doubleValue != doubleValue2) {
                    return Double.isNaN(doubleValue) && Double.isNaN(doubleValue2);
                }
                return true;
            }
            return obj2.equals(rVar.f7783a);
        }
    }

    @Override // com.crrepa.n0.l
    public byte f() {
        return y() ? p().byteValue() : Byte.parseByte(r());
    }

    @Override // com.crrepa.n0.l
    public char g() {
        return r().charAt(0);
    }

    @Override // com.crrepa.n0.l
    public double h() {
        return y() ? p().doubleValue() : Double.parseDouble(r());
    }

    public int hashCode() {
        long doubleToLongBits;
        if (this.f7783a == null) {
            return 31;
        }
        if (a(this)) {
            doubleToLongBits = p().longValue();
        } else {
            Object obj = this.f7783a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            doubleToLongBits = Double.doubleToLongBits(p().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    @Override // com.crrepa.n0.l
    public float i() {
        return y() ? p().floatValue() : Float.parseFloat(r());
    }

    @Override // com.crrepa.n0.l
    public int j() {
        return y() ? p().intValue() : Integer.parseInt(r());
    }

    @Override // com.crrepa.n0.l
    public long o() {
        return y() ? p().longValue() : Long.parseLong(r());
    }

    @Override // com.crrepa.n0.l
    public Number p() {
        Object obj = this.f7783a;
        return obj instanceof String ? new com.crrepa.p0.f((String) obj) : (Number) obj;
    }

    @Override // com.crrepa.n0.l
    public short q() {
        return y() ? p().shortValue() : Short.parseShort(r());
    }

    @Override // com.crrepa.n0.l
    public String r() {
        return y() ? p().toString() : x() ? A().toString() : (String) this.f7783a;
    }

    public void w(Object obj) {
        if (obj instanceof Character) {
            obj = String.valueOf(((Character) obj).charValue());
        } else {
            com.crrepa.p0.a.a((obj instanceof Number) || e(obj));
        }
        this.f7783a = obj;
    }

    public boolean x() {
        return this.f7783a instanceof Boolean;
    }

    public boolean y() {
        return this.f7783a instanceof Number;
    }

    public boolean z() {
        return this.f7783a instanceof String;
    }
}
