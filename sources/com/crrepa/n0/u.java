package com.crrepa.n0;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes9.dex */
public final class u implements Iterator<l> {
    public final com.crrepa.t0.a h;
    public final Object i;

    public u(Reader reader) {
        com.crrepa.t0.a aVar = new com.crrepa.t0.a(reader);
        this.h = aVar;
        aVar.b(true);
        this.i = new Object();
    }

    public u(String str) {
        this(new StringReader(str));
    }

    @Override // java.util.Iterator
    /* renamed from: a */
    public l next() throws p {
        if (hasNext()) {
            try {
                return com.crrepa.p0.k.a(this.h);
            } catch (p e) {
                if (e.getCause() instanceof EOFException) {
                    throw new NoSuchElementException();
                }
                throw e;
            } catch (OutOfMemoryError e2) {
                throw new p("Failed parsing JSON source to Json", e2);
            } catch (StackOverflowError e3) {
                throw new p("Failed parsing JSON source to Json", e3);
            }
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        boolean z;
        synchronized (this.i) {
            try {
                try {
                    z = this.h.t() != com.crrepa.t0.c.END_DOCUMENT;
                } catch (com.crrepa.t0.e e) {
                    throw new v(e);
                } catch (IOException e2) {
                    throw new m(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
