package com.crrepa.p0;

import com.crrepa.n0.m;
import com.crrepa.n0.p;
import com.crrepa.n0.v;
import com.crrepa.q0.n;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;
/* loaded from: classes9.dex */
public final class k {

    /* loaded from: classes9.dex */
    public static final class a extends Writer {
        public final Appendable h;
        public final C0352a i = new C0352a();

        /* renamed from: com.crrepa.p0.k$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0352a implements CharSequence {
            public char[] h;

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.h[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.h.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.h, i, i2 - i);
            }
        }

        public a(Appendable appendable) {
            this.h = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.h.append((char) i);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C0352a c0352a = this.i;
            c0352a.h = cArr;
            this.h.append(c0352a, i, i2 + i);
        }
    }

    public k() {
        throw new UnsupportedOperationException();
    }

    public static com.crrepa.n0.l a(com.crrepa.t0.a aVar) throws p {
        boolean z;
        try {
            try {
                aVar.t();
                z = false;
            } catch (EOFException e) {
                e = e;
                z = true;
            }
            try {
                return n.X.a(aVar);
            } catch (EOFException e2) {
                e = e2;
                if (z) {
                    return com.crrepa.n0.n.f7780a;
                }
                throw new v(e);
            }
        } catch (com.crrepa.t0.e e3) {
            throw new v(e3);
        } catch (IOException e4) {
            throw new m(e4);
        } catch (NumberFormatException e5) {
            throw new v(e5);
        }
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    public static void a(com.crrepa.n0.l lVar, com.crrepa.t0.d dVar) throws IOException {
        n.X.a(dVar, (com.crrepa.t0.d) lVar);
    }
}
