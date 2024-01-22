package com.crrepa.n0;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
/* loaded from: classes9.dex */
public abstract class x<T> {

    /* loaded from: classes9.dex */
    public class a extends x<T> {
        public a() {
        }

        @Override // com.crrepa.n0.x
        public T a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return (T) x.this.a(aVar);
        }

        @Override // com.crrepa.n0.x
        public void a(com.crrepa.t0.d dVar, T t) throws IOException {
            if (t == null) {
                dVar.k();
            } else {
                x.this.a(dVar, (com.crrepa.t0.d) t);
            }
        }
    }

    public final x<T> a() {
        return new a();
    }

    public final T a(l lVar) {
        try {
            return a((com.crrepa.t0.a) new com.crrepa.q0.e(lVar));
        } catch (IOException e) {
            throw new m(e);
        }
    }

    public abstract T a(com.crrepa.t0.a aVar) throws IOException;

    public final T a(Reader reader) throws IOException {
        return a(new com.crrepa.t0.a(reader));
    }

    public final T a(String str) throws IOException {
        return a((Reader) new StringReader(str));
    }

    public final String a(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            a((Writer) stringWriter, (StringWriter) t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public abstract void a(com.crrepa.t0.d dVar, T t) throws IOException;

    public final void a(Writer writer, T t) throws IOException {
        a(new com.crrepa.t0.d(writer), (com.crrepa.t0.d) t);
    }

    public final l b(T t) {
        try {
            com.crrepa.q0.f fVar = new com.crrepa.q0.f();
            a((com.crrepa.t0.d) fVar, (com.crrepa.q0.f) t);
            return fVar.n();
        } catch (IOException e) {
            throw new m(e);
        }
    }
}
