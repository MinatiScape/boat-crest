package com.crrepa.q0;

import com.crrepa.n0.o;
import com.crrepa.n0.r;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class f extends com.crrepa.t0.d {
    public static final Writer v = new a();
    public static final r w = new r("closed");
    public final List<com.crrepa.n0.l> s;
    public String t;
    public com.crrepa.n0.l u;

    /* loaded from: classes9.dex */
    public static class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public f() {
        super(v);
        this.s = new ArrayList();
        this.u = com.crrepa.n0.n.f7780a;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d a(double d) throws IOException {
        if (i() || !(Double.isNaN(d) || Double.isInfinite(d))) {
            r(new r((Number) Double.valueOf(d)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d);
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d a(long j) throws IOException {
        r(new r((Number) Long.valueOf(j)));
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d a(Boolean bool) throws IOException {
        if (bool == null) {
            return k();
        }
        r(new r(bool));
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d a(Number number) throws IOException {
        if (number == null) {
            return k();
        }
        if (!i()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        r(new r(number));
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d b(String str) throws IOException {
        if (this.s.isEmpty() || this.t != null) {
            throw new IllegalStateException();
        }
        if (s() instanceof o) {
            this.t = str;
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d c() throws IOException {
        com.crrepa.n0.i iVar = new com.crrepa.n0.i();
        r(iVar);
        this.s.add(iVar);
        return this;
    }

    @Override // com.crrepa.t0.d, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.s.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.s.add(w);
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d d() throws IOException {
        o oVar = new o();
        r(oVar);
        this.s.add(oVar);
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d d(boolean z) throws IOException {
        r(new r(Boolean.valueOf(z)));
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d e() throws IOException {
        if (this.s.isEmpty() || this.t != null) {
            throw new IllegalStateException();
        }
        if (s() instanceof com.crrepa.n0.i) {
            List<com.crrepa.n0.l> list = this.s;
            list.remove(list.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d e(String str) throws IOException {
        if (str == null) {
            return k();
        }
        r(new r(str));
        return this;
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d f() throws IOException {
        if (this.s.isEmpty() || this.t != null) {
            throw new IllegalStateException();
        }
        if (s() instanceof o) {
            List<com.crrepa.n0.l> list = this.s;
            list.remove(list.size() - 1);
            return this;
        }
        throw new IllegalStateException();
    }

    @Override // com.crrepa.t0.d, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // com.crrepa.t0.d
    public com.crrepa.t0.d k() throws IOException {
        r(com.crrepa.n0.n.f7780a);
        return this;
    }

    public com.crrepa.n0.l n() {
        if (this.s.isEmpty()) {
            return this.u;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.s);
    }

    public final void r(com.crrepa.n0.l lVar) {
        if (this.t != null) {
            if (!lVar.t() || g()) {
                ((o) s()).a(this.t, lVar);
            }
            this.t = null;
        } else if (this.s.isEmpty()) {
            this.u = lVar;
        } else {
            com.crrepa.n0.l s = s();
            if (!(s instanceof com.crrepa.n0.i)) {
                throw new IllegalStateException();
            }
            ((com.crrepa.n0.i) s).a(lVar);
        }
    }

    public final com.crrepa.n0.l s() {
        List<com.crrepa.n0.l> list = this.s;
        return list.get(list.size() - 1);
    }
}
