package com.crrepa.p0;

import java.io.ObjectStreamException;
import java.math.BigDecimal;
/* loaded from: classes9.dex */
public final class f extends Number {

    /* renamed from: a  reason: collision with root package name */
    private final String f7804a;

    public f(String str) {
        this.f7804a = str;
    }

    private Object a() throws ObjectStreamException {
        return new BigDecimal(this.f7804a);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.f7804a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            String str = this.f7804a;
            String str2 = ((f) obj).f7804a;
            return str == str2 || str.equals(str2);
        }
        return false;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.f7804a);
    }

    public int hashCode() {
        return this.f7804a.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            try {
                return Integer.parseInt(this.f7804a);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(this.f7804a);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(this.f7804a).intValue();
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.f7804a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.f7804a).longValue();
        }
    }

    public String toString() {
        return this.f7804a;
    }
}
