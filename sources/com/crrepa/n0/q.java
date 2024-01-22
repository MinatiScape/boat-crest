package com.crrepa.n0;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
/* loaded from: classes9.dex */
public final class q {
    public l a(com.crrepa.t0.a aVar) throws m, v {
        boolean j = aVar.j();
        aVar.b(true);
        try {
            try {
                return com.crrepa.p0.k.a(aVar);
            } catch (OutOfMemoryError e) {
                throw new p("Failed parsing JSON source: " + aVar + " to Json", e);
            } catch (StackOverflowError e2) {
                throw new p("Failed parsing JSON source: " + aVar + " to Json", e2);
            }
        } finally {
            aVar.b(j);
        }
    }

    public l a(Reader reader) throws m, v {
        try {
            com.crrepa.t0.a aVar = new com.crrepa.t0.a(reader);
            l a2 = a(aVar);
            if (!a2.t() && aVar.t() != com.crrepa.t0.c.END_DOCUMENT) {
                throw new v("Did not consume the entire document.");
            }
            return a2;
        } catch (com.crrepa.t0.e e) {
            throw new v(e);
        } catch (IOException e2) {
            throw new m(e2);
        } catch (NumberFormatException e3) {
            throw new v(e3);
        }
    }

    public l a(String str) throws v {
        return a(new StringReader(str));
    }
}
