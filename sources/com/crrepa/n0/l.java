package com.crrepa.n0;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes9.dex */
public abstract class l {
    public BigDecimal b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public BigInteger c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public byte f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public char g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double h() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public float i() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int j() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public i k() {
        if (s()) {
            return (i) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public n l() {
        if (t()) {
            return (n) this;
        }
        throw new IllegalStateException("This is not a JSON Null.");
    }

    public o m() {
        if (u()) {
            return (o) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public r n() {
        if (v()) {
            return (r) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public long o() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number p() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public short q() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String r() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean s() {
        return this instanceof i;
    }

    public boolean t() {
        return this instanceof n;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            com.crrepa.t0.d dVar = new com.crrepa.t0.d(stringWriter);
            dVar.b(true);
            com.crrepa.p0.k.a(this, dVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public boolean u() {
        return this instanceof o;
    }

    public boolean v() {
        return this instanceof r;
    }
}
